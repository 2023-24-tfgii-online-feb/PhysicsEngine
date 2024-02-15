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

/**
 * Service class responsible for managing the persistence and retrieval of {@link Body} objects.
 * It acts as a bridge between the application and the data access layer, offering a high-level
 * abstraction for operations related to bodies such as planets, asteroids, and spaceships.
 */
@Service
@RequiredArgsConstructor
public class DataService {
    private final BodyDAOImpl bodyDAO;

    /**
     * Adds a new body to the persistence layer.
     *
     * @param body The body to add. Must not be null.
     */
    public void addBody(@NonNull Body body){
        bodyDAO.addBody(body);
    }

    /**
     * Removes a body from the persistence layer by its ID.
     *
     * @param id The unique identifier of the body to remove.
     */
    public void removeBody(long id){
        bodyDAO.removeBody(id);
    }

    /**
     * Retrieves a body by its ID.
     *
     * @param id The unique identifier of the body to retrieve.
     * @return The body with the specified ID, or null if no such body exists.
     */
    public Body getBody(long id) {
        return bodyDAO.getBody(id);
    }

    /**
     * Generates a random body instance.
     *
     * @return A randomly generated body.
     */
    public Body genRandomBody() {
        return bodyDAO.randomBody();
    }

    /**
     * Generates a random planet instance.
     *
     * @return A randomly generated planet.
     */
    public Planet genRandomPlanet() {
        return (Planet) bodyDAO.randomPlanet();
    }

    /**
     * Generates a random asteroid instance.
     *
     * @return A randomly generated asteroid.
     */
    public Asteroid genRandomAsteroid() {
        return (Asteroid) bodyDAO.randomAsteroid();
    }

    /**
     * Updates the state of all bodies in the persistence layer.
     */
    public void updateBodies() {
        bodyDAO.updateBodies();
    }

    /**
     * Retrieves a list of all bodies.
     *
     * @return A list of all bodies currently managed.
     */
    public List<Body> getAllBodies() {
        return bodyDAO.getAllBodies();
    }

    /**
     * Generates a random spaceship instance.
     *
     * @return A randomly generated spaceship.
     */
    public Body genRandomSpaceship() {
        return (Spaceship) bodyDAO.randomSpaceship();
    }
}