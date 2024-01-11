package com.dmm.tfg.service;

import com.dmm.tfg.engine.model.Asteroid;
import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Planet;
import com.dmm.tfg.engine.model.Spaceship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BodyServiceTest {

    @Mock
    private DataService dataService;

    private BodyService bodyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bodyService = new BodyService(dataService);
    }

    @Test
    void addRandomBodyTest() {
        Body randomBody = mock(Body.class);
        when(dataService.genRandomBody()).thenReturn(randomBody);

        bodyService.addRandomBody();

        verify(dataService).addBody(randomBody);
    }

    @Test
    void updateBodiesTest() {
        bodyService.updateBodies();

        verify(dataService).updateBodies();
    }

    @Test
    void removeBodyTest() {
        long id = 1L;
        bodyService.removeBody(id);

        verify(dataService).removeBody(id);
    }

    @Test
    void retrieveBodiesTest() {
        List<Body> expectedBodies = Collections.singletonList(mock(Body.class));
        when(dataService.getAllBodies()).thenReturn(expectedBodies);

        List<Body> actualBodies = bodyService.retrieveBodies();

        assertEquals(expectedBodies, actualBodies);
        verify(dataService).getAllBodies();
    }

    @Test
    void addRandomPlanetTest() {
        Body randomPlanet = mock(Planet.class);
        when(dataService.genRandomPlanet()).thenReturn((Planet) randomPlanet);

        bodyService.addRandomPlanet();

        verify(dataService).addBody(randomPlanet);
    }

    @Test
    void addRandomAsteroidTest() {
        Body randomAsteroid = mock(Asteroid.class);
        when(dataService.genRandomAsteroid()).thenReturn((Asteroid) randomAsteroid);

        bodyService.addRandomAsteroid();

        verify(dataService).addBody(randomAsteroid);
    }

    @Test
    void addRandomSpaceshipTest() {
        Spaceship randomSpaceship = mock(Spaceship.class);
        when(dataService.genRandomSpaceship()).thenReturn(randomSpaceship);

        bodyService.addRandomSpaceship();

        verify(dataService).addBody(randomSpaceship);
    }

    @Test
    void toggleSelectBodyTest() {
        long id = 1L;
        Body body = mock(Body.class);
        when(dataService.getBody(id)).thenReturn(body);

        bodyService.toggleSelectBody(id);

        verify(body).setSelected(!body.isSelected());
        verify(dataService).getBody(id);
    }

    @Test
    void getSelectedSpaceshipsTest() {
        Spaceship selectedSpaceship = mock(Spaceship.class);
        when(selectedSpaceship.isSelected()).thenReturn(true);

        Body unselectedBody = mock(Body.class);
        when(unselectedBody.isSelected()).thenReturn(false);

        when(dataService.getAllBodies()).thenReturn(Arrays.asList(selectedSpaceship, unselectedBody));

        List<Spaceship> result = bodyService.getSelectedSpaceships();

        assertTrue(result.contains(selectedSpaceship));
        assertFalse(result.contains(unselectedBody));
    }
}
