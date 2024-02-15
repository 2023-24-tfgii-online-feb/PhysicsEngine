package com.dmm.tfg.service;

import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.BoundingBox;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.index.quadtree.Quadtree;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that utilizes a Quadtree for efficient querying of spatial data.
 * It is designed to manage and optimize the querying of bodies within a 2D space,
 * facilitating fast access to bodies based on their spatial location and bounding box information.
 */
@Service
public class QuadtreeService {

    private Quadtree quadtree = new Quadtree();

    /**
     * Inserts a body into the Quadtree. This is achieved by converting the body's
     * bounding box into an envelope and using it as the spatial key for insertion.
     *
     * @param body The body to be inserted into the Quadtree.
     */
    public void insertBody(Body body) {
        Envelope envelope = createEnvelopeFromBoundingBox(body.getBbox());
        quadtree.insert(envelope, body);
    }

    /**
     * Queries the Quadtree for bodies that are near the specified body, based on
     * the bounding box of the input body. This method is useful for detecting potential
     * collisions or interactions between bodies.
     *
     * @param body The body whose nearby bodies are to be queried.
     * @return A list of bodies that are within the bounding box of the specified body.
     */
    public List<Body> queryNearbyBodies(Body body) {
        Envelope queryEnvelope = createEnvelopeFromBoundingBox(body.getBbox());
        return quadtree.query(queryEnvelope);
    }

    /**
     * Creates an Envelope from a given BoundingBox. This envelope represents the
     * spatial extent of the bounding box and is used as a key for Quadtree operations.
     *
     * @param bbox The BoundingBox to convert into an Envelope.
     * @return The Envelope created from the BoundingBox.
     */
    private Envelope createEnvelopeFromBoundingBox(BoundingBox bbox) {
        double minX = bbox.getCenter().getX() - bbox.getRadius();
        double minY = bbox.getCenter().getY() - bbox.getRadius();
        double maxX = bbox.getCenter().getX() + bbox.getRadius();
        double maxY = bbox.getCenter().getY() + bbox.getRadius();
        return new Envelope(minX, maxX, minY, maxY);
    }

    /**
     * Clears the Quadtree of all entries, effectively resetting it. This is useful
     * for starting a new simulation or clearing the current state for a new set of operations.
     */
    public void clear() {
        quadtree = new Quadtree();
    }
}
