package com.dmm.tfg.service;

import com.dmm.tfg.engine.AttractionResolver;
import com.dmm.tfg.engine.CollisionResolver;
import com.dmm.tfg.engine.MovementResolver;
import com.dmm.tfg.engine.model.Asteroid;
import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Planet;
import com.dmm.tfg.engine.model.Spaceship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.mockito.Mockito.*;

class PhysicsServiceTest {

    @Mock
    private DataService dataService;
    @Mock
    private AttractionResolver attractionResolver;
    @Mock
    private CollisionResolver collisionResolver;
    @Mock
    private MovementResolver movementResolver;
    @Mock
    private QuadtreeService quadtreeService;

    private PhysicsService physicsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        physicsService = new PhysicsService(dataService, attractionResolver, collisionResolver, movementResolver, quadtreeService);
    }

    @Test
    void setupTest() {
        Spaceship mockSpaceship = mock(Spaceship.class);
        when(dataService.genRandomSpaceship()).thenReturn(mockSpaceship);
        physicsService.setup();
        verify(dataService, times(1)).addBody(any(Planet.class));
        verify(dataService, times(1)).addBody(any(Asteroid.class));
        verify(dataService, times(1)).addBody(mockSpaceship);
    }


    @Test
    void tickTest() {
        Body testBody = mock(Body.class);
        when(dataService.getAllBodies()).thenReturn(Collections.singletonList(testBody));

        physicsService.tick();

        verify(quadtreeService).clear();
        verify(quadtreeService).insertBody(testBody);
        verify(collisionResolver).checkEdges(Collections.singletonList(testBody));
        verify(attractionResolver).calculateAttractions(Collections.singletonList(testBody), quadtreeService);
        verify(movementResolver).resolveSpaceshipMovement(Collections.singletonList(testBody), quadtreeService);
        verify(collisionResolver).checkCollisions(Collections.singletonList(testBody), quadtreeService);
        verify(dataService).updateBodies();
    }
}
