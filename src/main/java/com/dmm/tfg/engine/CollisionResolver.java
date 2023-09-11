package com.dmm.tfg.engine;

import com.dmm.tfg.engine.model.Asteroid;
import com.dmm.tfg.engine.model.Planet;
import com.dmm.tfg.engine.model.Vector2D;
import com.dmm.tfg.service.QuadtreeService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import com.dmm.tfg.engine.model.Body;

import java.util.List;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;

@Component
@NoArgsConstructor
public class CollisionResolver {

    public void checkEdges(List<Body> bodies) {
        for (Body b : bodies) {
            int radius = 0;

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
        Vector2D vel1Initial = body1.getVelocity();
        Vector2D vel2Initial = body2.getVelocity();

        double m1 = body1.getMass();
        double m2 = body2.getMass();

        // Relative velocity
        Vector2D relVelInitial = Vector2D.sub(vel1Initial, vel2Initial);

        // Coefficient of restitution
        float e = 0.8f;  // Choose a value between 0 and 1

        // New velocities after collision
        Vector2D term1 = Vector2D.multiply(relVelInitial, (float) ((1 + e) * m2 / (m1 + m2)));
        Vector2D vel1Final = Vector2D.sub(vel1Initial, term1);

        Vector2D term2 = Vector2D.multiply(relVelInitial, (float) ((1 + e) * m1 / (m1 + m2)));
        Vector2D vel2Final = Vector2D.add(vel2Initial, term2);

        body1.setVelocity(vel1Final);
        body2.setVelocity(vel2Final);
    }
}
