package com.dmm.tfg.engine.model;

import org.junit.jupiter.api.Test;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;
import static org.junit.jupiter.api.Assertions.*;

public class BodyTest {

    private static class TestableBody extends Body {
        public TestableBody() {
            super();
        }

        public TestableBody(Vector2D position, Vector2D velocity, Vector2D acceleration, float mass) {
            super(position, velocity, acceleration, mass);
        }
    }

    @Test
    void testDefaultConstructor() {
        Body body = new TestableBody();
        assertEquals(0, body.getId());
        assertNotNull(body.getPosition());
        assertNotNull(body.getVelocity());
        assertNotNull(body.getAcceleration());
        assertEquals(0, body.getMass());
    }

    @Test
    void testParameterizedConstructor() {
        Vector2D position = new Vector2D(1, 1);
        Vector2D velocity = new Vector2D(2, 2);
        Vector2D acceleration = new Vector2D(3, 3);
        float mass = 10.0f;

        Body body = new TestableBody(position, velocity, acceleration, mass);
        assertEquals(position, body.getPosition());
        assertEquals(velocity, body.getVelocity());
        assertEquals(acceleration, body.getAcceleration());
        assertEquals(mass, body.getMass());
    }

    @Test
    void testApplyForce() {
        Body body = new TestableBody();
        Vector2D force = new Vector2D(2, 3);
        body.setMass(1);
        body.applyForce(force);

        Vector2D expectedAcceleration = new Vector2D(2, 3);
        assertEquals(expectedAcceleration, body.getAcceleration());
    }

    @Test
    void testUpdate() {
        Body body = new TestableBody();
        body.setPosition(new Vector2D(1, 1));
        body.setVelocity(new Vector2D(2, 2));
        body.setAcceleration(new Vector2D(1, 1));

        body.update();

        assertEquals(new Vector2D(4, 4), body.getPosition());
        assertEquals(new Vector2D(3, 3), body.getVelocity());
        assertEquals(new Vector2D(0, 0), body.getAcceleration());
    }

    @Test
    void testCheckSizeConstraints() {
        final float radius = 10.0f;
        Body body = new TestableBody();
        body.setBbox(new BoundingBox(body.getPosition(), radius));

        // Test constraint at left boundary
        body.setPosition(new Vector2D(-5, 50));
        body.checkSizeConstraints();
        assertEquals(radius, body.getPosition().getX());

        // Test constraint at top boundary
        body.setPosition(new Vector2D(50, -5));
        body.checkSizeConstraints();
        assertEquals(radius, body.getPosition().getY());

        // Test constraint at right boundary
        body.setPosition(new Vector2D(SPACE_WIDTH + 5, 50));
        body.checkSizeConstraints();
        assertEquals(SPACE_WIDTH - radius, body.getPosition().getX());

        // Test constraint at bottom boundary
        body.setPosition(new Vector2D(50, SPACE_HEIGHT + 5));
        body.checkSizeConstraints();
        assertEquals(SPACE_HEIGHT - radius, body.getPosition().getY());
    }


    @Test
    void testSetSelected() {
        Body body = new TestableBody();
        body.setSelected(true);
        assertTrue(body.isSelected());
    }

}
