package com.dmm.tfg.service;

import com.dmm.tfg.model.Body;
import com.dmm.tfg.model.Vector2D;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ThreadLocalRandom;


@Service
@RequiredArgsConstructor
public class BodyService {

    private final DataService dataService;

    public void addRandomBody() {
       dataService.addBody(dataService.genRandomBody());
    }

    public void updateBodies(){
        dataService.updateBodies();
    }

    public List<Body> retrieveBodies(){
        return dataService.getAllBodies();
    }

}
