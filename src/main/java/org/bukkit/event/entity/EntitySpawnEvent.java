package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class EntitySpawnEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private Location location;
    private boolean cancelled;

    public EntitySpawnEvent(final Entity what, final Location location) {
        super(what);
        this.location = location;
    }

    /**
     * Gets the location at which the entity is spawning.
     *
     * @return The location at which the entity is spawning
     */
    public Location getLocation() {
        return location;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
