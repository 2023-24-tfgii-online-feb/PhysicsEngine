package com.dmm.tfg.engine.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlanetTest {

    @Test
    void testPlanetConstructor() {
        Vector2D position = new Vector2D(50, 50);
        float mass = 1000000f;
        float radius = 10f;

        Planet planet = new Planet(position, mass, radius);

        // Check position
        assertEquals(position, planet.getPosition());

        // Check that the velocity is initialized to zero
        assertEquals(new Vector2D(0, 0), planet.getVelocity());

        // Check mass
        assertEquals(mass, planet.getMass());

        // Check radius
        assertEquals(radius, planet.getRadius());

        // Check body type
        assertEquals(BodyType.PLANET, planet.getBodyType());

        // Check bounding box
        assertNotNull(planet.getBbox());
        assertEquals(radius, planet.getBbox().getRadius());
        assertEquals(position, planet.getBbox().getCenter());

        // Ensure size constraints are checked (this might need adjustment based on your checkSizeConstraints implementation)
        assertTrue(planet.getPosition().getX() >= planet.getBbox().getRadius());
        assertTrue(planet.getPosition().getY() >= planet.getBbox().getRadius());
    }
}
