package org.bukkit.metadata;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FixedMetadataValueTest {
    @Test
    public void fixedIntTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, 10);
        assertEquals(10, metadataValue.value());
    }

    @Test
    public void fixedDoubleTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, 10.5);
        assertEquals(10.5, (Double)metadataValue.value(), 0.01);
    }

    @Test
    public void fixedStringTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, "TEN");
        assertEquals("TEN", metadataValue.value());
    }

    @Test
    public void fixedBooleanTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, true);
        assertEquals(true, metadataValue.value());
    }

    @Test
    public void fixedNullTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, null);
        assertEquals(null, metadataValue.value());
    }
}
