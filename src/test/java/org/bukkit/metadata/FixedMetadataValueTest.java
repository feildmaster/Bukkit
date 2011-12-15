package org.bukkit.metadata;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FixedMetadataValueTest {
    @Test
    public void fixedIntTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, 10);
        assertEquals(metadataValue.asInt(), 10);
    }

    @Test
    public void fixedDoubleTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, 10.5);
        assertEquals(metadataValue.asDouble(), 10.5, 0.01);
    }

    @Test
    public void fixedStringTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, "TEN");
        assertEquals(metadataValue.asString(), "TEN");
    }

    @Test
    public void fixedBooleanTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, true);
        assertEquals(metadataValue.asBoolean(), true);
    }
}
