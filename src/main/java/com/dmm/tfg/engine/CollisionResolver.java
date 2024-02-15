package com.dmm.tfg.engine;

import com.dmm.tfg.engine.model.*;
import com.dmm.tfg.service.QuadtreeService;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;

/**
 * Handles collision detection and resolution within the physics simulation.
 * This includes checking for boundary collisions and resolving interactions between bodies.
 */
@Component
@NoArgsConstructor
public class CollisionResolver {

    /**
     * Checks and handles the collisions of all bodies against the edges of the simulation space.
     * It applies different strategies for spaceships and other bodies.
     *
     * @param bodies The list of bodies to check for edge collisions.
     */
    public void checkEdges(List<Body> bodies) {
        for (Body b : bodies) {
            if (b instanceof Spaceship) {
                handleSpaceshipEdge(b);
            } else {
                handleBodyEdge(b);
            }
        }
    }

    /**
     * Handles edge collisions specifically for spaceships, allowing them to wrap around the simulation space.
     *
     * @param b The spaceship body to handle.
     */
    private void handleSpaceshipEdge(Body b) {
        wrapPosition(b, SPACE_WIDTH, SPACE_HEIGHT);
    }

    /**
     * Handles edge collisions for non-spaceship bodies, reflecting their velocity upon reaching an edge.
     *
     * @param b The body to handle.
     */
    private void handleBodyEdge(Body b) {
        float radius = getAdjustedRadius(b);
        reflectVelocityIfEdgeReached(b, radius, SPACE_WIDTH, SPACE_HEIGHT);
    }

    /**
     * Calculates an adjusted radius for a body, considering specific body types like planets and asteroids.
     *
     * @param b The body for which to calculate the radius.
     * @return The adjusted radius.
     */
    private float getAdjustedRadius(Body b) {
        if (b instanceof Planet) {
            return ((Planet) b).getRadius();
        } else if (b instanceof Asteroid) {
            return ((Asteroid) b).getRadius();
        }
        return b.getBbox().getRadius();
    }

    /**
     * Wraps a body's position to the opposite edge of the simulation space if it exceeds boundaries.
     *
     * @param b        The body to wrap.
     * @param maxWidth The width of the simulation space.
     * @param maxHeight The height of the simulation space.
     */
    private void wrapPosition(Body b, double maxWidth, double maxHeight) {
        Vector2D position = b.getPosition();
        position.setX(wrapCoordinate(position.getX(), maxWidth));
        position.setY(wrapCoordinate(position.getY(), maxHeight));
    }

    /**
     * Wraps a coordinate value around a maximum value, used for wrapping positions.
     *
     * @param coordinate The coordinate to wrap.
     * @param max        The maximum value for the space dimension.
     * @return The wrapped coordinate value.
     */
    private double wrapCoordinate(double coordinate, double max) {
        if (coordinate < 0) return max;
        if (coordinate > max) return 0;
        return coordinate;
    }

    /**
     * Reflects a body's velocity if it reaches the edge of the simulation space, considering its radius.
     *
     * @param b        The body to reflect.
     * @param radius   The radius to consider for edge detection.
     * @param maxWidth The width of the simulation space.
     * @param maxHeight The height of the simulation space.
     */
    private void reflectVelocityIfEdgeReached(Body b, float radius, float maxWidth, float maxHeight) {
        Vector2D position = b.getPosition();
        Vector2D velocity = b.getVelocity();

        if (position.getX() + radius > maxWidth || position.getX() - radius < 0) {
            velocity.setX(velocity.getX() * -1);
        }
        if (position.getY() + radius > maxHeight || position.getY() - radius < 0) {
            velocity.setY(velocity.getY() * -1);
        }
    }

    /**
     * Checks for and resolves collisions between bodies using a quadtree for efficient nearby body querying.
     *
     * @param bodies          The list of bodies to check for collisions.
     * @param quadtreeService The quadtree service for querying nearby bodies.
     */
    public void checkCollisions(List<Body> bodies, @NonNull QuadtreeService quadtreeService) {
        for (Body body1 : bodies) {
            // Query Quadtree for nearby bodies
            List<Body> nearbyBodies = quadtreeService.queryNearbyBodies(body1);

            for (Body body2 : nearbyBodies) {
                // Skip self
                if (body1.getId() == body2.getId()) {
                    continue;
                }

                // Perform detailed collision check
                if (isColliding(body1, body2)) {
                    resolveCollision(body1, body2);
                }
            }
        }
    }

    /**
     * Determines if two bodies are colliding based on their bounding boxes.
     *
     * @param body1 The first body.
     * @param body2 The second body.
     * @return true if the bodies are colliding; false otherwise.
     */
    public boolean isColliding(@NonNull Body body1, @NonNull Body body2) {
        // Get the center and radius from the bounding boxes
        Vector2D center1 = body1.getBbox().getCenter();
        Vector2D center2 = body2.getBbox().getCenter();
        float radius1 = body1.getBbox().getRadius();
        float radius2 = body2.getBbox().getRadius();

        // Calculate the distance between the two centers
        double distance = center1.distance(center2);

        // Check if the distance is less than or equal to the sum of the two radii
        return distance <= (radius1 + radius2);
    }

    /**
     * Resolves the collision between two bodies, adjusting their velocities based on physical principles.
     *
     * @param body1 The first body involved in the collision.
     * @param body2 The second body involved in the collision.
     */
    public void resolveCollision(@NonNull Body body1, @NonNull Body body2) {
        // Step 1: Calculate the normal and relative velocity
        Vector2D normal = Vector2D.normalize(Vector2D.sub(body2.getPosition(), body1.getPosition()));
        Vector2D relVel = Vector2D.sub(body2.getVelocity(), body1.getVelocity());

        // Step 2: Find the relative velocity along the normal
        double relVelAlongNormal = Vector2D.dot(relVel, normal);

        // Step 3: Early exit if bodies are separating
        if (relVelAlongNormal > 0) {
            return;
        }

        // Step 4: Calculate the impulse
        double restitution = 0.8; // Adjust this value to get the desired bounce effect
        double impulseScalar = -(1 + restitution) * relVelAlongNormal;
        impulseScalar /= (1 / body1.getMass()) + (1 / body2.getMass());

        // Step 5: Find the impulse vector and apply the impulse
        Vector2D impulseVector = Vector2D.multiply(normal, (float) impulseScalar);
        Vector2D velocityChange1 = Vector2D.multiply(impulseVector, (float) (1 / body1.getMass()));
        Vector2D velocityChange2 = Vector2D.multiply(impulseVector, (float) (1 / body2.getMass()));

        body1.setVelocity(Vector2D.sub(body1.getVelocity(), velocityChange1));
        body2.setVelocity(Vector2D.add(body2.getVelocity(), velocityChange2));

    }
}
