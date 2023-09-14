package com.dmm.tfg.engine.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Planet extends Body{
    private float radius;

    public Planet(Vector2D position, float mass, float radius) {
        super(position, new Vector2D(), new Vector2D(), mass); //Stationary object, no velocity.
        this.radius = radius;
        this.setBbox(new BoundingBox(this.getPosition(), radius));
        this.bodyType = BodyType.PLANET;
        this.checkSizeConstraints();
    }
}
