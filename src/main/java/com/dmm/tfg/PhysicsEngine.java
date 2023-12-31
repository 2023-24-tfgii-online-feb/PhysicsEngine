package com.dmm.tfg;

import com.dmm.tfg.service.PhysicsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class PhysicsEngine {

    public static final int SPACE_WIDTH = 1240;
    public static final int SPACE_HEIGHT = 720;

    public static void main(String[] args) {
        SpringApplication.run(PhysicsEngine.class, args);
    }

    @Bean
    public ScheduledExecutorService
    scheduledExecutorService(PhysicsService physicsService) {
        physicsService.setup();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        int tickRate = 150; // 100Hz tick rate
        executor.scheduleAtFixedRate(physicsService::tick, 0, 1000 / tickRate, TimeUnit.MILLISECONDS);
        return executor;
    }
}
