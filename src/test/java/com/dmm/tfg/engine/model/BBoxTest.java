package com.dmm.tfg.engine.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BBoxTest {


    @Test
    void testNonIntersectingBoxes() {
        BoundingBox box1 = new BoundingBox(new Vector2D(0, 0), 10);
        BoundingBox box2 = new BoundingBox(new Vector2D(30, 30), 10);
        assertFalse(box1.intersects(box2), "Boxes should not intersect");
    }

    @Test
    void testIntersectingBoxes() {
        BoundingBox box1 = new BoundingBox(new Vector2D(0, 0), 15);
        BoundingBox box2 = new BoundingBox(new Vector2D(10, 10), 10);
        assertTrue(box1.intersects(box2), "Boxes should intersect");
    }

    @Test
    void testBorderlineCase() {
        BoundingBox box1 = new BoundingBox(new Vector2D(0, 0), 10);
        BoundingBox box2 = new BoundingBox(new Vector2D(20, 0), 10);
        assertTrue(box1.intersects(box2), "Boxes should just touch and therefore intersect");
    }
    
}
