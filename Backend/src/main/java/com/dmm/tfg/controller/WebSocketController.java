package com.dmm.tfg.controller;

import com.dmm.tfg.model.Body;
import com.dmm.tfg.service.BodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WebSocketController {

    private final BodyService bodyService;

    @Autowired
    public WebSocketController(BodyService bodyService) {
        this.bodyService = new BodyService();
    }

    @MessageMapping("/random-body")
    @SendTo("/topic/bodies")
    public void addRandomBody() {
        bodyService.addRandomBody();
    }


    @MessageMapping("/update-bodies")
    @SendTo("/topic/bodies")
    public List<Body> updateBodies() {
        return bodyService.updateBodies();
    }

}
