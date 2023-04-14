package com.dmm.tfg.model;
import java.util.UUID;

import java.util.UUID;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;

public abstract class Body {
    private long id;
    private Vector2D position;
    private Vector2D velocity;



    private Vector2D acceleration;
    private float mass;

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

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    // Move the body according to its velocity and a time step
    public void update(float deltaTime) {
        position.add(Vector2D.multiply(velocity, deltaTime));
    }

    // Apply a force to the body
    public void applyForce(Vector2D force) {
        force.divide(getMass());
        acceleration.add(force);
    }

    public void checkEdges(){
        if (position.getX() > SPACE_WIDTH) {
            position.setX(0);
        } else if (position.getX() < 0){
            position.setX(SPACE_WIDTH);
        }

        if (position.getY() > SPACE_HEIGHT) {
            position.setY(0);
        } else if (position.getY() < 0){
            position.setY(SPACE_HEIGHT);
        }
    }

}




