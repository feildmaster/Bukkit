package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

/**
 * The LazyMetadataValue class implements a type of metadata that is not computed until another plugin asks for it.
 * By making metadata values lazy, no computation is done by the providing plugin until absolutely necessary (if ever).
 * Additionally, LazyMetadataValue objects cache their values internally unless overridden by a {@link CacheStrategy}
 * or invalidated at the individual or plugin level. Once invalidated, the LazyMetadataValue will recompute its value
 * when asked.
 */
public class LazyMetadataValue implements MetadataValue {
    private Callable<Object> lazyValue;
    private CacheStrategy cacheStrategy;
    private SoftReference<Object> internalValue = new SoftReference<Object>(null);
    private String owningPlugin;
    private static final Object ACTUALLY_NULL = new Object();

    /**
     * Initialized a LazyMetadataValue object with the default CACHE_AFTER_FIRST_EVAL cache strategy.
     *
     * @param owningPlugin the {@link Plugin} that created this metadata value.
     * @param lazyValue the lazy value assigned to this metadata value.
     */
    public LazyMetadataValue(Plugin owningPlugin, Callable<Object> lazyValue) {
        this(owningPlugin, CacheStrategy.CACHE_AFTER_FIRST_EVAL, lazyValue);
    }

    /**
     * Initializes a LazyMetadataValue object with a specific cache strategy.
     *
     * @param owningPlugin the {@link Plugin} that created this metadata value.
     * @param cacheStrategy determines the rules for caching this metadata value.
     * @param lazyValue the lazy value assigned to this metadata value.
     */
    public LazyMetadataValue(Plugin owningPlugin, CacheStrategy cacheStrategy, Callable<Object> lazyValue) {
        if (owningPlugin == null) {
            throw new IllegalArgumentException("owningPlugin cannot be null");
        }
        if (cacheStrategy == null) {
            throw new IllegalArgumentException("cacheStrategy cannot be null");
        }
        if (lazyValue == null) {
            throw new IllegalArgumentException("lazyValue cannot be null");
        }


        this.lazyValue = lazyValue;
        this.owningPlugin = owningPlugin.getDescription().getName().intern();
        this.cacheStrategy = cacheStrategy;
    }

    /**
     * Returns the name of the {@link Plugin} that created this metadata item.
     *
     * @return the name of the plugin that owns this metadata value.
     */
    public String getOwningPlugin() {
        return owningPlugin;
    }

    /**
     * Fetches the value of this metadata item.
     *
     * @return the metadata value.
     */
    public Object value() {
        eval();
        Object value = internalValue.get();
        if (value == ACTUALLY_NULL) {
            return null;
        } else {
            return value;
        }
    }

    /**
     * Lazily evaluates the value of this metadata item.
     *
     * @throws MetadataEvaluationException if computing the metadata value fails.
     */
    private synchronized void eval() throws MetadataEvaluationException {
        if (cacheStrategy == CacheStrategy.NEVER_CACHE || internalValue.get() == null) {
            try {
                Object value = lazyValue.call();
                if (value == null) {
                    value = ACTUALLY_NULL;
                }
                internalValue = new SoftReference<Object>(value);
            } catch (Exception e) {
                throw new MetadataEvaluationException(e);
            }
        }
    }

    /**
     * Invalidates this metadata item's value. The next time the value is requested it will be recomputed.
     */
    public synchronized void invalidate() {
        if (cacheStrategy != CacheStrategy.CACHE_ETERNALLY) {
            internalValue.clear();
        }
    }

    /**
     * Describes possible caching strategies for metadata.
     */
    public enum CacheStrategy {
        /**
         * Once the metadata value has been evaluated, do not re-evaluate the value until it is manually invalidated.
         */
        CACHE_AFTER_FIRST_EVAL,

        /**
         * Re-evaluate the metadata item every time it is requested
         */
        NEVER_CACHE,

        /**
         * Once the metadata value has been evaluated, do not re-evaluate the value in spite of manual invalidation.
         */
        CACHE_ETERNALLY
    }
}
