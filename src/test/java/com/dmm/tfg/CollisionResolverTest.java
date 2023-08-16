package com.dmm.tfg;

import com.dmm.tfg.engine.CollisionResolver;
import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.engine.model.Vector2D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CollisionResolverTest {

    @InjectMocks
    private CollisionResolver collisionResolver;

    @Test
    public void checkEdgesTest() {
        Body body1 = mock(Body.class);
        Vector2D pos1 = new Vector2D(200, 200);
        when(body1.getPosition()).thenReturn(pos1);

        Body body2 = mock(Body.class);
        Vector2D pos2 = new Vector2D(700, 700);
        when(body2.getPosition()).thenReturn(pos2);

        collisionResolver.checkEdges(Arrays.asList(body1, body2));

        // If more logic is added for collisions, you can add more verification steps.
    }
}

