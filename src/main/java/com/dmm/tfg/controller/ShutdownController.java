package com.dmm.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShutdownController {

    private final ConfigurableApplicationContext context;

    public ShutdownController(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @PostMapping("/shutdown")
    public void shutdown() {
        context.close();
    }
}