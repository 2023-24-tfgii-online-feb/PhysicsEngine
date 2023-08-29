package com.dmm.tfg.service;

import com.dmm.tfg.engine.AttractionResolver;
import com.dmm.tfg.engine.CollisionResolver;
import com.dmm.tfg.engine.model.Asteroid;
import com.dmm.tfg.engine.model.Planet;
import com.dmm.tfg.engine.model.Vector2D;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PhysicsService {

    private final DataService dataService;
    private final AttractionResolver attractionResolver;
    private final CollisionResolver collisionResolver;


    public void setup(){
        dataService.addBody(new Planet(new Vector2D(400, 300), 100000000, 100));
        dataService.addBody(new Asteroid(new Vector2D(), new Vector2D(1,1), 100, 25));
    }

    private void applyAcceleration(){
        dataService.getAllBodies().forEach(body -> body.getVelocity().add(body.getAcceleration()));
    }

    public void tick() {
        collisionResolver.checkEdges(dataService.getAllBodies());
        attractionResolver.calculateAttractions(dataService.getAllBodies());
        applyAcceleration();
        dataService.updateBodies();
    }
}
