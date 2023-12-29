package com.dmm.tfg.engine.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;

@Getter
@Setter
public abstract class Body {
    private long id;
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;
    private double mass;
    private BoundingBox bbox;
    protected BodyType bodyType;
    private boolean selected;

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
        this.selected = false;
    }

    public void setSelected(boolean selected){
        this.selected = selected;
    }

    // Apply a force to the body
    public void applyForce(Vector2D force) {
        force.divide(getMass());
        acceleration.add(force);
    }

    protected void checkSizeConstraints(){
        Vector2D position = getPosition();
        float radius = this.bbox.getRadius();
        if (position.getX() < radius){
            position.setX(position.getX() + radius);
        }
        if (position.getY() < radius){
            position.setY(position.getY() + radius);
        }

        if (position.getX() + radius > SPACE_WIDTH){
            position.setX(position.getX() - radius);
        }

        if (position.getY() + radius > SPACE_HEIGHT){
            position.setY(position.getY() - radius);
        }
    }

    public void update() {
        this.getVelocity().add(this.getAcceleration());
        this.getPosition().add(this.getVelocity());
        this.acceleration.multiply(0);
    }
}




