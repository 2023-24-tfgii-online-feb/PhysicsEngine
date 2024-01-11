package com.dmm.tfg.engine.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Represents a 2-dimensional vector with basic vector operations.
 */
@Getter
@Setter
public class Vector2D {
    private double x;
    private double y;

    /**
     * Default constructor initializing the vector to (0,0).
     */
    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructs a vector with specified x and y coordinates.
     *
     * @param x The x-coordinate of the vector.
     * @param y The y-coordinate of the vector.
     */
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

    /**
     * Calculates the magnitude of this vector.
     *
     * @return The magnitude (length) of the vector.
     */
    public float magnitude(){
        return (float) Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
    }

    /**
     * Normalizes this vector to have a magnitude of 1.
     */
    public void normalize(){
        float magnitude = this.magnitude();
        this.divide(magnitude);
    }

    /**
     * Returns a normalized version of the given vector.
     *
     * @param vec The vector to normalize.
     * @return A new vector that is the normalized version of the input vector.
     */
    public static Vector2D normalize(Vector2D vec){
        float magnitude = vec.magnitude();
        return Vector2D.divide(vec, magnitude);
    }

    /**
     * Adds the specified vector to this vector.
     *
     * @param other The vector to add.
     */
    public void add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
    }

    /**
     * Subtracts the specified vector from this vector.
     *
     * @param other The vector to subtract.
     */
    public void subtract(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
    }


    /**
     * Multiplies this vector by a scalar.
     *
     * @param scalar The scalar to multiply the vector by.
     */
    public void multiply(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    /**
     * Divides this vector by a scalar.
     *
     * @param scalar The scalar to divide the vector by.
     */
    public void divide(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
    }

    /**
     * Adds two vectors together and returns the resulting vector.
     *
     * @param a The first vector to be added.
     * @param b The second vector to be added.
     * @return A new Vector2D instance representing the sum of vectors a and b.
     */
    public static Vector2D add(Vector2D a, Vector2D b) {
        return new Vector2D(a.x + b.x, a.y + b.y);
    }

    /**
     * Subtracts one vector from another and returns the resulting vector.
     *
     * @param a The vector from which vec2 will be subtracted.
     * @param b The vector to be subtracted from vec1.
     * @return A new Vector2D instance representing the difference between vec1 and vec2.
     */
    public static Vector2D sub(Vector2D a, Vector2D b){
        return new Vector2D(a.x - b.x, a.y - b.y);
    }

    /**
     * Multiplies a vector by a scalar value and returns the resulting vector.
     *
     * @param a The vector to be multiplied.
     * @param scalar The scalar value by which the vector will be multiplied.
     * @return A new Vector2D instance representing the product of vector a and the scalar.
     */
    public static Vector2D multiply(Vector2D a, float scalar) {
        return new Vector2D(a.x * scalar,
                a.y * scalar);
    }

    /**
     * Divides a vector by a scalar value and returns the resulting vector.
     *
     * @param a The vector to be divided.
     * @param scalar The scalar value by which the vector will be divided.
     * @return A new Vector2D instance representing the quotient of vector a divided by the scalar.
     */
    public static Vector2D divide(Vector2D a, float scalar) {
        return new Vector2D(a.x / scalar,
                a.y / scalar);
    }

    /**
     * Calculates the distance between this vector and another vector.
     *
     * @param other The other vector.
     * @return The distance between this vector and the other vector.
     */
    public float distance(Vector2D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Limits the magnitude of this vector to a maximum value.
     *
     * @param max The maximum magnitude.
     */
    public void limit(float max) {
        if (this.magnitude() > max) {
            this.normalize();
            this.multiply(max);
        }
    }

    /**
     * Checks if the vector is a zero vector (both x and y are 0).
     *
     * @return true if the vector is a zero vector, false otherwise.
     */
    public boolean isZero() {
        return getX() == 0 && getY() == 0;
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
