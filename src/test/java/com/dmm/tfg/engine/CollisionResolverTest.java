package com.dmm.tfg.engine;

import com.dmm.tfg.engine.model.Asteroid;
import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.BoundingBox;
import com.dmm.tfg.engine.model.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CollisionResolverTest {

    private CollisionResolver collisionResolver;

    @BeforeEach
    void setUp() {
        collisionResolver = new CollisionResolver();
    }

    private Body createMockBody(double x, double y, double mass, float radius) {
        Body body = mock(Body.class);
        when(body.getPosition()).thenReturn(new Vector2D(x, y));
        when(body.getVelocity()).thenReturn(new Vector2D());
        when(body.getMass()).thenReturn(mass);
        when(body.getBbox()).thenReturn(new BoundingBox(new Vector2D(x, y), radius));
        return body;
    }

    @Test
    void testCheckEdges() {
        Asteroid body1 = new Asteroid(new Vector2D(5,5), new Vector2D(-1,-1), 10.0f, 5);
        body1.setPosition(new Vector2D(4,4));
        double velX = body1.getVelocity().getX();
        double velY = body1.getVelocity().getY();
        collisionResolver.checkEdges(List.of(body1));

        // Assert position changes after edge check
        assertNotEquals(velX, body1.getVelocity().getX());
        assertNotEquals(velY, body1.getVelocity().getY());
    }

    @Test
    void testIsColliding() {
        Body body1 = createMockBody(5, 5, 10.0, 5.0f);
        Body body2 = createMockBody(15, 15, 20.0, 10.0f);

        boolean isColliding = collisionResolver.isColliding(body1, body2);

        assertTrue(isColliding);
    }

    @Test
    void testResolveCollision() {
        Body body1 = createMockBody(0, 0, 10.0, 5.0f);
        Body body2 = createMockBody(10, 10, 20.0, 5.0f);

        // Assuming both bodies are already colliding
        collisionResolver.resolveCollision(body1, body2);

        // Verify that velocities are updated as a result of the collision
        verify(body1).setVelocity(any(Vector2D.class));
        verify(body2).setVelocity(any(Vector2D.class));
    }
}
