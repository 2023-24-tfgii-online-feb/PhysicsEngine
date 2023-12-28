package com.dmm.tfg.controller;

import com.dmm.tfg.engine.dao.BodyIdDTO;
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

    @MessageMapping("/random-spaceship")
    @SendTo("/topic/bodies")
    public void addRandomSpaceship() {
        bodyService.addRandomSpaceship();
    }


    @MessageMapping("/add-body")
    public void addBody(){ bodyService.addBody();}


    @MessageMapping("/retrieve-bodies")
    @SendTo("/topic/bodies")
    public List<Body> retrieveBodies() {
        return bodyService.retrieveBodies();
    }

    @MessageMapping("/remove-body")
    public void removeBody(BodyIdDTO bodyIdDTO) {
        bodyService.removeBody(bodyIdDTO.getId());
    }

    @MessageMapping("/toggle-select-body")
    @SendTo("/topic/bodies")
    public void toggleSelectBody(BodyIdDTO bodyIdDTO) {
        bodyService.toggleSelectBody(bodyIdDTO.getId());
    }


    @MessageMapping("/update-bodies")
    public void updateBodies() {
        bodyService.updateBodies();
    }

}
