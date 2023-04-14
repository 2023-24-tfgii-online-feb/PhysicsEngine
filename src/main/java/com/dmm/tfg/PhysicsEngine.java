package com.dmm.tfg;

import com.dmm.tfg.controller.WebSocketController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhysicsEngine {

    public static final int SPACE_WIDTH = 800;
    public static final int SPACE_HEIGHT = 600;


    public static void main(String[] args) {
        SpringApplication.run(PhysicsEngine.class, args);
    }
}
