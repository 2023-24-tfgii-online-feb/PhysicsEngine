package com.dmm.tfg.service;

import com.dmm.tfg.model.Body;
import com.dmm.tfg.model.Vector2D;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class PhysicsService {

    /**
     * Gravitational constant translated into a float.
     */
    private final double GRAVITATIONAL_CONST = 6.67428e-11;
    private DataService dataService;

    public void calculateAttractions() {
        ArrayList<Body> bodies = new ArrayList<>(dataService.getAllBodies());
        for (int i = 0; i < bodies.size(); i++) {
            for (int j = i + 1; j < bodies.size(); j++) {
                Vector2D pull = gravitationalPull(bodies.get(i), bodies.get(j));
            }
        }
    }

    /**
     *
     * @param attractor Body entity that exhorts the gravitational pull.
     * @param mover     Body entity that is pulled towards the attractor.
     * @return          A Vector2D object which represents said force.
     */
    private Vector2D gravitationalPull(Body attractor, Body mover) {
        //1. Calculate the direction of the force
        Vector2D force = Vector2D.sub(attractor.getPosition(), mover.getPosition());
        float distance = force.magnitude(); // We will use it later.
        force.normalize();

        //2. Calculate the magnitude of the force
        float mag = (float) ((GRAVITATIONAL_CONST * attractor.getMass() * mover.getMass()) / (Math.pow(distance, 2)));

        //3. Apply said magnitude.
        force.multiply(mag);

        return force;
    }


}
