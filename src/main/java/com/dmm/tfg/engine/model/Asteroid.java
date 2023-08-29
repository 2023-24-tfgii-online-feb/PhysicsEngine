package com.dmm.tfg.engine.model;

import lombok.Getter;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;

@Getter
public class Asteroid extends Body{

    private float radius;
    public Asteroid(Vector2D position, Vector2D velocity, float mass, float radius) {
        super(position, velocity, new Vector2D(), mass);
        this.radius = radius;
        this.bodyType = BodyType.ASTEROID;
        this.checkSizeConstraints();
    }

    @Override
    protected void checkSizeConstraints(){
        Vector2D position = getPosition();
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

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
