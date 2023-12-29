package com.dmm.tfg.engine.model;

import java.util.List;

public class Spaceship extends Body{
    private static final float WANDER_FORCE_MAGNITUDE = 0.1f;
    private static final float AVOIDANCE_FORCE_MAGNITUDE = 0.5f;
    private static final float AVOIDANCE_RANGE = 50.0f;
    private static final float MAX_AVOIDANCE_FORCE = 10f;
    private static final float MAX_VELOCITY = 5.0f;
    private static final float DAMPING_FACTOR = 0.99f;

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

    public void wander() {
        // Create a random vector for wandering
        double angle = Math.random() * 2 * Math.PI; // Random angle
        Vector2D wanderForce = new Vector2D(Math.cos(angle), Math.sin(angle));
        wanderForce.multiply(WANDER_FORCE_MAGNITUDE); // Adjust the magnitude as needed

        this.applyForce(wanderForce);
    }

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

    public void capVelocity() {
        if (this.getVelocity().magnitude() > MAX_VELOCITY) {
            this.getVelocity().normalize();
            this.getVelocity().multiply(MAX_VELOCITY);
        }
    }

    public void applyDamping() {
        this.getVelocity().multiply(DAMPING_FACTOR);
    }

}
