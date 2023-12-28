package com.dmm.tfg.service;

import com.dmm.tfg.engine.model.Body;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BodyService {



    private final DataService dataService;

    public void addRandomBody() {
       dataService.addBody(dataService.genRandomBody());
    }

    public void updateBodies(){
        dataService.updateBodies();
    }

    public void removeBody(long id){
        dataService.removeBody(id);
    }

    public List<Body> retrieveBodies(){
        return dataService.getAllBodies();
    }

    public void addBody() {
    }

    public void addRandomPlanet() {
        dataService.addBody(dataService.genRandomPlanet());
    }

    public void addRandomAsteorid() {
        dataService.addBody(dataService.genRandomAsteroid());
    }

    public void addRandomSpaceship() {
        dataService.addBody(dataService.genRandomSpaceship());
    }

    public void toggleSelectBody(long id) {
        Body body = dataService.getBody(id);
        if (body != null) {
            body.setSelected(!body.isSelected());
        }
    }
}

