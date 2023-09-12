package com.dmm.tfg;

import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Planet;
import com.dmm.tfg.engine.model.Vector2D;
import com.dmm.tfg.service.QuadtreeService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuadtreeServiceTest {


    @Test
    public void testSingleBodyInsertion() {
        // Setup
        QuadtreeService quadtreeService = new QuadtreeService();
        Body body = new Planet(new Vector2D(50, 50), 10, 10);

        // Execution
        quadtreeService.insertBody(body);

        // Verification
        List<Body> retrievedBodies = quadtreeService.queryNearbyBodies(body);
        assertEquals(1, retrievedBodies.size());
        assertEquals(body, retrievedBodies.get(0));
    }

    @Test
    public void testMultipleBodiesInsertion() {
        // Setup
        QuadtreeService quadtreeService = new QuadtreeService();
        Body body1 = new Planet(new Vector2D(30, 30), 10, 10);
        Body body2 = new Planet(new Vector2D(70, 70), 10, 10);

        // Execution
        quadtreeService.insertBody(body1);
        quadtreeService.insertBody(body2);

        // Verification
        List<Body> retrievedBodies = quadtreeService.queryNearbyBodies(body1);
        assertEquals(2, retrievedBodies.size());
        assertTrue(retrievedBodies.contains(body1));
        assertTrue(retrievedBodies.contains(body2));
    }


    @Test
    public void testClearQuadtree() {
        // Setup
        QuadtreeService quadtreeService = new QuadtreeService();
        Body body = new Planet(new Vector2D(50, 50), 10, 10);
        quadtreeService.insertBody(body);

        // Execution
        quadtreeService.clear();

        // Verification
        List<Body> retrievedBodies = quadtreeService.queryNearbyBodies(body);
        assertTrue(retrievedBodies.isEmpty());
    }




}
