package com.dmm.tfg.engine;

import com.dmm.tfg.engine.model.Spaceship;
import com.dmm.tfg.service.QuadtreeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.mockito.Mockito.*;

class MovementResolverTest {

    private MovementResolver movementResolver;

    @Mock
    private QuadtreeService quadtreeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movementResolver = new MovementResolver();
    }

    @Test
    void testResolveSpaceshipMovement() {
        Spaceship mockSpaceship = mock(Spaceship.class);
        when(mockSpaceship.getId()).thenReturn(1L);
        when(quadtreeService.queryNearbyBodies(mockSpaceship)).thenReturn(Collections.emptyList());
        movementResolver.resolveSpaceshipMovement(Collections.singletonList(mockSpaceship), quadtreeService);
        verify(mockSpaceship).wander();
        verify(mockSpaceship).avoidCollisions(Collections.emptyList());
        verify(mockSpaceship).applyDamping();
        verify(mockSpaceship).capVelocity();
        verifyNoMoreInteractions(mockSpaceship);
    }
}
