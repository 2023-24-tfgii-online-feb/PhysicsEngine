package com.dmm.tfg.service;

import com.dmm.tfg.model.Body;
import com.dmm.tfg.model.Vector2D;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class BodyService {

    private final ConcurrentHashMap<Long, Body> bodies;
    private final AtomicLong idCounter;

    public BodyService() {
        bodies = new ConcurrentHashMap<>();
        idCounter = new AtomicLong();
    }

    public Body createBody(Body body) {
        long newId = idCounter.incrementAndGet();
        //body.setId(newId);
        bodies.put(newId, body);
        return body;
    }

    public Body getBody(Long id) {
        return bodies.get(id);
    }

    public Body updateBody(Long id, Body body) {
        //body.setId(id);
        bodies.put(id, body);
        return body;
    }

    public void deleteBody(Long id) {
        bodies.remove(id);
    }

    public Body getRandomBody() {
        return generateRandomBody();
    }

    private Body generateRandomBody() {
        // Generate random position
        float posX = ThreadLocalRandom.current().nextFloat() * 800; // Assuming canvas width is 800
        float posY = ThreadLocalRandom.current().nextFloat() * 600; // Assuming canvas height is 600
        Vector2D position = new Vector2D(posX, posY);

        // Generate random velocity
        float velX = ThreadLocalRandom.current().nextFloat() * 2 - 1; // Random float between -1 and 1
        float velY = ThreadLocalRandom.current().nextFloat() * 2 - 1; // Random float between -1 and 1
        Vector2D velocity = new Vector2D(velX, velY);

        // Generate random mass
        float mass = ThreadLocalRandom.current().nextFloat() * 10 + 1; // Random float between 1 and 11

        // Generate random radius
        float radius = ThreadLocalRandom.current().nextFloat() * 30 + 5; // Random float between 5 and 35

        return new Body(position, velocity, mass, radius);
    }

    public Body updateBody(Body body) {
        // Update the body's position based on its velocity
        body.getPosition().add(body.getVelocity());
        return body;
    }

    public List<Body> updateBodies() {
        List<Body> updatedBodies = new ArrayList<>();
        for (Body body : bodies.values()) {
            // Update the body's position based on its velocity
            body.getPosition().add(body.getVelocity());
            updatedBodies.add(body);
        }
        return updatedBodies;
    }

    public void addRandomBody() {
        Body randomBody = getRandomBody();
        long randomKey = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        bodies.put(randomKey, randomBody);
    }

    public List<Body> getAllBodies() {
        return new ArrayList<>(bodies.values());
    }
}
