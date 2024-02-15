package com.dmm.tfg.engine.dao;

import lombok.Getter;

/**
 * Data Transfer Object for carrying the unique identifier of a Body entity.
 * This class is used to encapsulate the ID attribute for easy transfer over the network or application layers.
 */
@Getter
public class BodyIdDTO {
    private long id;

    /**
     * Sets the unique identifier for the Body entity.
     *
     * @param id The unique identifier to be set for the Body.
     */
    public void setId(long id) {
        this.id = id;
    }
}
