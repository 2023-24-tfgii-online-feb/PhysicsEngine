package com.dmm.tfg.model;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public float magnitude(){
        return (float) Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
    }

    public void normalize(){
        float magnitude = this.magnitude();
        this.divide(magnitude);
    }

    public void applyForce(){

    }
    public void add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
    }

    public void subtract(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
    }

    public static Vector2D sub(Vector2D vec1, Vector2D vec2){
        double x = vec1.getX() - vec2.getX();
        double y = vec1.getY() - vec2.getY();
        return new Vector2D(x,y);
    }

    public void multiply(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public void divide(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
    }

    public static Vector2D add(Vector2D a, Vector2D b) {
        return new Vector2D(a.x + b.x, a.y + b.y);
    }

    public static Vector2D subtract(Vector2D a, Vector2D b) {
        return new Vector2D(a.x - b.x, a.y - b.y);
    }

    public static Vector2D multiply(Vector2D a, float scalar) {
        return new Vector2D(a.x * scalar, a.y * scalar);
    }

    public static Vector2D divide(Vector2D a, float scalar) {
        return new Vector2D(a.x / scalar, a.y / scalar);
    }
}
