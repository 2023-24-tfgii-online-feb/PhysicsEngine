package com.dmm.tfg.engine.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AsteroidTest {

    @Test
    void testAsteroidConstructor() {
        Vector2D position = new Vector2D(100, 100);
        Vector2D velocity = new Vector2D(1, 1);
        float mass = 5000f;
        float radius = 20f;

        Asteroid asteroid = new Asteroid(position, velocity, mass, radius);

        // Check position, velocity, and mass
        assertEquals(position, asteroid.getPosition());
        assertEquals(velocity, asteroid.getVelocity());
        assertEquals(mass, asteroid.getMass());

        // Check radius
        assertEquals(radius, asteroid.getRadius());

        // Check body type
        assertEquals(BodyType.ASTEROID, asteroid.getBodyType());

        // Check vertices
        assertNotNull(asteroid.getVertices());
        assertFalse(asteroid.getVertices().isEmpty());

        // Check bounding box
        assertNotNull(asteroid.getBbox());
        assertEquals(radius, asteroid.getBbox().getRadius());
        assertEquals(position, asteroid.getBbox().getCenter());

        // Check spin value
        assertTrue(asteroid.getSpin() >= 1 && asteroid.getSpin() <= 5);
    }

    @Test
    void testUpdateMethod() {
        Vector2D position = new Vector2D(100, 100);
        Vector2D velocity = new Vector2D(1, 1);
        float mass = 5000f;
        float radius = 20f;

        Asteroid asteroid = new Asteroid(position, velocity, mass, radius);

        // Capture initial vertices state
        List<Vector2D> initialVertices = new ArrayList<>();
        for (Vector2D v : asteroid.getVertices()) {
            initialVertices.add(new Vector2D(v.getX(), v.getY()));
        }

        // Update the asteroid
        asteroid.update();

        // Check if vertices have been rotated
        List<Vector2D> updatedVertices = asteroid.getVertices();
        boolean isRotated = false;
        for (int i = 0; i < initialVertices.size(); i++) {
            Vector2D initial = initialVertices.get(i);
            Vector2D updated = updatedVertices.get(i);
            if (initial.getX() != updated.getX() || initial.getY() != updated.getY()) {
                isRotated = true;
                break;
            }
        }

        assertTrue(isRotated, "Vertices should have rotated.");
    }
}
