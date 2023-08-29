package com.dmm.tfg.engine;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import com.dmm.tfg.engine.model.Body;

import java.util.List;

import static com.dmm.tfg.PhysicsEngine.SPACE_HEIGHT;
import static com.dmm.tfg.PhysicsEngine.SPACE_WIDTH;

@Component
@NoArgsConstructor
public class CollisionResolver {

    public void checkEdges(List<Body> bodies){
        for (Body b : bodies){
            if (b.getPosition().getX()  > SPACE_WIDTH || b.getPosition().getX() < 0) {
                b.getVelocity().setX(b.getVelocity().getX() * -1);
            }
            if (b.getPosition().getY() > SPACE_HEIGHT || b.getPosition().getY() < 0) {
                b.getVelocity().setY(b.getVelocity().getY() * -1);
            }
        }
    }

}
