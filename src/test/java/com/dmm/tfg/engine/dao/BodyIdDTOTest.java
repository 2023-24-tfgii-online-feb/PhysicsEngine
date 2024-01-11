package com.dmm.tfg.engine.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BodyIdDTOTest {

    @Test
    void testIdSetterGetter() {
        BodyIdDTO bodyIdDTO = new BodyIdDTO();
        long expectedId = 123L;
        bodyIdDTO.setId(expectedId);

        assertEquals(expectedId, bodyIdDTO.getId());
    }
}
