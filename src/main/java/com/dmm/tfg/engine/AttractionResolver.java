package com.dmm.tfg.engine;

import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Vector2D;
import com.dmm.tfg.service.QuadtreeService;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AttractionResolver {


    /**
     *
     * @param bodies List of bodies of the system, all bodies are attracted among them.
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
     *
     * @param attractor Body entity that exhorts the gravitational pull.
     * @param mover     Body entity that is pulled towards the attractor.
     * @return          A Vector2D object which represents said force.
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
