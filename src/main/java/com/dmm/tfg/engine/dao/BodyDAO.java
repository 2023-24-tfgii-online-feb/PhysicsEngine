package com.dmm.tfg.engine.dao;

import com.dmm.tfg.engine.model.Body;

import java.util.List;

public interface BodyDAO {
    Body getBody(Long id);
    void addBody(Body body);
    List<Body> getAllBodies();
    void updateBodies();
    void removeBody(Long id);
}
