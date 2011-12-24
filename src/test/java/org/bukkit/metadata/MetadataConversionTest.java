//    Copyright (C) 2011  Ryan Michela
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.bukkit.metadata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 */
public class MetadataConversionTest {
    @Test
    public void testFromInt() {
        FixedMetadataValue val = new FixedMetadataValue(new MockPlugin("x"), 10);
        assertEquals(10, val.asInt());
        assertEquals(10, val.asFloat(), 0.000001);
        assertEquals(10, val.asDouble(), 0.000001);
        assertEquals(10, val.asLong());
        assertEquals(10, val.asShort());
        assertEquals(10, val.asByte());
        assertEquals(true, val.asBoolean());
        assertEquals("10", val.asString());
    }

    @Test
    public void testFromFloat() {
        FixedMetadataValue val = new FixedMetadataValue(new MockPlugin("x"), 10.5);
        assertEquals(10, val.asInt());
        assertEquals(10.5, val.asFloat(), 0.000001);
        assertEquals(10.5, val.asDouble(), 0.000001);
        assertEquals(10, val.asLong());
        assertEquals(10, val.asShort());
        assertEquals(10, val.asByte());
        assertEquals(true, val.asBoolean());
        assertEquals("10.5", val.asString());
    }

    @Test
    public void testFromNumericString() {
        FixedMetadataValue val = new FixedMetadataValue(new MockPlugin("x"), "10");
        assertEquals(10, val.asInt());
        assertEquals(10, val.asFloat(), 0.000001);
        assertEquals(10, val.asDouble(), 0.000001);
        assertEquals(10, val.asLong());
        assertEquals(10, val.asShort());
        assertEquals(10, val.asByte());
        assertEquals(false, val.asBoolean());
        assertEquals("10", val.asString());
    }

    @Test
    public void testFromNonNumericString() {
        FixedMetadataValue val = new FixedMetadataValue(new MockPlugin("x"), "true");
        assertEquals(0, val.asInt());
        assertEquals(0, val.asFloat(), 0.000001);
        assertEquals(0, val.asDouble(), 0.000001);
        assertEquals(0, val.asLong());
        assertEquals(0, val.asShort());
        assertEquals(0, val.asByte());
        assertEquals(true, val.asBoolean());
        assertEquals("true", val.asString());
    }

    @Test
    public void testFromNull() {
        FixedMetadataValue val = new FixedMetadataValue(new MockPlugin("x"), null);
        assertEquals(0, val.asInt());
        assertEquals(0, val.asFloat(), 0.000001);
        assertEquals(0, val.asDouble(), 0.000001);
        assertEquals(0, val.asLong());
        assertEquals(0, val.asShort());
        assertEquals(0, val.asByte());
        assertEquals(false, val.asBoolean());
        assertEquals("", val.asString());
    }
}
