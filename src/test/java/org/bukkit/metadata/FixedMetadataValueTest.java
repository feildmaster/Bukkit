package org.bukkit.metadata;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FixedMetadataValueTest {
    @Test
    public void fixedIntTest() {
        MockPlugin mockPlugin = new MockPlugin("x");
        FixedMetadataValue metadataValue = new FixedMetadataValue(mockPlugin, 10);
        assertEquals(10, metadataValue.value());
    }

    @Test
    public void fixedDoubleTest() {
        MockPlugin mockPlugin = new MockPlugin("x");
        FixedMetadataValue metadataValue = new FixedMetadataValue(mockPlugin, 10.5);
        assertEquals(10.5, (Double) metadataValue.value(), 0.01);
    }

    @Test
    public void fixedStringTest() {
        MockPlugin mockPlugin = new MockPlugin("x");
        FixedMetadataValue metadataValue = new FixedMetadataValue(mockPlugin, "TEN");
        assertEquals("TEN", metadataValue.value());
    }

    @Test
    public void fixedBooleanTest() {
        MockPlugin mockPlugin = new MockPlugin("x");
        FixedMetadataValue metadataValue = new FixedMetadataValue(mockPlugin, true);
        assertEquals(true, metadataValue.value());
    }

    @Test
    public void fixedNullTest() {
        MockPlugin mockPlugin = new MockPlugin("x");
        FixedMetadataValue metadataValue = new FixedMetadataValue(mockPlugin, true);
        assertEquals(null, metadataValue.value());
    }

    @Test
    public void fixedFloatTest() {
        MockPlugin mockPlugin = new MockPlugin("x");
        FixedMetadataValue metadataValue = new FixedMetadataValue(mockPlugin, (float)10.5);
        assertEquals((float)10.5, metadataValue.value());
    }

    @Test
    public void fixedLongTest() {
        MockPlugin mockPlugin = new MockPlugin("x");
        FixedMetadataValue metadataValue = new FixedMetadataValue(mockPlugin, (long)10);
        assertEquals((long)10, metadataValue.value());
    }

    @Test
    public void fixedShortTest() {
        MockPlugin mockPlugin = new MockPlugin("x");
        FixedMetadataValue metadataValue = new FixedMetadataValue(mockPlugin, (short)10);
        assertEquals((short)10, metadataValue.value());
    }

    @Test
    public void fixedByteTest() {
        MockPlugin mockPlugin = new MockPlugin("x");
        FixedMetadataValue metadataValue = new FixedMetadataValue(mockPlugin, (byte)10);
        assertEquals((byte)10, metadataValue.value());
    }
}
