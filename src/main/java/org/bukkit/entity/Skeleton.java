package org.bukkit.entity;

/**
 * Represents a Skeleton.
 */
public interface Skeleton extends Monster {
    public enum Type {
        NORMAL, WITHER;
    }

    /**
     * Get the skeleton type
     * @return Type of skeleton
     */
    Type getSkeletonType();

    void setSkeletonType(Type type);
}
