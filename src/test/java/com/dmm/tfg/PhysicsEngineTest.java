package com.dmm.tfg;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.ScheduledExecutorService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PhysicsEngineTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertNotNull(context);
    }

    @Test
    void scheduledExecutorServiceBeanExists() {
        ScheduledExecutorService scheduledExecutorService = context.getBean(ScheduledExecutorService.class);
        assertNotNull(scheduledExecutorService);
    }
}
