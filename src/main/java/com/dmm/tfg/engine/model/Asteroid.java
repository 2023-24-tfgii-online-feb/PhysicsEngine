package com.dmm.tfg.engine.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents an asteroid in the physics simulation. Asteroids are dynamic bodies
 * with a defined radius, mass, and a randomly generated shape represented by vertices.
 * They can also spin around their center, simulating a more realistic asteroid behavior.
 */
@Getter
@Setter
public class Asteroid extends Body{

    private float radius;
    private List<Vector2D> vertices;
    private float spin;

    /**
     * Constructs an Asteroid with specified position, velocity, mass, and radius.
     * Initializes the asteroid with a random shape and sets its bounding box.
     * The spin rate is also randomly determined to add dynamism to the simulation.
     *
     * @param position The initial position of the asteroid.
     * @param velocity The initial velocity of the asteroid.
     * @param mass     The mass of the asteroid.
     * @param radius   The radius of the asteroid, used to generate its shape.
     */
    public Asteroid(Vector2D position, Vector2D velocity, float mass, float radius) {
        super(position, velocity, new Vector2D(), mass);
        this.radius = radius;
        this.bodyType = BodyType.ASTEROID;
        this.vertices = generateRandomVertices(radius);
        this.setBbox(new BoundingBox(this.getPosition(), radius));
        this.checkSizeConstraints();
        this.spin = new Random().nextFloat(1,5);
    }

    @Override
    public void update() {
        super.update();
        this.computeVertices();
    }

    /**
     * Generates a list of vertices to simulate an irregular shape of the asteroid.
     * The number of vertices and their distance from the center is randomized.
     *
     * @param radius The radius of the asteroid used as a base for vertex generation.
     * @return A list of {@link Vector2D} objects representing the vertices of the asteroid.
     */
    private List<Vector2D> generateRandomVertices(float radius) {
        List<Vector2D> generatedVertices = new ArrayList<>();
        Random random = new Random();
        int numVertices = random.nextInt(8) + 3; // Generate between 3 to 10 vertices

        for (int i = 0; i < numVertices; i++) {
            // Randomize radius a little to get an uneven shape
            double r = radius * (0.8 + 0.4 * random.nextDouble());
            double theta = 2 * Math.PI * i / numVertices;

            double x = r * Math.cos(theta);
            double y = r * Math.sin(theta);

            generatedVertices.add(new Vector2D(x, y));
        }

        return generatedVertices;
    }


    /**
     * Computes the new positions of the asteroid's vertices based on its spin.
     * This simulates the asteroid rotating around its center.
     */
    public void computeVertices() {
        double anglePerTickRadians = Math.toRadians(this.spin * (1.0 / 10));
        for (Vector2D vertex : this.vertices) {
            double newX = vertex.getX() * Math.cos(anglePerTickRadians) - vertex.getY() * Math.sin(anglePerTickRadians);
            double newY = vertex.getX() * Math.sin(anglePerTickRadians) + vertex.getY() * Math.cos(anglePerTickRadians);
            vertex.setX(newX);
            vertex.setY(newY);
        }
    }
}
