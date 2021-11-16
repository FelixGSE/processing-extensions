package test.shapes;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import modules.shapes.*;
import processing.core.PVector;


public class TriangleTest {
    @Test
    public void testAngle() {

        PVector A = new PVector(100,100);
        PVector B = new PVector(110,100);
        PVector C = new PVector(105,105);
        Triangle triangle = new Triangle(A,B,C);

        System.out.println(String.format("H A: %f", triangle.hA()));
        System.out.println(String.format("H B: %f", triangle.hB()));
        System.out.println(String.format("H C: %f", triangle.hC()));


        System.out.println(String.format("Test a: %f", triangle.a()));
        System.out.println(String.format("Test b: %f", triangle.b()));
        System.out.println(String.format("Test c: %f", triangle.c()));
        // System.out.println(String.format("Actual X: %f", triangle.aTest()));

        System.out.println(Math.pow(triangle.a(), 2));

// Code
 double angle = Math.acos((Math.pow(triangle.b(), 2) + Math.pow(triangle.c(), 2) - Math.pow(triangle.a(), 2) / (2 * triangle.b() * triangle.c())));
        double test = Math.acos((Math.pow(triangle.a(), 2) + Math.pow(triangle.b(), 2) - Math.pow(triangle.c(), 2)) / (2 * triangle.a() * triangle.b()));
//        double test = Math.acos((Math.pow(triangle.b(), 2) + Math.pow(triangle.c(), 2) - Math.pow(triangle.a(), 2) / (2 * triangle.b() * triangle.c())));
        System.out.println(String.format("Test : %f", test));
        System.out.println(String.format("Angle : %f", angle));
        System.out.println(String.format("alpha X: %f", triangle.alpha()));
        System.out.println(String.format("beta X: %f", triangle.beta()));
        System.out.println(String.format("gamma X: %f", triangle.gamma()));
        System.out.println(String.format("Inner Center X: %f", triangle.innerCircleCenter().x));
        System.out.println(String.format("Inner Center Y: %f", triangle.innerCircleCenter().y));

        // Given
        Circle inner = triangle.getInnerCircle();
        System.out.println(String.format("Inner Center Y: %f", inner.radius));
        // When

        // Then
        assertEquals(1,1);
    }
}