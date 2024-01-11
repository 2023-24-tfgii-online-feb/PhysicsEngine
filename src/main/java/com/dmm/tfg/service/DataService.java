package com.dmm.tfg.service;

import com.dmm.tfg.engine.dao.BodyDAOImpl;
import com.dmm.tfg.engine.model.Asteroid;
import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Planet;
import com.dmm.tfg.engine.model.Spaceship;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {
    private final BodyDAOImpl bodyDAO;

    public void addBody(@NonNull Body body){
        bodyDAO.addBody(body);
    }

    public void removeBody(long id){
        bodyDAO.removeBody(id);
    }

    public Body getBody(long id) {
        return bodyDAO.getBody(id);
    }
    public Body genRandomBody() {
        return bodyDAO.randomBody();
    }

    public Planet genRandomPlanet() {
        return (Planet) bodyDAO.randomPlanet();
    }

    public Asteroid genRandomAsteroid() {
        return (Asteroid) bodyDAO.randomAsteroid();
    }

    public void updateBodies() {
        bodyDAO.updateBodies();
    }

    public List<Body> getAllBodies() {
        return bodyDAO.getAllBodies();
    }

    public Body genRandomSpaceship() {
        return (Spaceship) bodyDAO.randomSpaceship();
    }
}