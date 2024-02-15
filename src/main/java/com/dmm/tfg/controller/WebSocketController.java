package com.dmm.tfg.controller;

import com.dmm.tfg.engine.dao.BodyIdDTO;
import com.dmm.tfg.engine.dao.TargetPositionDTO;
import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Spaceship;
import com.dmm.tfg.engine.model.Vector2D;
import com.dmm.tfg.service.BodyService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Controller for handling WebSocket messages related to body entities within the simulation.
 * Supports operations such as adding random bodies, retrieving all bodies, removing a body,
 * toggling the selection of a body, and moving selected spaceships towards a target position.
 */
@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final BodyService bodyService;

    /**
     * Adds a random body to the simulation and broadcasts the update.
     */
    @MessageMapping("/random-body")
    public void addRandomBody() {
        bodyService.addRandomBody();
    }

    /**
     * Adds a random planet to the simulation and broadcasts the update.
     */
    @MessageMapping("/random-planet")
    public void addRandomPlanet() {
        bodyService.addRandomPlanet();
    }

    /**
     * Adds a random asteroid to the simulation and broadcasts the update.
     */
    @MessageMapping("/random-asteroid")
    public void addRandomAsteroid() {
        bodyService.addRandomAsteroid();
    }

    /**
     * Adds a random spaceship to the simulation and broadcasts the update.
     */
    @MessageMapping("/random-spaceship")
    public void addRandomSpaceship() {
        bodyService.addRandomSpaceship();
    }

    /**
     * Retrieves all bodies from the simulation and broadcasts the list.
     *
     * @return A list of all bodies currently in the simulation.
     */
    @MessageMapping("/retrieve-bodies")
    @SendTo("/topic/bodies")
    public List<Body> retrieveBodies() {
        return bodyService.retrieveBodies();
    }

    /**
     * Removes a body from the simulation based on its ID.
     *
     * @param bodyIdDTO Data transfer object containing the ID of the body to remove.
     */
    @MessageMapping("/remove-body")
    public void removeBody(BodyIdDTO bodyIdDTO) {
        bodyService.removeBody(bodyIdDTO.getId());
    }

    /**
     * Toggles the selection state of a body based on its ID and broadcasts the update.
     *
     * @param bodyIdDTO Data transfer object containing the ID of the body to toggle.
     */
    @MessageMapping("/toggle-select-body")
    public void toggleSelectBody(BodyIdDTO bodyIdDTO) {
        bodyService.toggleSelectBody(bodyIdDTO.getId());
    }

    /**
     * Moves all selected spaceships towards a given target position.
     *
     * @param positionDTO Data transfer object containing the target position.
     */
    @MessageMapping("/move-selected-spaceships")
    public void moveSelectedSpaceships(TargetPositionDTO positionDTO) {
        List<Spaceship> selectedSpaceships = bodyService.getSelectedSpaceships();
        Vector2D target = new Vector2D(positionDTO.getX(), positionDTO.getY());
        for (Spaceship spaceship : selectedSpaceships) {
            spaceship.seek(target);
        }
    }

}
