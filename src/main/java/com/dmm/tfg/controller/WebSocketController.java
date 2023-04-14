package com.dmm.tfg.controller;

import com.dmm.tfg.model.Body;
import com.dmm.tfg.service.BodyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
