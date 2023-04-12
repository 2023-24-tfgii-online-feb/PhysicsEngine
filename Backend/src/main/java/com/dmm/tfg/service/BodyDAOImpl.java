package com.dmm.tfg.service;

import com.dmm.tfg.model.Body;
import com.dmm.tfg.model.Vector2D;
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
    final AtomicLong idCounter = new AtomicLong();

    @Override
    public Body getBody(Long id) {
        return bodies.get(id);
    }

    @Override
    public void addBody(@NonNull Body body) {
        bodies.put(idCounter.getAndIncrement(), body);
    }

    @Override
    public List<Body> getAllBodies() {
        return new ArrayList<>(bodies.values());
    }

    @Override
    public void updateBodies() {
        for (Body body : bodies.values()){
            body.getPosition().add(body.getVelocity());
        }
    }

    @Override
    public void deleteBody(Long id) {
        bodies.remove(id);
    }

    public Body randomBody() {
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
}
