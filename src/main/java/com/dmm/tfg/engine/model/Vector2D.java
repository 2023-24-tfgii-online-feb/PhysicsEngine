package com.dmm.tfg.engine.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
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

    /**
     * Calculates the dot product of this vector and another vector.
     *
     * @param other The other vector.
     * @return The dot product of the two vectors.
     */
    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    /**
     * Calculates the dot product of two vectors.
     *
     * @param v1 The first vector.
     * @param v2 The second vector.
     * @return The dot product of the two vectors.
     */
    public static double dot(Vector2D v1, Vector2D v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public float magnitude(){
        return (float) Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
    }

    public void normalize(){
        float magnitude = this.magnitude();
        this.divide(magnitude);
    }
    
    public static Vector2D normalize(Vector2D vec){
        float magnitude = vec.magnitude();
        return Vector2D.divide(vec, magnitude);
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

    public float distance(Vector2D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vector2D vector2D = (Vector2D) obj;
        return Double.compare(vector2D.x, x) == 0 &&
                Double.compare(vector2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


}
