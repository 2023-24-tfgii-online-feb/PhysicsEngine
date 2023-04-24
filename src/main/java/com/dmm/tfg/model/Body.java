package com.dmm.tfg.model;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;

public abstract class Body {
    private long id;
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;
    private double mass;
    private BoundingBox bbox;
    protected BodyType bodyType;

    public Body() {
        this.id = 0;
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
    }

    public Body(Vector2D position, Vector2D velocity, Vector2D acceleration, float mass) {
        this.id = 0;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.mass = mass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public Vector2D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    // Apply a force to the body
    public void applyForce(Vector2D force) {
        force.divide(getMass());
        acceleration.add(force);
    }

    public void checkEdges(){
        if (position.getX()  > SPACE_WIDTH || position.getX() < 0) {
            velocity.setX(velocity.getX() * -1);
        }

        if (position.getY() > SPACE_HEIGHT || position.getY() < 0) {
            velocity.setY(velocity.getY() * -1);
        }
    }

}




