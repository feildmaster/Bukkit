package org.bukkit.metadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.Callable;

import org.junit.Test;

public class MetadataStoreTest {

    StringMetadataStore subject = new StringMetadataStore();

    @Test
    public void testMetadataStore() {
        subject.setMetadata("subject", "key", new FixedMetadataValue(new MockPlugin(), 10));

        assertTrue(subject.hasMetadata("subject", "key"));
        List<MetadataValue> values = subject.getMetadata("subject", "key");
        assertEquals(values.get(0).asInt(), 10);
    }

    @Test
    public void testMetadataNotPresent() {
        assertFalse(subject.hasMetadata("subject", "key"));
        List<MetadataValue> values = subject.getMetadata("subject", "key");
        assertTrue(values.isEmpty());
    }

    @Test
    public void testInvalidateAll() {
        final Counter counter = new Counter();

        MockPlugin mockPlugin = new MockPlugin();

        subject.setMetadata("subject", "key", new LazyMetadataValue(mockPlugin, new Callable<Object>() {
            public Object call() throws Exception {
                counter.increment();
                return 10;
            }
        }));

        assertTrue(subject.hasMetadata("subject", "key"));
        subject.getMetadata("subject", "key").get(0).asInt();
        subject.getMetadata("subject", "key").get(0).asInt();
        subject.invalidateAll(mockPlugin);
        subject.getMetadata("subject", "key").get(0).asInt();
        subject.getMetadata("subject", "key").get(0).asInt();
        assertEquals(counter.value(), 2);
    }

    @Test
    public void testMetadataReplace() {
        MockPlugin mockPlugin1 = new MockPlugin();
        MockPlugin mockPlugin2 = new MockPlugin();

        subject.setMetadata("subject", "key", new FixedMetadataValue(mockPlugin1, 10));
        subject.setMetadata("subject", "key", new FixedMetadataValue(mockPlugin2, 10));
        subject.setMetadata("subject", "key", new FixedMetadataValue(mockPlugin1, 20));

        for (MetadataValue mv : subject.getMetadata("subject", "key")) {
            if (mv.getOwningPlugin() == mockPlugin1) {
                assertEquals(mv.asInt(), 20);
            }
            if (mv.getOwningPlugin() == mockPlugin2) {
                assertEquals(mv.asInt(), 10);
            }
        }
    }

    @Test
    public void testMetadataRemove() {
        MockPlugin mockPlugin1 = new MockPlugin();
        MockPlugin mockPlugin2 = new MockPlugin();

        subject.setMetadata("subject", "key", new FixedMetadataValue(mockPlugin1, 10));
        subject.setMetadata("subject", "key", new FixedMetadataValue(mockPlugin2, 20));
        subject.removeMetadata("subject", "key", mockPlugin1);

        assertTrue(subject.hasMetadata("subject", "key"));
        assertEquals(subject.getMetadata("subject", "key").size(), 1);
        assertEquals(subject.getMetadata("subject", "key").get(0).asInt(), 20);
    }

    private class StringMetadataStore extends MetadataStoreBase<String> implements MetadataStore<String> {
        @Override
        protected String disambiguate(String subject, String metadataKey) {
            return subject + ":" + metadataKey;
        }
    }

    private class Counter {
        int c = 0;

        public void increment() {
            c++;
        }

        public int value() {
            return c;
        }
    }
}
