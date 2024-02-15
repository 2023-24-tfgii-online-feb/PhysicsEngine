package com.dmm.tfg.engine.model;

import lombok.Getter;

/**
 * Represents a bounding box used for collision detection within the physics simulation.
 * It is defined by a center position and a radius, effectively creating a circular bounding area.
 */
@Getter
public class BoundingBox {
    private final Vector2D center;
    private final float radius;

    /**
     * Constructs a new BoundingBox with the specified center and radius.
     *
     * @param center The center point of the bounding box.
     * @param radius The radius of the bounding box, defining its size.
     */
    public BoundingBox(Vector2D center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Determines if this bounding box intersects with another bounding box.
     * Intersection is defined as the distance between the centers being less than or equal to the sum of their radii.
     *
     * @param other The other bounding box to check for intersection with.
     * @return true if the bounding boxes intersect; false otherwise.
     */
    public boolean intersects(BoundingBox other) {
        float distance = this.center.distance(other.getCenter());
        return distance <= (this.radius + other.getRadius());
    }
}
