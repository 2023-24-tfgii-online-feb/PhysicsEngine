package com.dmm.tfg.engine.dao;

import com.dmm.tfg.engine.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BodyDAOImplTest {

    private BodyDAOImpl bodyDAO;

    @BeforeEach
    void setUp() {
        bodyDAO = new BodyDAOImpl();
    }

    @Test
    void addAndGetBodyTest() {
        Body body = new Planet(new Vector2D(10, 10), 100000, 50);
        bodyDAO.addBody(body);
        Body retrievedBody = bodyDAO.getBody(body.getId());

        assertEquals(body, retrievedBody);
    }

    @Test
    void getAllBodiesTest() {
        Body body1 = new Planet(new Vector2D(10, 10), 100000, 50);
        Body body2 = new Asteroid(new Vector2D(20, 20), new Vector2D(1, 1), 50000, 30);
        bodyDAO.addBody(body1);
        bodyDAO.addBody(body2);

        List<Body> bodies = bodyDAO.getAllBodies();
        assertTrue(bodies.contains(body1));
        assertTrue(bodies.contains(body2));
    }

    @Test
    void updateBodiesTest() {
        Body body = new Planet(new Vector2D(10, 10), 100000, 50);
        bodyDAO.addBody(body);
        bodyDAO.updateBodies();
        Body updatedBody = bodyDAO.getBody(body.getId());

        assertNotNull(updatedBody);
    }

    @Test
    void removeBodyTest() {
        Body body = new Planet(new Vector2D(10, 10), 100000, 50);
        bodyDAO.addBody(body);
        bodyDAO.removeBody(body.getId());
        Body retrievedBody = bodyDAO.getBody(body.getId());

        assertNull(retrievedBody);
    }

    @Test
    void randomPlanetTest() {
        Body planet = bodyDAO.randomPlanet();
        assertNotNull(planet);
        assertTrue(planet instanceof Planet);
    }

    @Test
    void randomAsteroidTest() {
        Body asteroid = bodyDAO.randomAsteroid();
        assertNotNull(asteroid);
        assertTrue(asteroid instanceof Asteroid);
    }

    @Test
    void randomSpaceshipTest() {
        Body spaceship = bodyDAO.randomSpaceship();
        assertNotNull(spaceship);
        assertTrue(spaceship instanceof Spaceship);
    }

    @Test
    void randomBodyTest() {
        Body body = bodyDAO.randomBody();
        assertNotNull(body);
        assertTrue(body instanceof Planet || body instanceof Asteroid || body instanceof Spaceship);
    }
}

