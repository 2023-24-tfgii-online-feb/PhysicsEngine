package com.dmm.tfg.engine;

import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Spaceship;
import com.dmm.tfg.service.QuadtreeService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@NoArgsConstructor
public class MovementResolver {

    public void resolveSpaceshipMovement(List<Body> allBodies, QuadtreeService quadtreeService) {
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
