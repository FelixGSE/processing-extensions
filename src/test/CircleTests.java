package test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import shapes.*;


public class CircleTests {
    @Test
    public void testGCircle() {

        // Given
        var center = new Point(0,0);
        double radius = 1;
        var circle = new Circle(center, radius);
        double area = circle.area();
        double approximateCircleArea = 3.141592653589793;

        // When

        // Then
        
        assertEquals(approximateCircleArea, area,0.0000000001);
    }
}