package com.dmm.tfg.engine.model;

import lombok.Getter;
import lombok.Setter;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;

/**
 * Abstract class representing a physical body within the physics engine.
 * It includes properties for the body's ID, position, velocity, acceleration, mass, and bounding box,
 * along with a type and a selection flag. This class serves as a base for all entities within the simulation,
 * providing common functionality such as force application and size constraint checks.
 */
@Getter
@Setter
public abstract class Body {
    private long id;
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;
    private double mass;
    private BoundingBox bbox;

    /**
     * The type of the body, indicating whether it is a PLANET, ASTEROID, or SPACESHIP.
     * This classification helps in applying different physics rules and interactions based on the body type.
     */
    protected BodyType bodyType;
    private boolean selected;

    /**
     * Default constructor initializing a body with default position, velocity, and acceleration vectors.
     */
    public Body() {
        this.id = 0;
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
    }

    /**
     * Constructs a body with specified position, velocity, acceleration, and mass.
     *
     * @param position     The initial position of the body.
     * @param velocity     The initial velocity of the body.
     * @param acceleration The initial acceleration of the body.
     * @param mass         The mass of the body.
     */
    public Body(Vector2D position, Vector2D velocity, Vector2D acceleration, float mass) {
        this.id = 0;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.mass = mass;
        this.selected = false;
    }

    /**
     * Sets the selected state of the body.
     *
     * @param selected The new selected state.
     */
    public void setSelected(boolean selected){
        this.selected = selected;
    }

    /**
     * Applies a force to the body, adjusting its acceleration accordingly.
     *
     * @param force The force to apply.
     */
    public void applyForce(Vector2D force) {
        force.divide(getMass());
        acceleration.add(force);
    }

    /**
     * Checks and adjusts the body's position to ensure it remains within the defined size constraints
     * of the simulation space.
     */
    protected void checkSizeConstraints(){
        Vector2D position = getPosition();
        float radius = this.bbox.getRadius();
        if (position.getX() < radius){
            position.setX(radius);
        }
        if (position.getY() < radius){
            position.setY(radius);
        }

        if (position.getX() + radius > SPACE_WIDTH){
            position.setX(SPACE_WIDTH - radius);
        }

        if (position.getY() + radius > SPACE_HEIGHT){
            position.setY(SPACE_HEIGHT - radius);
        }
    }

    /**
     * Updates the body's velocity and position based on its current acceleration,
     * and then resets the acceleration for the next simulation tick.
     */
    public void update() {
        this.getVelocity().add(this.getAcceleration());
        this.getPosition().add(this.getVelocity());
        this.acceleration.multiply(0);
    }
}




