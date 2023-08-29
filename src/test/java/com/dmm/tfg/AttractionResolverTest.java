package com.dmm.tfg;

import com.dmm.tfg.engine.AttractionResolver;
import com.dmm.tfg.engine.model.Body;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AttractionResolverTest {

    @InjectMocks
    private AttractionResolver attractionResolver;

    @Test
    public void calculateAttractionsTest() {
        Body body1 = mock(Body.class);
        Body body2 = mock(Body.class);

        attractionResolver.calculateAttractions(Arrays.asList(body1, body2));

        // We expect two calls to applyForce since both bodies will influence each other.
        verify(body1, times(2)).applyForce(any());
    }
}