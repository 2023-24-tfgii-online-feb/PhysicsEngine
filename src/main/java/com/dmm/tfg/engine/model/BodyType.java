package com.dmm.tfg.engine.model;

/**
 * Enumerates the types of bodies that can exist within the physics simulation.
 * These types help in distinguishing between different entities in the simulation,
 * allowing for type-specific behavior and interactions.
 */
public enum BodyType {
    /**
     * Represents a planet, a large, stationary body that can exert gravitational forces.
     */
    PLANET,

    /**
     * Represents an asteroid, a smaller, movable body that can interact with other bodies.
     */
    ASTEROID,

    /**
     * Represents a spaceship, a controllable entity capable of complex maneuvers and interactions.
     */
    SPACESHIP
}
