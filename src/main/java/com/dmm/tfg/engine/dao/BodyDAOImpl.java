package com.dmm.tfg.engine.dao;

import com.dmm.tfg.engine.model.*;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;

/**
 * Implementation of the {@link BodyDAO} interface. Provides concrete data access operations to manage {@link Body} entities
 * using an in-memory data store with a {@link ConcurrentHashMap}.
 */
@Repository
public class BodyDAOImpl implements BodyDAO {

    Logger logger = LoggerFactory.getLogger(BodyDAOImpl.class);
    private final ConcurrentHashMap<Long, Body> bodies = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public Body getBody(Long id) {
        return bodies.get(id);
    }

    @Override
    public void addBody(@NonNull Body body) {
        body.setId(idCounter.get());
        bodies.put(idCounter.getAndIncrement(), body);
    }

    @Override
    public List<Body> getAllBodies() {
        return new ArrayList<>(bodies.values());
    }

    @Override
    public void updateBodies() {
        for (Body body : bodies.values()){
            body.update();
        }
    }
    @Override
    public void removeBody(Long id) {
        bodies.remove(id);
    }

    /**
     * Generates a random body instance. The type of body (PLANET, ASTEROID, or SPACESHIP)
     * is selected randomly, and its properties are initialized to random values within their
     * respective ranges.
     *
     * @return A new instance of a Body, with type and properties randomly determined.
     */
    public Body randomBody() {
        BodyType bodyType = BodyType.values()[ThreadLocalRandom.current().nextInt(3)];

        return switch (bodyType) {
            case PLANET -> randomPlanet();
            case ASTEROID -> randomAsteroid();
            case SPACESHIP -> randomSpaceship();
        };
    }

    /**
     * Generates a random planet with properties set to random values suitable for a planet.
     * The mass and radius are selected randomly within a range that is reasonable for planets.
     *
     * @return A new instance of a Planet with random properties.
     */
    public Body randomPlanet() {
        Vector2D position = genRandomPos();

        float max = 100000000;
        float min = 50000000;
        float range = max - min;

        float mass = ThreadLocalRandom.current().nextFloat() * range + min;
        float radius = ThreadLocalRandom.current().nextFloat() * 30 + 5;

        logger.info("Planet generated with this properties: Position:{}, mass:{}, radius:{}",
                position.toString(),mass, radius);
        return new Planet(position, mass, radius);
    }

    /**
     * Generates a random asteroid with properties set to random values suitable for an asteroid.
     * This includes mass, radius, and initial velocity, all chosen randomly within reasonable ranges.
     *
     * @return A new instance of an Asteroid with random properties.
     */
    public Body randomAsteroid() {
        Vector2D position = genRandomPos();
        Vector2D velocity = genRandomVel();

        float max = 100000;
        float min = 50000;
        float range = max - min;

        float mass = ThreadLocalRandom.current().nextFloat() * range + min;
        float radius = ThreadLocalRandom.current().nextFloat() * 30 + 5; // Random float between 5 and 35

        logger.info("Asteroid generated with this properties: Position:{}, velocity:{}, mass:{}, radius:{}",
                position.toString(), velocity.toString(), mass, radius);
        return new Asteroid(position, velocity, mass, radius);
    }

    /**
     * Generates a random spaceship with properties set to random values suitable for a spaceship.
     * The mass is chosen randomly, and initial velocity is set to ensure dynamic movement.
     *
     * @return A new instance of a Spaceship with random properties.
     */
    public Spaceship randomSpaceship() {
        Vector2D position = genRandomPos();
        Vector2D velocity = genRandomVel();
        float mass = ThreadLocalRandom.current().nextFloat() * 0.1f + 1; // Random float between 0.1 and 1

        logger.info("Spaceship generated with this properties: Position:{}, velocity:{}, mass:{}",
                position.toString(), velocity.toString(), mass);
        return new Spaceship(position, velocity, mass);
    }

    private Vector2D genRandomPos(){
        float posX = ThreadLocalRandom.current().nextFloat() * SPACE_WIDTH;
        float posY = ThreadLocalRandom.current().nextFloat() * SPACE_HEIGHT;
        return new Vector2D(posX, posY);
    }

    private Vector2D genRandomVel(){
        float velX = ThreadLocalRandom.current().nextFloat() * 2 - 1; // Random float between -1 and 1
        float velY = ThreadLocalRandom.current().nextFloat() * 2 - 1; // Random float between -1 and 1
        return new Vector2D(velX, velY);
    }


}
