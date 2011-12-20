package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

public interface MetadataValue {

    /**
     * Fetches the value of this metadata item.
     *
     * @return the metadata value.
     */
    public Object value();

    /**
     * Returns the {@link Plugin} that created this metadata item.
     *
     * @return the plugin that owns this metadata value.
     */
    public Plugin getOwningPlugin();

    /**
     * Invalidates this metadata item, forcing it to recompute when next accessed.
     */
    public void invalidate();
}
