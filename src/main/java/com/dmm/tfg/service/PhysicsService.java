package com.dmm.tfg.service;

import com.dmm.tfg.engine.AttractionResolver;
import com.dmm.tfg.engine.CollisionResolver;
import com.dmm.tfg.engine.MovementResolver;
import com.dmm.tfg.engine.model.Asteroid;
import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Planet;
import com.dmm.tfg.engine.model.Vector2D;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for managing physics-related operations in the application.
 * It orchestrates various physics resolvers like attraction, collision, and movement
 * to simulate a dynamic environment where bodies interact with each other based on
 * physical principles.
 */
@Service
@RequiredArgsConstructor
public class PhysicsService {

    private final DataService dataService;
    private final AttractionResolver attractionResolver;
    private final CollisionResolver collisionResolver;
    private final MovementResolver movementResolver;
    private final QuadtreeService quadtreeService;



    /**
     * Initializes the simulation with a default set of bodies. This includes
     * adding a planet, an asteroid, and a randomly generated spaceship to the simulation.
     */
    public void setup(){
        dataService.addBody(new Planet(new Vector2D(400, 300), 100000000, 100));
        dataService.addBody(new Asteroid(new Vector2D(1,1), new Vector2D(0.5,0.5), 100000, 25));
        dataService.addBody(dataService.genRandomSpaceship());
    }

    /**
     * Advances the simulation by one tick. This involves clearing the current state,
     * updating the positions of all bodies based on their interactions, and resolving
     * movements, collisions, and attractions. The quadtree is used for efficient collision
     * detection and attraction calculations among bodies.
     */
    public void tick() {
        quadtreeService.clear();
        for (Body body : dataService.getAllBodies()) {
            quadtreeService.insertBody(body);
        }

        collisionResolver.checkEdges(dataService.getAllBodies());
        attractionResolver.calculateAttractions(dataService.getAllBodies(), quadtreeService);
        movementResolver.resolveSpaceshipMovement(dataService.getAllBodies(), quadtreeService);
        collisionResolver.checkCollisions(dataService.getAllBodies(), quadtreeService);
        dataService.updateBodies();
    }
}
