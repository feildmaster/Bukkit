package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;

/**
 * Called when an item is spawned into a world
 */
public class ItemSpawnEvent extends EntitySpawnEvent {
    private static final HandlerList handlers = new HandlerList();

    public ItemSpawnEvent(final Entity spawnee, final Location loc) {
        super(spawnee, loc);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
