package test.shapes;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import modules.shapes.*;
import processing.core.PVector;

import java.util.ArrayList;

import org.junit.Test;

public class ArcTests {

    @Test
    public void testGCircle() {
        PVector center = new PVector(500,500);
        float radius = 300;
        Circle circle = new Circle(center,radius);
        PVector A = new PVector(500,500);
        PVector B = new PVector(800,500);
        PVector C = new PVector(500,800);
        NotReallyAnArc arc = new NotReallyAnArc(A,B,C,circle);
        PVector a = new PVector(100,100);
        PVector b = new PVector(600,100);
        PVector c = new PVector(300,600);
        Triangle triangle = new Triangle(a,b,c);
        arc.test(triangle);

        System.out.println(arc.toString());


    }
}
