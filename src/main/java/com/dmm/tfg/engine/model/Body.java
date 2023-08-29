package com.dmm.tfg.engine.model;

import lombok.Data;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;

@Data
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

    protected abstract void checkSizeConstraints();
}




