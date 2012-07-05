package org.bukkit.plugin;

import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * Extends RegisteredListener to include timing information
 */
public class TimedRegisteredListener extends RegisteredListener {
    private int count;
    private long totalTime;
    private Class<? extends Event> eventClass;
    private boolean multiple = false;

    public TimedRegisteredListener(final Listener pluginListener, final EventExecutor eventExecutor, final EventPriority eventPriority, final Plugin registeredPlugin, final boolean listenCancelled) {
        super(pluginListener, eventExecutor, eventPriority, registeredPlugin, listenCancelled);
    }

    @Override
    public void callEvent(Event event) throws EventException {
        if (event.isAsynchronous()) {
            super.callEvent(event);
            return;
        }
        count++;
        if (this.eventClass == null) {
            this.eventClass = event.getClass();
        }
        else if (!this.eventClass.equals(event.getClass())) {
            multiple = true;
        }
        long start = System.nanoTime();
        super.callEvent(event);
        totalTime += System.nanoTime() - start;
    }

    /**
     * Resets the call count and total time for this listener
     */
    public void reset() {
        count = 0;
        totalTime = 0;
    }

    /**
     * Gets the total times this listener has been called
     *
     * @return Times this listener has been called
     */
    public int getCount() {
        return count;
    }

    /**
     * Gets the total time calls to this listener have taken
     *
     * @return Total time for all calls of this listener
     */
    public long getTotalTime() {
        return totalTime;
    }

    /**
     * Gets the class of the first event this listener handled
     *
     * @return The first event class handled by this RegisteredListener
     */
    public Class<? extends Event> getEventClass() {
        return eventClass;
    }

    /**
     * Gets whether this listener has handled multiple events
     *
     * @return True if this listener has handled multiple events
     */
    public boolean hasMultiple() {
        return multiple;
    }
}
