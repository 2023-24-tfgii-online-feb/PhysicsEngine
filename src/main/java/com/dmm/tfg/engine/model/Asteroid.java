package com.dmm.tfg.engine.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;

@Getter
@Setter
public class Asteroid extends Body{

    private float radius;
    private List<Vector2D> vertices; // New attribute
    private float spin; // New attribute


    public Asteroid(Vector2D position, Vector2D velocity, float mass, float radius) {
        super(position, velocity, new Vector2D(), mass);
        this.radius = radius;
        this.bodyType = BodyType.ASTEROID;
        this.vertices = generateRandomVertices(radius);
        this.checkSizeConstraints();
        this.setBbox(new BoundingBox(this.getPosition(), radius));
        this.spin = new Random().nextFloat(1,5);
    }

    @Override
    public void update() {
        super.update();
        this.computeVertices();
    }

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


    public void computeVertices() {
        double anglePerTickRadians = Math.toRadians(this.spin * (1.0 / 1000)); // Convert to radians
        for (Vector2D vertex : this.vertices) {  // Changed Vertex to Vector2D for consistency
            double newX = vertex.getX() * Math.cos(anglePerTickRadians) - vertex.getY() * Math.sin(anglePerTickRadians);
            double newY = vertex.getX() * Math.sin(anglePerTickRadians) + vertex.getY() * Math.cos(anglePerTickRadians);
            vertex.setX(newX);
            vertex.setY(newY);
        }
    }
    @Override
    protected void checkSizeConstraints(){
        Vector2D position = getPosition();
        if (position.getX() < radius){
            position.setX(position.getX() + radius);
        }
        if (position.getY() < radius){
            position.setY(position.getY() + radius);
        }

        if (position.getX() + radius > SPACE_WIDTH){
            position.setX(position.getX() - radius);
        }

        if (position.getY() + radius > SPACE_HEIGHT){
            position.setY(position.getY() - radius);
        }
    }

}
