package test.shapes;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import modules.shapes.*;

import java.util.ArrayList;
import java.util.Random;
import processing.core.PVector;


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

        PVector center = new PVector(0,0);
        float radius = 1;
        Circle circle = new Circle(center,radius);
        double radians = Utils.degreeToRadians(90);

        // Given
        PVector pointOnCircle = Utils.pointOnCircle(circle, radians);
        // When

        // Then
        
        assertEquals(0,pointOnCircle.x,0.001);
        assertEquals(1,pointOnCircle.y,0.001);
    }

    @Test
    public void testAngleForPointOnCircle() {

        PVector center = new PVector(0,0);
        float radius = 1;
        Circle circle = new Circle(center,radius);

        PVector pointOnCircle = new PVector(0, 1);

        // Given
        double angleForPointOnCircle = Utils.angleForPointOnCircle(circle, pointOnCircle);
        // When

        // Then
                
        assertEquals(Utils.degreeToRadians(90),angleForPointOnCircle,0.001);
        
    }

    @Test
    public void testLineSubdivision() {
        long seed =20;
        Random random = new Random();
        random.setSeed(seed);

        PVector start = new PVector(0,0);
        PVector end = new PVector(1000,0);
        Line lineToSplit = new Line(start, end );

        ArrayList<Line> listOfLines = Utils.divideLineIntoUniformRandomParts(lineToSplit,1);

//        for(int i = 0; i<=listOfLines.size();i++){
//            Line line = listOfLines.get(i);
//            start = line.start;
//            end = line.end;
//            System.out.println(String.format("START X: %f, START Y: %f", start.x,start.y));
//            System.out.println(String.format("END X: %f, END Y: %f", end.x,end.y));
//        }

        // Given

        // When

        // Then

        assertEquals(4,listOfLines.size());

    }
}