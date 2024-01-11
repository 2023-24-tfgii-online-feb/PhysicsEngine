package com.dmm.tfg.engine.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class Vector2DTest {

    @Test
    void testDefaultConstructor() {
        Vector2D vec = new Vector2D();
        assertEquals(0.0, vec.getX());
        assertEquals(0.0, vec.getY());
    }

    @Test
    void testParameterizedConstructor() {
        Vector2D vec = new Vector2D(3.0, 4.0);
        assertEquals(3.0, vec.getX());
        assertEquals(4.0, vec.getY());
    }

    @Test
    void testDotProduct() {
        Vector2D vec1 = new Vector2D(1, 2);
        Vector2D vec2 = new Vector2D(3, 4);
        double result = vec1.dot(vec2);
        assertEquals(11, result);
    }

    @Test
    void testDotProductStatic() {
        Vector2D vec1 = new Vector2D(1, 2);
        Vector2D vec2 = new Vector2D(3, 4);
        double result = Vector2D.dot(vec1, vec2);
        assertEquals(11, result);
    }

    @Test
    void testMagnitude() {
        Vector2D vec = new Vector2D(3, 4);
        assertEquals(5.0, vec.magnitude());
    }

    @Test
    void testNormalize() {
        Vector2D vec = new Vector2D(3, 4);
        vec.normalize();
        assertEquals(0.6, vec.getX(), 0.001);
        assertEquals(0.8, vec.getY(), 0.001);
    }

    @Test
    void testNormalizeStatic() {
        Vector2D vec = new Vector2D(3, 4);
        Vector2D normalizedVec = Vector2D.normalize(vec);
        assertEquals(0.6, normalizedVec.getX(), 0.001);
        assertEquals(0.8, normalizedVec.getY(), 0.001);
    }

    @Test
    void testAdd() {
        Vector2D vec1 = new Vector2D(1, 1);
        Vector2D vec2 = new Vector2D(2, 3);
        vec1.add(vec2);
        assertEquals(3.0, vec1.getX());
        assertEquals(4.0, vec1.getY());
    }

    @Test
    void testSubtract() {
        Vector2D vec1 = new Vector2D(5, 5);
        Vector2D vec2 = new Vector2D(2, 3);
        vec1.subtract(vec2);
        assertEquals(3.0, vec1.getX());
        assertEquals(2.0, vec1.getY());
    }

    @Test
    void testMultiply() {
        Vector2D vec = new Vector2D(3, 4);
        vec.multiply(2);
        assertEquals(6.0, vec.getX());
        assertEquals(8.0, vec.getY());
    }

    @Test
    void testDivide() {
        Vector2D vec = new Vector2D(10, 20);
        vec.divide(2);
        assertEquals(5.0, vec.getX());
        assertEquals(10.0, vec.getY());
    }

    @Test
    void testStaticAdd() {
        Vector2D vec1 = new Vector2D(1, 1);
        Vector2D vec2 = new Vector2D(2, 3);
        Vector2D result = Vector2D.add(vec1, vec2);
        assertEquals(3.0, result.getX());
        assertEquals(4.0, result.getY());
    }

    @Test
    void testStaticSubtract() {
        Vector2D vec1 = new Vector2D(5, 5);
        Vector2D vec2 = new Vector2D(2, 3);
        Vector2D result = Vector2D.sub(vec1, vec2);
        assertEquals(3.0, result.getX());
        assertEquals(2.0, result.getY());
    }

    @Test
    void testStaticMultiply() {
        Vector2D vec = new Vector2D(3, 4);
        Vector2D result = Vector2D.multiply(vec, 2);
        assertEquals(6.0, result.getX());
        assertEquals(8.0, result.getY());
    }

    @Test
    void testStaticDivide() {
        Vector2D vec = new Vector2D(10, 20);
        Vector2D result = Vector2D.divide(vec, 2);
        assertEquals(5.0, result.getX());
        assertEquals(10.0, result.getY());
    }

    @Test
    void testDistance() {
        Vector2D vec1 = new Vector2D(1, 1);
        Vector2D vec2 = new Vector2D(4, 5);
        float distance = vec1.distance(vec2);
        assertEquals(5.0, distance, 0.001);
    }

    @Test
    void testLimit() {
        Vector2D vec = new Vector2D(3, 4);
        vec.limit(2);
        assertTrue(vec.magnitude() <= 2);
    }

    @Test
    void testEquals() {
        Vector2D vec1 = new Vector2D(1, 2);
        Vector2D vec2 = new Vector2D(1, 2);
        Vector2D vec3 = new Vector2D(2, 3);
        assertEquals(vec1, vec2);
        assertNotEquals(vec1, vec3);
    }

    @Test
    void testIsZero() {
        Vector2D vec = new Vector2D(0, 0);
        assertTrue(vec.isZero());
    }
}
