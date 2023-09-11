package com.dmm.tfg.service;

import com.dmm.tfg.engine.AttractionResolver;
import com.dmm.tfg.engine.CollisionResolver;
import com.dmm.tfg.engine.model.Asteroid;
import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Planet;
import com.dmm.tfg.engine.model.Vector2D;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.index.quadtree.Quadtree;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PhysicsService {

    private final DataService dataService;
    private final AttractionResolver attractionResolver;
    private final CollisionResolver collisionResolver;
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

        dataService.updateBodies();
    }
}
