package com.dmm.tfg;

import com.dmm.tfg.service.PhysicsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Main entry point for the Physics Engine application.
 * Configures and starts the Spring Boot application, setting up the physics
 * simulation environment including the scheduled execution of physics updates.
 */
@SpringBootApplication
public class PhysicsEngine {

    /**
     * The width of the simulation space in pixels. This constant defines the horizontal
     * dimension of the simulation area where bodies can move and interact.
     */
    public static final int SPACE_WIDTH = 1240;

    /**
     * The height of the simulation space in pixels. This constant defines the vertical
     * dimension of the simulation area, limiting the movement and interactions of bodies
     * within these bounds.
     */
    public static final int SPACE_HEIGHT = 720;

    /**
     * Starts the Spring Boot application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(PhysicsEngine.class, args);
    }

    /**
     * Configures a scheduled executor service to periodically update the physics
     * simulation at a fixed rate. Initializes the physics service and schedules
     * the physics tick updates.
     *
     * @param physicsService The physics service responsible for managing the simulation's physics.
     * @return A configured ScheduledExecutorService that manages the periodic physics updates.
     */
    @Bean
    public ScheduledExecutorService
    scheduledExecutorService(PhysicsService physicsService) {
        physicsService.setup();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        int tickRate = 150;
        executor.scheduleAtFixedRate(physicsService::tick, 0, 1000 / tickRate, TimeUnit.MILLISECONDS);
        return executor;
    }
}
