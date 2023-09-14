package com.dmm.tfg.controller;

import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.service.BodyService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final BodyService bodyService;

    @MessageMapping("/random-body")
    @SendTo("/topic/bodies")
    public void addRandomBody() {
        bodyService.addRandomBody();
    }

    @MessageMapping("/random-planet")
    @SendTo("/topic/bodies")
    public void addRandomPlanet() {
        bodyService.addRandomPlanet();
    }

    @MessageMapping("/random-asteroid")
    @SendTo("/topic/bodies")
    public void addRandomAsteroid() {
        bodyService.addRandomAsteorid();
    }

    @MessageMapping("/add-body")
    @SendTo("/topic/bodies")
    public void addBody(){ bodyService.addBody();}


    @MessageMapping("/retrieve-bodies")
    @SendTo("/topic/bodies")
    public List<Body> retrieveBodies() {
        return bodyService.retrieveBodies();
    }

    @MessageMapping("/update-bodies")
    @SendTo("/topic/bodies")
    public void updateBodies() {
        bodyService.updateBodies();
    }

}
