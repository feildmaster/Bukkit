package org.bukkit.metadata;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FixedMetadataValueTest {
    @Test
    public void fixedIntTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, 10);
        assertEquals(10, metadataValue.asInt());
    }

    @Test
    public void fixedDoubleTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, 10.5);
        assertEquals(10.5, metadataValue.asDouble(), 0.01);
    }

    @Test
    public void fixedStringTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, "TEN");
        assertEquals("TEN", metadataValue.asString());
    }

    @Test
    public void fixedBooleanTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, true);
        assertEquals(true, metadataValue.asBoolean());
    }
}
