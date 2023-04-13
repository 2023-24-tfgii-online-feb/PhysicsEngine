package com.dmm.tfg.model;

public class Asteroid extends Body{

    private float radius;
    public Asteroid(Vector2D position, Vector2D velocity, float mass, float radius) {
        super(position, velocity, mass);
        this.radius = radius;
        this.bodyType = BodyType.ASTEROID;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
