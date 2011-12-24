package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

import java.util.concurrent.Callable;

/**
 * A FixedMetadataValue is a special case metadata item that contains the same value forever after initialization.
 * Invalidating a FixedMetadataValue has no affect.
 */
public class FixedMetadataValue extends LazyMetadataValue {
    /**
     * Initializes a FixedMetadataValue with an Object
     *
     * @param owningPlugin the {@link Plugin} that created this metadata value.
     * @param value the value assigned to this metadata value.
     */
    public FixedMetadataValue(Plugin owningPlugin, final Object value) {
        super(owningPlugin, CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                return value;
            }
        });
    }

    /**
     * Initializes a FixedMetadataValue as an int
     *
     * @param owningPlugin the {@link Plugin} that created this metadata value.
     * @param value the value assigned to this metadata value.
     */
    public FixedMetadataValue(Plugin owningPlugin, final int value) {
        super(owningPlugin, CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                return value;
            }
        });
    }

    /**
     * Initializes a FixedMetadataValue as a float
     *
     * @param owningPlugin the {@link Plugin} that created this metadata value.
     * @param value the value assigned to this metadata value.
     */
    public FixedMetadataValue(Plugin owningPlugin, final float value) {
        super(owningPlugin, CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                return value;
            }
        });
    }

    /**
     * Initializes a FixedMetadataValue as a long
     *
     * @param owningPlugin the {@link Plugin} that created this metadata value.
     * @param value the value assigned to this metadata value.
     */
    public FixedMetadataValue(Plugin owningPlugin, final long value) {
        super(owningPlugin, CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                return value;
            }
        });
    }

    /**
     * Initializes a FixedMetadataValue as a short
     *
     * @param owningPlugin the {@link Plugin} that created this metadata value.
     * @param value the value assigned to this metadata value.
     */
    public FixedMetadataValue(Plugin owningPlugin, final short value) {
        super(owningPlugin, CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                return value;
            }
        });
    }

    /**
     * Initializes a FixedMetadataValue as a byte
     *
     * @param owningPlugin the {@link Plugin} that created this metadata value.
     * @param value the value assigned to this metadata value.
     */
    public FixedMetadataValue(Plugin owningPlugin, final byte value) {
        super(owningPlugin, CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                return value;
            }
        });
    }

    /**
     * Initializes a FixedMetadataValue as a boolean
     *
     * @param owningPlugin the {@link Plugin} that created this metadata value.
     * @param value the value assigned to this metadata value.
     */
    public FixedMetadataValue(Plugin owningPlugin, final boolean value) {
        super(owningPlugin, CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                return value;
            }
        });
    }

    /**
     * Initializes a FixedMetadataValue as a double
     *
     * @param owningPlugin the {@link Plugin} that created this metadata value.
     * @param value the value assigned to this metadata value.
     */
    public FixedMetadataValue(Plugin owningPlugin, final double value) {
        super(owningPlugin, CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                return value;
            }
        });
    }

    /**
     * Initializes a FixedMetadataValue as a string
     *
     * @param owningPlugin the {@link Plugin} that created this metadata value.
     * @param value the value assigned to this metadata value.
     */
    public FixedMetadataValue(Plugin owningPlugin, final String value) {
        super(owningPlugin, CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                return value;
            }
        });
    }
}
