package com.dmm.tfg.service;

import com.dmm.tfg.model.Body;

import java.util.List;

public interface BodyDAO {
    Body getBody(Long id);
    void addBody(Body body);
    List<Body> getAllBodies();
    void updateBodies();
    void removeBody(Long id);
}
