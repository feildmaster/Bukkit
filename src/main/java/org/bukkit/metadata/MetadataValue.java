package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

public interface MetadataValue {

    /**
     * Attempts to convert this metadata value to an int and return it.
     * @return the metadata value converted into an int.
     * @throws MetadataConversionException when metadata conversion fails.
     */
    public int asInt() throws MetadataConversionException;

    /**
     * Attempts to convert this metadata value to a double and return it.
     * @return the metadata value converted into a double.
     * @throws MetadataConversionException when metadata conversion fails.
     */
    public double asDouble() throws MetadataConversionException;

    /**
     * Attempts to convert this metadata value to a boolean and return it.
     * @return the metadata value converted into a boolean.
     * @throws MetadataConversionException when metadata conversion fails.
     */
    public boolean asBoolean() throws MetadataConversionException;

    /**
     * Returns the String representation of this metadata item.
     * @return the metadata value converted into a string.
     */
    public String asString();

    /**
     * Returns the {@link Plugin} that created this metadata item.
     * @return the plugin that owns this metadata value.
     */
    public Plugin getOwningPlugin();

    /**
     * Invalidates this metadata item, forcing it to recompute when next accessed.
     */
    public void invalidate();
}
