package com.dmm.tfg.model;

public class Planet extends Body{
    private float radius;

    public Planet(Vector2D position, float mass, float radius) {
        super(position, new Vector2D(0,0), mass); //Stationary object, no velocity.
        this.radius = radius;
        this.bodyType = BodyType.PLANET;
    }


    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }



}
