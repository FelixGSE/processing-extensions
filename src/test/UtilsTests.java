package test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import shapes.*;


public class UtilsTests {
    @Test
    public void testRadiansToDegrees() {

        // Given
        double degress = Utils.radiansToDegree(1.5708);
        // When

        // Then
        
        assertEquals(90.00,degress,0.01);
    }

    @Test
    public void testPointOnCircle() {

        var center = new Point(0,0);
        float radius = 1;
        var circle = new Circle(center,radius);
        double radians = Utils.degreeToRadians(90);

        // Given
        var pointOnCircle = Utils.pointOnCircle(circle, radians);
        // When

        // Then
        
        assertEquals(0,pointOnCircle.x,0.001);
        assertEquals(1,pointOnCircle.y,0.001);
    }

    @Test
    public void testAngleForPointOnCircle() {

        var center = new Point(0,0);
        float radius = 1;
        var circle = new Circle(center,radius);
        
        var pointOnCircle = new Point(0, 1);

        // Given
        var angleForPointOnCircle = Utils.angleForPointOnCircle(circle, pointOnCircle);
        // When

        // Then
                
        assertEquals(Utils.degreeToRadians(90),angleForPointOnCircle,0.001);
        
    }
}