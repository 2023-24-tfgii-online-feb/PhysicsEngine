package com.dmm.tfg.engine;

import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Vector2D;
import com.dmm.tfg.service.QuadtreeService;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AttractionResolver {


    /**
     *
     * @param bodies List of bodies of the system, all bodies are attracted among them.
     */
    public void calculateAttractions(List<Body> bodies, QuadtreeService quadtreeService) {
        for (int i = 0; i < bodies.size(); i++) {
            for (int j = i + 1; j < bodies.size(); j++) {
                Vector2D pull = gravitationalPull(bodies.get(i), bodies.get(j));
                bodies.get(i).applyForce(pull);
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
        double GRAVITATIONAL_CONST = 6.67428e-11;
        float mag = (float) ((GRAVITATIONAL_CONST * attractor.getMass() * mover.getMass()) / (Math.pow(distance, 2)));

        //3. Apply said magnitude.
        force.multiply(mag);

        return force;
    }
}
