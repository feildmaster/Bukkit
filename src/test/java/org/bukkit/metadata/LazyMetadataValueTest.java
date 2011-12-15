package org.bukkit.metadata;

import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.*;

public class LazyMetadataValueTest {
    private LazyMetadataValue subject;

    @Test
    public void testLazyInt() {
        int value = 10;
        subject = makeSimpleCallable(value);

        assertEquals(value, subject.asInt());
    }

    @Test
    public void testLazyDouble() {
        double value = 10.5;
        subject = makeSimpleCallable(value);

        assertEquals(value, subject.asDouble(), 0.01);
    }

    @Test
    public void testLazyString() {
        String value = "TEN";
        subject = makeSimpleCallable(value);

        assertEquals(value, subject.asString());
    }

    @Test
    public void testLazyBoolean() {
        boolean value = false;
        subject = makeSimpleCallable(value);

        assertEquals(value, subject.asBoolean());
    }

    @Test(expected=MetadataConversionException.class)
    public void testFailingInt() {
        makeSimpleCallable("NotAnInt").asInt();
    }

    @Test(expected=MetadataConversionException.class)
    public void testFailingDouble() {
        makeSimpleCallable("NotADouble").asDouble();
    }

    @Test(expected=MetadataEvaluationException.class)
    public void testEvalException() {
        subject = new LazyMetadataValue(null, LazyMetadataValue.CacheStrategy.CACHE_AFTER_FIRST_EVAL, new Callable<Object>() {
            public Object call() throws Exception {
                throw new RuntimeException("Gotcha!");
            }
        });
        subject.asString();
    }

    @Test
    public void testCacheStrategyCacheAfterFirstEval() {
        final Counter counter = new Counter();
        final int value = 10;
        subject = new LazyMetadataValue(null, LazyMetadataValue.CacheStrategy.CACHE_AFTER_FIRST_EVAL, new Callable<Object>() {
            public Object call() throws Exception {
                counter.increment();
                return value;
            }
        });

        subject.asInt();
        subject.asInt();
        assertEquals(value, subject.asInt());
        assertEquals(1, counter.value());

        subject.invalidate();
        subject.asInt();
        assertEquals(2, counter.value());
    }

    @Test
    public void testCacheStrategyNeverCache() {
        final Counter counter = new Counter();
        final int value = 10;
        subject = new LazyMetadataValue(null, LazyMetadataValue.CacheStrategy.NEVER_CACHE, new Callable<Object>() {
            public Object call() throws Exception {
                counter.increment();
                return value;
            }
        });

        subject.asInt();
        subject.asInt();
        assertEquals(value, subject.asInt());
        assertEquals(3, counter.value());
    }

    @Test
    public void testCacheStrategyEternally() {
        final Counter counter = new Counter();
        final int value = 10;
        subject = new LazyMetadataValue(null, LazyMetadataValue.CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                counter.increment();
                return value;
            }
        });

        subject.asInt();
        subject.asInt();
        assertEquals(value, subject.asInt());
        assertEquals(1, counter.value());

        subject.invalidate();
        subject.asInt();
        assertEquals(value, subject.asInt());
        assertEquals(1, counter.value());
    }

    private LazyMetadataValue makeSimpleCallable(final Object value) {
        return new LazyMetadataValue(null, new Callable<Object>() {
            public Object call() throws Exception {
                return value;
            }
        });
    }

    private class Counter {
        private int c = 0;

        public void increment() {
            c++;
        }

        public int value() {
            return c;
        }
    }
}
