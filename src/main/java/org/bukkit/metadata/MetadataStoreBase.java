package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

import java.util.*;

public abstract class MetadataStoreBase<T> {
    private Map<String, List<MetadataValue>> metadataMap = new HashMap<String, List<MetadataValue>>();

    /**
     * Adds a metadata value to an object. Each metadata value is owned by a specific{@link Plugin}.
     * If a plugin has already added a metadata value to an object, that value
     * will be replaced with the value of {@code newMetadataValue}. Multiple plugins can set independent values for
     * the same {@code metadataKey} without conflict.
     *
     * Implementation note: I considered using a {@link java.util.concurrent.locks.ReadWriteLock} for controlling
     * access to {@code metadataMap}, but decided that the added overhead wasn't worth the finer grained access control.
     * Bukkit is almost entirely single threaded so locking overhead shouldn't pose a problem.
     *
     * @see MetadataStore#setMetadata(Object, String, MetadataValue)
     * @param subject The object receiving the metadata.
     * @param metadataKey A unique key to identify this metadata.
     * @param newMetadataValue The metadata value to apply.
     */
    public synchronized void setMetadata(T subject, String metadataKey, MetadataValue newMetadataValue) {
        String key = disambiguate(subject, metadataKey);
        if (!metadataMap.containsKey(key)) {
            metadataMap.put(key, new ArrayList<MetadataValue>());
        }
        // we now have a list of subject's metadata for the given metadata key. If newMetadataValue's owningPlugin
        // is found in this list, replace the value rather than add a new one.
        List<MetadataValue> metadataList = metadataMap.get(key);
        for (int i = 0; i < metadataList.size(); i++) {
            if (samePlugin(metadataList.get(i).getOwningPlugin(), newMetadataValue.getOwningPlugin())) {
                metadataList.set(i, newMetadataValue);
                return;
            }
        }
        // we didn't find a duplicate...add the new metadata value
        metadataList.add(newMetadataValue);
    }

    /**
     * Returns all metadata values attached to an object. If multiple plugins have attached metadata, each will value
     * will be included.
     *
     * @see MetadataStore#getMetadata(Object, String)
     * @param subject the object being interrogated.
     * @param metadataKey the unique metadata key being sought.
     * @return A list of values, one for each plugin that has set the requested value.
     */
    public synchronized List<MetadataValue> getMetadata(T subject, String metadataKey) {
        String key = disambiguate(subject, metadataKey);
        if (metadataMap.containsKey(key)) {
            return Collections.unmodifiableList(metadataMap.get(key));
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Tests to see if a metadata attribute has been set on an object.
     *
     * @param subject the object upon which the has-metadata test is performed.
     * @param metadataKey the unique metadata key being queried.
     * @return the existence of the metadataKey within subject.
     */
    public synchronized boolean hasMetadata(T subject, String metadataKey) {
        String key = disambiguate(subject, metadataKey);
        return metadataMap.containsKey(key);
    }

    /**
     * Removes a metadata item owned by a plugin from a subject.
     *
     * @see MetadataStore#removeMetadata(Object, String, org.bukkit.plugin.Plugin)
     * @param subject the object to remove the metadata from.
     * @param metadataKey the unique metadata key identifying the metadata to remove.
     * @param owningPlugin the plugin attempting to remove a metadata item.
     */
    public synchronized void removeMetadata(T subject, String metadataKey, Plugin owningPlugin) {
        String key = disambiguate(subject, metadataKey);
        List<MetadataValue> metadataList = metadataMap.get(key);
        for (int i = 0; i < metadataList.size(); i++) {
            if (samePlugin(metadataList.get(i).getOwningPlugin(), owningPlugin)) {
                metadataList.remove(i);
            }
        }
    }

    /**
     * Invalidates all metadata in the metadata store that originates from the given plugin. Doing this will force
     * each invalidated metadata item to be recalculated the next time it is accessed.
     *
     * @see MetadataStore#invalidateAll(org.bukkit.plugin.Plugin)
     * @param owningPlugin the plugin requesting the invalidation.
     */
    public synchronized void invalidateAll(Plugin owningPlugin) {
        for (List<MetadataValue> values : metadataMap.values()) {
            for (MetadataValue value : values) {
                if (samePlugin(value.getOwningPlugin(), owningPlugin)) {
                    value.invalidate();
                }
            }
        }
    }

    private boolean samePlugin(Plugin lhs, Plugin rhs) {
        if (lhs == null || rhs == null) {
           return false;
        }
        return lhs.getDescription().getFullName().equals(rhs.getDescription().getFullName());
    }

    /**
     * Creates a unique name for the object receiving metadata by combining unique data from the subject with a metadataKey.
     * The name created must be globally unique for the given object and any two equivalent objects must generate the
     * same unique name. For example, two Player objects must generate the same string if they represent the same player,
     * even if the objects would fail a reference equality test.
     *
     * @param subject The object for which this key is being generated.
     * @param metadataKey The name identifying the metadata value.
     * @return a unique metadata key for the given subject.
     */
    protected abstract String disambiguate(T subject, String metadataKey);
}
