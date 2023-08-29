package com.dmm.tfg.engine;

import com.dmm.tfg.engine.model.Asteroid;
import com.dmm.tfg.engine.model.Planet;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import com.dmm.tfg.engine.model.Body;

import java.util.List;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;

@Component
@NoArgsConstructor
public class CollisionResolver {

    public void checkEdges(List<Body> bodies) {
        for (Body b : bodies) {
            int radius = 0;

            if (b instanceof Planet) {
                radius = (int) ((Planet) b).getRadius();
            }

            if (b instanceof Asteroid) {
                radius = (int) ((Asteroid) b).getRadius();
            }

            if (b.getPosition().getX() + radius > SPACE_WIDTH || b.getPosition().getX() - radius < 0) {
                b.getVelocity().setX(b.getVelocity().getX() * -1);
            }
            if (b.getPosition().getY() + radius > SPACE_HEIGHT || b.getPosition().getY() - radius < 0) {
                b.getVelocity().setY(b.getVelocity().getY() * -1);
            }
        }
    }

}
