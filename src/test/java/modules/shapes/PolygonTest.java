package modules.shapes;

import org.junit.jupiter.api.Test;
import processing.core.PVector;
import modules.shapes.Polygon;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolygonTest {

    @Test
    public void shouldCreatePolygonWithFourVerticesWhenInputIsFour(){
        // given
        ArrayList<PVector> vertices = new ArrayList<PVector>();
        vertices.add(new PVector(100,100));
        vertices.add(new PVector(400,100));
        vertices.add(new PVector(100,400));
        vertices.add(new PVector(400,400));
        Polygon polygon = new Polygon(vertices);

        // when
        int result = polygon.getN();

        // then
        assertEquals(4,result);
    }


}