package com.dmm.tfg.engine.dao;

import com.dmm.tfg.engine.model.*;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class BodyDAOImpl implements BodyDAO {
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

    public Body randomBody() {
        // Generate random position
        Vector2D position = genRandomPos();
        // Generate random velocity
        Vector2D velocity = genRandomVel();
        // Generate random mass
        float mass = ThreadLocalRandom.current().nextFloat() * 10 + 1; // Random float between 1 and 11
        // Generate random radius
        float radius = ThreadLocalRandom.current().nextFloat() * 30 + 5; // Random float between 5 and 35
        // Choose a random body type
        BodyType bodyType = BodyType.values()[ThreadLocalRandom.current().nextInt(3)];
        return switch (bodyType) {
            case PLANET -> new Planet(position, mass, radius);
            case ASTEROID -> new Asteroid(position, velocity, mass, radius);
            case SPACESHIP -> new Spaceship(position, velocity, mass);
        };
    }

    public Body randomPlanet() {

        Vector2D position = genRandomPos();
        // Generate random mass
        float mass = ThreadLocalRandom.current().nextFloat() * 1000 + 1;
        // Generate random radius
        float radius = ThreadLocalRandom.current().nextFloat() * 30 + 5; // Random float between 5 and 35
        return new Planet(position, mass, radius);

    }

    public Body randomAsteroid() {

        Vector2D position = genRandomPos();
        Vector2D velocity = genRandomVel();
        // Generate random mass
        float mass = ThreadLocalRandom.current().nextFloat() * 10 + 1; // Random float between 1 and 11
        // Generate random radius
        float radius = ThreadLocalRandom.current().nextFloat() * 30 + 5; // Random float between 5 and 35
        return new Asteroid(position, velocity, mass, radius);

    }

    public Spaceship randomSpaceship() {
        Vector2D position = genRandomPos();
        Vector2D velocity = genRandomVel();
        float mass = ThreadLocalRandom.current().nextFloat() * 0.1f + 1; // Random float between 0.1 and 1
        return new Spaceship(position, velocity, mass);
    }

    private Vector2D genRandomPos(){
        // Generate random position
        float posX = ThreadLocalRandom.current().nextFloat() * 800; // Assuming canvas width is 800
        float posY = ThreadLocalRandom.current().nextFloat() * 600; // Assuming canvas height is 600
        return new Vector2D(posX, posY);
    }

    private Vector2D genRandomVel(){
        // Generate random velocity
        float velX = ThreadLocalRandom.current().nextFloat() * 2 - 1; // Random float between -1 and 1
        float velY = ThreadLocalRandom.current().nextFloat() * 2 - 1; // Random float between -1 and 1
        return new Vector2D(velX, velY);
    }


}
