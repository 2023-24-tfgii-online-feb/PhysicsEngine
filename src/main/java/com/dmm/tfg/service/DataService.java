package com.dmm.tfg.service;

import com.dmm.tfg.model.Body;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {
    private final BodyDAOImpl bodyDAO;


    public void addBody(@NonNull Body body){
        bodyDAO.addBody(body);
    }
    public Body genRandomBody() {
        return bodyDAO.randomBody();
    }

    public void updateBodies() {
        bodyDAO.updateBodies();
    }

    public List<Body> getAllBodies() {
        return bodyDAO.getAllBodies();
    }
}