package test.shapes;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import modules.shapes.*;
import processing.core.PVector;


public class CircleTests {
    @Test
    public void testGCircle() {

        // Given
        PVector center = new PVector(0,0);
        float radius = 1;

        Circle circle = new Circle(center, radius);
        double area = circle.area();
        double approximateCircleArea = 3.141592653589793;

        // When

        // Then
        
        assertEquals(approximateCircleArea, area,0.0000000001);
    }
}