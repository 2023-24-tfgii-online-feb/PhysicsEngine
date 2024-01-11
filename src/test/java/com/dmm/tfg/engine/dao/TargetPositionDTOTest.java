package com.dmm.tfg.engine.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TargetPositionDTOTest {

    @Test
    void testXSetterGetter() {
        TargetPositionDTO targetPositionDTO = new TargetPositionDTO();
        double expectedX = 10.5;
        targetPositionDTO.setX(expectedX);

        assertEquals(expectedX, targetPositionDTO.getX());
    }

    @Test
    void testYSetterGetter() {
        TargetPositionDTO targetPositionDTO = new TargetPositionDTO();
        double expectedY = 20.5;
        targetPositionDTO.setY(expectedY);

        assertEquals(expectedY, targetPositionDTO.getY());
    }
}
