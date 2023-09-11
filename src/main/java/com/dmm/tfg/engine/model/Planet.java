package com.dmm.tfg.engine.model;

import lombok.Getter;
import lombok.Setter;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;

@Getter
@Setter
public class Planet extends Body{
    private float radius;

    public Planet(Vector2D position, float mass, float radius) {
        super(position, new Vector2D(), new Vector2D(), mass); //Stationary object, no velocity.
        this.radius = radius;
        this.setBbox(new BoundingBox(this.getPosition(), radius));
        this.bodyType = BodyType.PLANET;
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
}
