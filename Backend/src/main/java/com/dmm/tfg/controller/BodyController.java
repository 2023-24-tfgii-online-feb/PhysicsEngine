package com.dmm.tfg.controller;

import com.dmm.tfg.model.Body;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class BodyController {

    @MessageMapping("/randomBody")
    @SendTo("/topic/randomBody")
    public Body getRandomBody() {
        // Generate a random Body object
        Body randomBody = new Body();
        // Set body properties (e.g., position, size, color) with random values
        // ...

        return randomBody;
    }

}
