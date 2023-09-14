package com.dmm.tfg.engine;

import com.dmm.tfg.engine.model.*;
import com.dmm.tfg.service.QuadtreeService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;

@Component
@NoArgsConstructor
public class CollisionResolver {

    public void checkEdges(List<Body> bodies) {
        for (Body b : bodies) {
            float radius = b.getBbox().getRadius();

            if (b instanceof Spaceship) {
                if (b.getPosition().getX() < 0) {
                    b.getPosition().setX(SPACE_WIDTH);
                }
                if (b.getPosition().getY() < 0) {
                    b.getPosition().setY(SPACE_HEIGHT);
                }
                if (b.getPosition().getX() > SPACE_WIDTH) {
                    b.getPosition().setX(0);
                }
                if (b.getPosition().getY() > SPACE_HEIGHT) {
                    b.getPosition().setY(0);
                }
            } else{
                if (b instanceof Planet) {
                    radius = (int) ((Planet) b).getRadius();
                }
                if (b instanceof Asteroid) {
                    radius = (int) ((Asteroid) b).getRadius();
                }
                if (b.getPosition().getX() + radius > SPACE_WIDTH || b.getPosition().getX() - radius < 0) {
                    b.getVelocity().setX(b.getVelocity().getX() * -1);
                }
                if (b.getPosition().getY() + radius > SPACE_HEIGHT || b.getPosition().getY() - radius < 0) {
                    b.getVelocity().setY(b.getVelocity().getY() * -1);
                }
            }
        }
    }


    public void checkCollisions(List<Body> bodies, QuadtreeService quadtreeService) {
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

    public boolean isColliding(Body body1, Body body2) {
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


    public void resolveCollision(Body body1, Body body2) {
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
