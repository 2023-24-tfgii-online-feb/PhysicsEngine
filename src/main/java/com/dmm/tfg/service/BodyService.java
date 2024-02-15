package com.dmm.tfg.service;

import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Spaceship;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Service class for managing operations related to Body entities within the application.
 * It provides functionalities for adding, updating, removing, and retrieving Body objects,
 * including specific types like planets, asteroids, and spaceships. It acts as a facade
 * over the DataService, simplifying the interaction with Body objects.
 */
@Service
@RequiredArgsConstructor
public class BodyService {

    private final DataService dataService;

    /**
     * Adds a randomly generated body to the application's data store.
     */
    public void addRandomBody() {
       dataService.addBody(dataService.genRandomBody());
    }

    /**
     * Triggers an update operation on all bodies within the application's data store.
     */
    public void updateBodies(){
        dataService.updateBodies();
    }

    /**
     * Removes a body from the application's data store by its unique identifier.
     *
     * @param id The unique identifier of the body to remove.
     */
    public void removeBody(long id){
        dataService.removeBody(id);
    }

    /**
     * Retrieves a list of all bodies currently managed by the application.
     *
     * @return A list of all bodies.
     */
    public List<Body> retrieveBodies(){
        return dataService.getAllBodies();
    }

    /**
     * Adds a randomly generated planet to the application's data store.
     */
    public void addRandomPlanet() {
        dataService.addBody(dataService.genRandomPlanet());
    }

    /**
     * Adds a randomly generated asteroid to the application's data store.
     */
    public void addRandomAsteroid() {
        dataService.addBody(dataService.genRandomAsteroid());
    }

    /**
     * Adds a randomly generated spaceship to the application's data store.
     */
    public void addRandomSpaceship() {
        dataService.addBody(dataService.genRandomSpaceship());
    }

    /**
     * Toggles the selection state of a body identified by its unique identifier.
     *
     * @param id The unique identifier of the body to toggle selection state.
     */
    public void toggleSelectBody(long id) {
        Body body = dataService.getBody(id);
        if (body != null) {
            body.setSelected(!body.isSelected());
        }
    }

    /**
     * Retrieves a list of all spaceships that are currently selected.
     *
     * @return A list of selected spaceships.
     */
    public List<Spaceship> getSelectedSpaceships() {
        List<Spaceship> selectedSpaceships = new ArrayList<>();
        for (Body body : dataService.getAllBodies()){
            if (body instanceof Spaceship && body.isSelected()){
                selectedSpaceships.add((Spaceship) body);
            }
        }
        return selectedSpaceships;
    }
}

