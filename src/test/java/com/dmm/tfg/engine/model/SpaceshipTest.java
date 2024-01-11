package com.dmm.tfg.engine.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SpaceshipTest {

    @Test
    void constructorTest() {
        Vector2D position = new Vector2D(100, 100);
        Vector2D velocity = new Vector2D(2, 2);
        float mass = 100f;

        Spaceship spaceship = new Spaceship(position, velocity, mass);

        assertEquals(position, spaceship.getPosition());
        assertEquals(velocity, spaceship.getVelocity());
        assertEquals(mass, spaceship.getMass());
        assertEquals(BodyType.SPACESHIP, spaceship.getBodyType());
        assertNotNull(spaceship.getBbox());
    }

    @Test
    void checkSizeConstraintsTest() {
        Spaceship spaceship = new Spaceship(new Vector2D(-1, -1), new Vector2D(), 100f);
        spaceship.checkSizeConstraints();
        assertTrue(spaceship.getPosition().getX() >= 0);
        assertTrue(spaceship.getPosition().getY() >= 0);

        spaceship.setPosition(new Vector2D(101, 101));
        spaceship.checkSizeConstraints();
        assertTrue(spaceship.getPosition().getX() <= 100);
        assertTrue(spaceship.getPosition().getY() <= 100);
    }

    @Test
    void wanderTest() {
        Spaceship spaceship = new Spaceship(new Vector2D(50, 50), new Vector2D(), 100f);
        Vector2D initialVelocity = new Vector2D(spaceship.getVelocity().getX(), spaceship.getVelocity().getY());

        spaceship.wander();
        spaceship.update();
        Vector2D postWanderVelocity = spaceship.getVelocity();

        assertNotEquals(initialVelocity, postWanderVelocity);
    }

    @Test
    void avoidCollisionsTest() {
        Spaceship spaceship = new Spaceship(new Vector2D(50, 50), new Vector2D(), 100f);
        Vector2D initialVelocity = new Vector2D(spaceship.getVelocity().getX(), spaceship.getVelocity().getY());

        // Create a dummy nearby body
        Vector2D closePosition = new Vector2D(55, 55);
        Body nearbyBody = new Planet(closePosition,100000000f, 100f);
        List<Body> nearbyBodies = Collections.singletonList(nearbyBody);

        spaceship.avoidCollisions(nearbyBodies);
        spaceship.update();
        Vector2D postAvoidanceVelocity = spaceship.getVelocity();

        assertNotEquals(initialVelocity, postAvoidanceVelocity);
    }

    @Test
    void capVelocityTest() {
        Spaceship spaceship = new Spaceship(new Vector2D(50, 50), new Vector2D(10, 10), 100f);
        spaceship.capVelocity();

        assertTrue(spaceship.getVelocity().magnitude() <= 3.0f); // MAX_VELOCITY
    }

    @Test
    void applyDampingTest() {
        Spaceship spaceship = new Spaceship(new Vector2D(50, 50), new Vector2D(2, 2), 100f);
        float initialVelocity = spaceship.getVelocity().magnitude();
        spaceship.applyDamping();
        float dampenedVelocity = spaceship.getVelocity().magnitude();
        assertTrue(dampenedVelocity < initialVelocity);
    }

    @Test
    void seekTest() {
        Spaceship spaceship = new Spaceship(new Vector2D(50, 50), new Vector2D(), 100f);
        Vector2D target = new Vector2D(100, 100);

        spaceship.seek(target);
        spaceship.update();
        Vector2D velocityAfterSeek = spaceship.getVelocity();

        assertNotEquals(new Vector2D(), velocityAfterSeek);
    }
}
