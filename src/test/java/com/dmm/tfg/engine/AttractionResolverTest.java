package com.dmm.tfg.engine;

import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Vector2D;
import com.dmm.tfg.service.QuadtreeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class AttractionResolverTest {

    private AttractionResolver attractionResolver;
    private QuadtreeService quadtreeService;

    @BeforeEach
    void setUp() {
        attractionResolver = new AttractionResolver();
        quadtreeService = Mockito.mock(QuadtreeService.class);
    }

    private Body createMockBody(double x, double y, double mass) {
        Body body = mock(Body.class);
        when(body.getPosition()).thenReturn(new Vector2D(x, y));
        when(body.getMass()).thenReturn(mass);
        return body;
    }

    @Test
    void calculateAttractionsWithNoBodies() {
        assertDoesNotThrow(() -> attractionResolver.calculateAttractions(Collections.emptyList(), quadtreeService));
    }

    @Test
    void calculateAttractionsWithOneBody() {
        Body singleBody = createMockBody(0,0,100);
        assertDoesNotThrow(() -> attractionResolver.calculateAttractions(Collections.singletonList(singleBody), quadtreeService));
    }

    @Test
    void calculateAttractionsWithNearbyBodies() {
        // Creating two mock bodies
        Body body1 = createMockBody(0, 0, 10.0);
        Body body2 = createMockBody(1, 1, 20.0);

        // Mocking QuadtreeService to return the other body as nearby
        when(quadtreeService.queryNearbyBodies(body1)).thenReturn(List.of(body2));
        when(quadtreeService.queryNearbyBodies(body2)).thenReturn(List.of(body1));

        // Call calculateAttractions with both bodies
        attractionResolver.calculateAttractions(Arrays.asList(body1, body2), quadtreeService);

        // Verify that applyForce was called on each body with the gravitational pull from the other body
        verify(body1).applyForce(any(Vector2D.class));
        verify(body2).applyForce(any(Vector2D.class));
    }
}

