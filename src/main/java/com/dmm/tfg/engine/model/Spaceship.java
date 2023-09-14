package com.dmm.tfg.engine.model;

public class Spaceship extends Body{
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

}
