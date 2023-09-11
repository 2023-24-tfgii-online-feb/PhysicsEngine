package com.dmm.tfg.service;

import org.locationtech.jts.index.quadtree.Quadtree;
import org.locationtech.jts.geom.Envelope;
import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.BoundingBox;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QuadtreeService {

    private Quadtree quadtree = new Quadtree();

    public void insertBody(Body body) {
        Envelope envelope = createEnvelopeFromBoundingBox(body.getBbox());
        quadtree.insert(envelope, body);
    }

    public List<Body> queryNearbyBodies(Body body) {
        Envelope queryEnvelope = createEnvelopeFromBoundingBox(body.getBbox());
        return quadtree.query(queryEnvelope);
    }

    private Envelope createEnvelopeFromBoundingBox(BoundingBox bbox) {
        double minX = bbox.getCenter().getX() - bbox.getRadius();
        double minY = bbox.getCenter().getY() - bbox.getRadius();
        double maxX = bbox.getCenter().getX() + bbox.getRadius();
        double maxY = bbox.getCenter().getY() + bbox.getRadius();
        return new Envelope(minX, maxX, minY, maxY);
    }

    public void clear() {
        quadtree = new Quadtree();
    }
}
