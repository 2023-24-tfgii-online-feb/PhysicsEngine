package com.dmm.tfg.service;

import com.dmm.tfg.engine.dao.BodyDAOImpl;
import com.dmm.tfg.engine.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class DataServiceTest {

    @Mock
    private BodyDAOImpl bodyDAO;

    @InjectMocks
    private DataService dataService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBody() {
        Body testBody = new Planet(new Vector2D(10, 10), 100000, 20);
        dataService.addBody(testBody);
        verify(bodyDAO).addBody(testBody);
    }

    @Test
    void testRemoveBody() {
        long testId = 1L;
        dataService.removeBody(testId);
        verify(bodyDAO).removeBody(testId);
    }

    @Test
    void testGetBody() {
        long testId = 1L;
        Body expectedBody = new Asteroid(new Vector2D(20, 20), new Vector2D(1, 1), 50000, 10);
        when(bodyDAO.getBody(testId)).thenReturn(expectedBody);

        Body actualBody = dataService.getBody(testId);
        assertEquals(expectedBody, actualBody);
    }

    @Test
    void testGenRandomBody() {
        Body expectedBody = new Spaceship(new Vector2D(30, 30), new Vector2D(1, 1), 1);
        when(bodyDAO.randomBody()).thenReturn(expectedBody);

        Body actualBody = dataService.genRandomBody();
        assertEquals(expectedBody, actualBody);
    }

    @Test
    void testGenRandomPlanet() {
        Planet expectedPlanet = new Planet(new Vector2D(40, 40), 80000, 15);
        when(bodyDAO.randomPlanet()).thenReturn(expectedPlanet);

        Planet actualPlanet = dataService.genRandomPlanet();
        assertEquals(expectedPlanet, actualPlanet);
    }

    @Test
    void testGenRandomAsteroid() {
        when(bodyDAO.randomAsteroid()).thenReturn(mock(Asteroid.class));
        assertNotNull(dataService.genRandomAsteroid());
    }

    @Test
    void testGenRandomSpaceship() {
        when(bodyDAO.randomSpaceship()).thenReturn(mock(Spaceship.class));
        assertNotNull(dataService.genRandomSpaceship());
    }

    @Test
    void testUpdateBodies() {
        dataService.updateBodies();
        verify(bodyDAO).updateBodies();
    }

    @Test
    void testGetAllBodies() {
        List<Body> expectedBodies = new ArrayList<>();
        when(bodyDAO.getAllBodies()).thenReturn(expectedBodies);
        assertEquals(expectedBodies, dataService.getAllBodies());
    }

}

