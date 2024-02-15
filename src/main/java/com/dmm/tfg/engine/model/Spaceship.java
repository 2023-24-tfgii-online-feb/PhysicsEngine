package com.dmm.tfg.engine.model;

import java.util.List;

/**
 * Spaceship represents a maneuverable entity within the physics simulation.
 * It extends the generic {@link Body} class, inheriting its physical properties such as position and mass,
 * and introduces behaviors unique to spaceships like wandering, collision avoidance, velocity capping,
 * damping, and seeking a target.
 */
public class Spaceship extends Body{
    private static final float WANDER_FORCE_MAGNITUDE = 0.1f;
    private static final float AVOIDANCE_RANGE = 100f;
    private static final float MAX_AVOIDANCE_FORCE = 5f;
    private static final float MAX_VELOCITY = 3.0f;
    private static final float DAMPING_FACTOR = 0.99f;
    private static final float MAX_FORCE = 0.3f;

    /**
     * Constructs a Spaceship with given position, velocity, and mass.
     *
     * @param position The initial position of the spaceship.
     * @param velocity The initial velocity of the spaceship.
     * @param mass     The mass of the spaceship.
     */
    public Spaceship(Vector2D position, Vector2D velocity, float mass) {
        super(position, velocity, new Vector2D(), mass);
        this.bodyType = BodyType.SPACESHIP;
        this.setBbox(new BoundingBox(position, 15));
    }


    @Override
    protected void checkSizeConstraints(){
        Vector2D position = getPosition();
        if (position.getX() < 0){
            position.setX(position.getX() + 1);
        }
        if (position.getY() < 0){
            position.setY(position.getY() + 1);
        }

        if (position.getX() + 1 > 100){
            position.setX(position.getX() - 1);
        }

        if (position.getY() + 1 > 100){
            position.setY(position.getY() - 1);
        }
    }

    /**
     * Applies a wandering force to the spaceship for random movement.
     */
    public void wander() {
        // Create a random vector for wandering
        double angle = Math.random() * 2 * Math.PI; // Random angle
        Vector2D wanderForce = new Vector2D(Math.cos(angle), Math.sin(angle));
        wanderForce.multiply(WANDER_FORCE_MAGNITUDE); // Adjust the magnitude as needed

        this.applyForce(wanderForce);
    }

    /**
     * Avoids collisions with nearby bodies by applying an avoidance force.
     *
     * @param nearbyBodies A list of bodies to consider for collision avoidance.
     */
    public void avoidCollisions(List<Body> nearbyBodies) {
        Vector2D avoidanceForce = new Vector2D(0, 0);

        for (Body nearbyBody : nearbyBodies) {
            float distance = this.getPosition().distance(nearbyBody.getPosition());
            if (distance < AVOIDANCE_RANGE && distance > 0) {
                Vector2D awayFromBody = Vector2D.sub(this.getPosition(), nearbyBody.getPosition());
                if (!awayFromBody.isZero()) {
                    awayFromBody.normalize();
                    awayFromBody.divide(distance);
                    avoidanceForce.add(awayFromBody);
                }
            }
        }

        // Cap the avoidance force to avoid excessively large values
        if (avoidanceForce.magnitude() > MAX_AVOIDANCE_FORCE) {
            avoidanceForce.normalize();
            avoidanceForce.multiply(MAX_AVOIDANCE_FORCE);
        }

        this.applyForce(avoidanceForce);
    }

    /**
     * Caps the velocity of the spaceship to a maximum speed.
     */
    public void capVelocity() {
        if (this.getVelocity().magnitude() > MAX_VELOCITY) {
            this.getVelocity().normalize();
            this.getVelocity().multiply(MAX_VELOCITY);
        }
    }

    /**
     * Applies damping to reduce the spaceship's velocity over time.
     */
    public void applyDamping() {
        this.getVelocity().multiply(DAMPING_FACTOR);
    }

    /**
     * Seeks a target position, adjusting velocity based on distance to the target.
     *
     * @param target The target position to seek.
     */
    public void seek(Vector2D target) {
        float slowingRadius = 100f;
        this.seek(target, slowingRadius);
    }

    /**
     * Seeks a target position with a specified slowing radius for smooth stopping.
     *
     * @param target        The target position to seek.
     * @param slowingRadius The radius within which the spaceship begins to slow down.
     */
    public void seek(Vector2D target, float slowingRadius) {
        Vector2D desired = Vector2D.sub(target, this.getPosition());
        float distance = desired.magnitude();
        desired.normalize();

        if (distance < slowingRadius) {
            // Inside the slowing area (Arrive behavior)
            float m = map(distance, 0, slowingRadius, 0, MAX_VELOCITY);
            desired.multiply(m);
        } else {
            // Outside the slowing area (Seek behavior)
            desired.multiply(MAX_VELOCITY);
        }

        // Steering force calculation
        Vector2D steer = Vector2D.sub(desired, this.getVelocity());
        steer.limit(MAX_FORCE);

        // Apply the combined steering force
        this.applyForce(steer);
    }

    /**
     * Utility method to linearly interpolate a value within a specified range.
     *
     * @param value  The value to map.
     * @param start1 The start of the initial range.
     * @param stop1  The end of the initial range.
     * @param start2 The start of the target range.
     * @param stop2  The end of the target range.
     * @return The mapped value.
     */
    private static float map(float value, float start1, float stop1, float start2, float stop2) {
        return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
    }

}
