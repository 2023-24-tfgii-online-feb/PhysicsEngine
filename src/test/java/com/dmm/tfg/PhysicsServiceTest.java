package com.dmm.tfg;

import com.dmm.tfg.engine.AttractionResolver;
import com.dmm.tfg.engine.CollisionResolver;
import com.dmm.tfg.engine.model.Body;
import com.dmm.tfg.service.DataService;
import com.dmm.tfg.service.PhysicsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PhysicsServiceTest {

    @Mock
    private DataService dataService;

    @Mock
    private AttractionResolver attractionResolver;

    @Mock
    private CollisionResolver collisionResolver;

    @InjectMocks
    private PhysicsService physicsService;

    @Test
    public void tickTest() {
        Body body1 = mock(Body.class);
        Body body2 = mock(Body.class);

        when(dataService.getAllBodies()).thenReturn(Arrays.asList(body1, body2));

        physicsService.tick();

        verify(collisionResolver, times(1)).checkEdges(anyList());
        verify(attractionResolver, times(1)).calculateAttractions(anyList());
        verify(dataService, times(1)).updateBodies();
    }
}