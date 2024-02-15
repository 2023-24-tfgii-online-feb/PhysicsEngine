package com.dmm.tfg.engine.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a planet in the physics engine. A planet is a type of {@link Body} with a specified radius.
 * It initializes with a position, mass, and radius, and assumes no initial velocity or acceleration
 * as planets are considered stationary for the simulation's purposes. The bounding box for collision
 * detection is set based on the radius.
 */
@Getter
@Setter
public class Planet extends Body{
    private float radius;

    /**
     * Constructs a new Planet with the specified position, mass, and radius. The planet is
     * stationary, with no initial velocity or acceleration. A bounding box is created based
     * on the radius to facilitate collision detection.
     *
     * @param position The position of the planet in the simulation space.
     * @param mass The mass of the planet. Used in physics calculations.
     * @param radius The radius of the planet. Used for rendering and collision detection.
     */
    public Planet(Vector2D position, float mass, float radius) {
        super(position, new Vector2D(), new Vector2D(), mass); //Stationary object, no velocity.
        this.radius = radius;
        this.setBbox(new BoundingBox(this.getPosition(), radius));
        this.bodyType = BodyType.PLANET;
        this.checkSizeConstraints();
    }
}
