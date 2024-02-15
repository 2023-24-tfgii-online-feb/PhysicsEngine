package com.dmm.tfg.engine.dao;

import com.dmm.tfg.engine.model.Body;

import java.util.List;

/**
 * Interface for data access operations related to {@link Body} entities.
 * Defines basic CRUD operations along with methods to retrieve all bodies.
 */
public interface BodyDAO {

    /**
     * Retrieves a body by its unique identifier.
     *
     * @param id The unique identifier of the body to retrieve.
     * @return The body with the specified ID, or null if not found.
     */
    Body getBody(Long id);

    /**
     * Adds a new body to the data store.
     *
     * @param body The body to add.
     */
    void addBody(Body body);

    /**
     * Retrieves all bodies from the data store.
     *
     * @return A list of all bodies.
     */
    List<Body> getAllBodies();

    /**
     * Updates the state of all bodies in the data store.
     */
    void updateBodies();

    /**
     * Removes a body from the data store by its unique identifier.
     *
     * @param id The unique identifier of the body to remove.
     */
    void removeBody(Long id);
}
