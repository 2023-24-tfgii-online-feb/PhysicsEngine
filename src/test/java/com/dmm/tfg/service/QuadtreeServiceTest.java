package com.dmm.tfg.service;

import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.BoundingBox;
import com.dmm.tfg.engine.model.Planet;
import com.dmm.tfg.engine.model.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuadtreeServiceTest {

    private QuadtreeService quadtreeService;

    @BeforeEach
    void setUp() {
        quadtreeService = new QuadtreeService();
    }

    @Test
    void testInsertAndQueryBody() {
        Vector2D position = new Vector2D(50, 50);
        BoundingBox bbox = new BoundingBox(position, 10);
        Body body = new Planet( position, 10000000, 100);
        quadtreeService.insertBody(body);
        List<Body> result = quadtreeService.queryNearbyBodies(body);
        assertTrue(result.contains(body), "The body should be found in the quadtree");
    }

    @Test
    void testQueryNoNearbyBodies() {
        Vector2D position1 = new Vector2D(50, 50);
        BoundingBox bbox1 = new BoundingBox(position1, 10);
        Body body1 = new Planet( position1, 10000000, 10);
        body1.setBbox(bbox1);
        quadtreeService.insertBody(body1);
        Vector2D position2 = new Vector2D(400, 400);
        BoundingBox bbox2 = new BoundingBox(position2, 10);
        Body body2 = new Planet( position2, 500000, 10);
        body2.setBbox(bbox2);
        List<Body> result = quadtreeService.queryNearbyBodies(body2);
        assertFalse(result.contains(body1), "body1 should not be found near body2");
    }

    @Test
    void testClearQuadtree() {
        Vector2D position = new Vector2D(50, 50);
        BoundingBox bbox = new BoundingBox(position, 10);
        Body body = new Planet( position, 100000, 10);
        body.setBbox(bbox);
        quadtreeService.insertBody(body);
        quadtreeService.clear();
        List<Body> result = quadtreeService.queryNearbyBodies(body);
        assertTrue(result.isEmpty(), "The quadtree should be empty after clear");
    }
}
