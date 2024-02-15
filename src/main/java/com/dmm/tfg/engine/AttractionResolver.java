package com.dmm.tfg.engine;

import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Vector2D;
import com.dmm.tfg.service.QuadtreeService;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Component responsible for calculating gravitational attractions between bodies in the physics simulation.
 * Utilizes the Quadtree structure for efficient querying of nearby bodies to simulate gravitational forces.
 */
@Component
public class AttractionResolver {

    /**
     * Calculates and applies gravitational attractions between all bodies in the simulation.
     * This method iterates through each body, queries for nearby bodies using the quadtree service,
     * and applies a gravitational pull force between them.
     *
     * @param bodies          The list of bodies within the simulation to calculate attractions for.
     * @param quadtreeService The quadtree service used for efficiently finding nearby bodies.
     */
    public void calculateAttractions(List<Body> bodies, @NonNull QuadtreeService quadtreeService) {
        for (Body body : bodies) {
            List<Body> nearbyBodies = quadtreeService.queryNearbyBodies(body);
            for (Body nearbyBody : nearbyBodies) {
                if (!body.equals(nearbyBody)) {
                    Vector2D pull = gravitationalPull(body, nearbyBody);
                    body.applyForce(pull);
                }
            }
        }
    }



    /**
     * Calculates the gravitational pull force exerted by an attractor body on a mover body.
     * Uses the universal law of gravitation formula to compute the force based on the mass of the bodies
     * and the distance between them.
     *
     * @param attractor The body exerting the gravitational force.
     * @param mover     The body being attracted towards the attractor.
     * @return          The calculated gravitational force as a {@link Vector2D}.
     */
    private Vector2D gravitationalPull(@NonNull Body attractor, @NonNull Body mover) {
        //1. Calculate the direction of the force
        Vector2D force = Vector2D.sub(attractor.getPosition(), mover.getPosition());
        float distance = force.magnitude(); // We will use it later.
        force.normalize();

        //2. Calculate the magnitude of the force
        double GRAVITATIONAL_CONST = 6.67428e-11;
        float mag = (float) ((GRAVITATIONAL_CONST * attractor.getMass() * mover.getMass()) / (Math.pow(distance, 2)));

        //3. Apply said magnitude.
        force.multiply(mag);

        return force;
    }
}
