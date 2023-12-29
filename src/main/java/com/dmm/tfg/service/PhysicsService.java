package com.dmm.tfg.service;

import com.dmm.tfg.engine.AttractionResolver;
import com.dmm.tfg.engine.CollisionResolver;
import com.dmm.tfg.engine.MovementResolver;
import com.dmm.tfg.engine.model.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class PhysicsService {

    private final DataService dataService;
    private final AttractionResolver attractionResolver;
    private final CollisionResolver collisionResolver;
    private final MovementResolver movementResolver;
    private final QuadtreeService quadtreeService;




    public void setup(){
        dataService.addBody(new Planet(new Vector2D(400, 300), 100000000, 100));
        dataService.addBody(new Asteroid(new Vector2D(1,1), new Vector2D(1,1), 100, 25));
    }


    public void tick() {
        quadtreeService.clear();
        for (Body body : dataService.getAllBodies()) {
            quadtreeService.insertBody(body);
        }

        collisionResolver.checkEdges(dataService.getAllBodies());
        attractionResolver.calculateAttractions(dataService.getAllBodies(), quadtreeService);
        collisionResolver.checkCollisions(dataService.getAllBodies(), quadtreeService);
        movementResolver.resolveSpaceshipMovement(dataService.getAllBodies(), quadtreeService);
        dataService.updateBodies();
    }
}
