package com.dmm.tfg.engine;

import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Spaceship;
import com.dmm.tfg.service.QuadtreeService;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Resolves the movement of spaceships within the physics simulation.
 * This includes applying wandering motion, avoiding collisions, damping velocity, and capping the maximum velocity.
 */
@Component
@NoArgsConstructor
public class MovementResolver {

    /**
     * Resolves the movement of all spaceship entities within the simulation.
     * This method iterates through all bodies, identifies spaceships, and applies movement logic
     * including wandering, collision avoidance, damping, and velocity capping based on nearby bodies.
     *
     * @param allBodies A list of all bodies in the simulation.
     * @param quadtreeService The quadtree service used for efficient spatial querying of nearby bodies.
     */
    public void resolveSpaceshipMovement(List<Body> allBodies, @NonNull QuadtreeService quadtreeService) {
        for (Body body : allBodies){
            if (body instanceof Spaceship spaceship){
                List<Body> nearbyBodies = quadtreeService.queryNearbyBodies(spaceship);
                spaceship.wander();
                spaceship.avoidCollisions(nearbyBodies);
                spaceship.applyDamping();
                spaceship.capVelocity();
            }
        }
    }
}
