package com.dmm.tfg.engine.model;

import lombok.Getter;

@Getter
public class BoundingBox {
    private Vector2D center;
    private float radius;

    public BoundingBox(Vector2D center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean intersects(BoundingBox other) {
        float distance = this.center.distance(other.getCenter());
        return distance <= (this.radius + other.getRadius());
    }
}
