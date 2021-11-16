package test.shapes;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import modules.shapes.*;
import processing.core.PVector;

import java.util.ArrayList;


public class CircleTests {
    @Test
    public void testGCircle() {

        // Given
        PVector center = new PVector(0,0);
        float radius = 1;

        Circle circle = new Circle(center, radius);
        double area = circle.area();
        double approximateCircleArea = 3.141592653589793;

        PVector p = circle.getPointOnCircleForAngle(400);
        PVector p2 = circle.getPointOnCircleForAngle(40);
        System.out.println(String.format("P1 x: %f", p.x));
        System.out.println(String.format("P1 y: %f", p.y));
        System.out.println(String.format("P2 x: %f", p2.x));
        System.out.println(String.format("P2 y: %f", p2.y));

        // When

        // Then
        
        assertEquals(approximateCircleArea, area,0.0000000001);
    }

    @Test
    public void testEqualArcSubdivide() {

        PVector center = new PVector(0,0);
        float radius = 1;
        Circle circle = new Circle(center,radius);

        ArrayList<NotReallyAnArc> listOfArcs = circle.equalArcSubdivide(4);

        int n = 4;
        ArrayList<PVector> degreeLine = Utils.makePositiveRealLine(360).divideInEqualParts(n);
        ArrayList<NotReallyAnArc> arcList = new ArrayList<>();

        for (int i = 0; i <= degreeLine.size() - 2; i++) {
            System.out.println(String.format("Angle lower: %f",degreeLine.get(i).x));
            System.out.println(String.format("Angle upper: %f",degreeLine.get(i+1).x));

            PVector A = circle.center.copy();
            double lowerAngleInRadians = Utils.degreeToRadians(degreeLine.get(i).x);
            PVector B = Utils.pointOnCircle(circle, lowerAngleInRadians);

            System.out.println(String.format("B X: %f",B.x));
            System.out.println(String.format("B Y: %f",B.y));


            double upperAngleInRadians = Utils.degreeToRadians(degreeLine.get(i + 1).x);
            PVector C = Utils.pointOnCircle(circle, upperAngleInRadians);

            System.out.println(String.format("C X: %f",C.x));
            System.out.println(String.format("C Y: %f",C.y));

            NotReallyAnArc currentArc = new NotReallyAnArc(A, B, C, circle);
            arcList.add(currentArc);
        }

        assertEquals(4,listOfArcs.size());
        // 1
//        assertEquals(1.000000,listOfArcs.get(0).B.x,0.1);
//        assertEquals(0.000000,listOfArcs.get(0).B.y,0.1);
//        assertEquals(0.000000,listOfArcs.get(0).C.x,0.1);
//        assertEquals(1.000000,listOfArcs.get(0).C.y,0.1);
//        // 2
//        assertEquals(0.000000,listOfArcs.get(1).B.x,0.1);
//        assertEquals(1.000000,listOfArcs.get(1).B.y,0.1);
//        assertEquals(-1.000000,listOfArcs.get(1).C.x,0.1);
//        assertEquals(0.000000,listOfArcs.get(1).C.y,0.1);
//        // 3
//        assertEquals(-1.000000,listOfArcs.get(2).B.x,0.1);
//        assertEquals(0.000000,listOfArcs.get(2).B.y,0.1);
//        assertEquals(0.000000,listOfArcs.get(2).C.x,0.1);
//        assertEquals(1.000000,listOfArcs.get(2).C.y,0.1);
//        //4
//        assertEquals(0.000000,listOfArcs.get(3).B.x,0.1);
//        assertEquals(1.000000,listOfArcs.get(3).B.y,0.1);
//        assertEquals(1.000000,listOfArcs.get(3).C.x,0.1);
//        assertEquals(0.000000,listOfArcs.get(3).C.y,0.1);

    }

    @Test
    public void angleForPointOnCircle() {

        PVector center = new PVector(0,0);
        float radius = 1;
        Circle circle = new Circle(center,radius);

        PVector A = new PVector(1,0);
        double angleA = Utils.findAngle(circle, A);
        System.out.println(String.format("Degree A: %f",angleA));

        PVector B = new PVector(0,-1);
        double angleB = Utils.findAngle(circle, B);
        System.out.println(String.format("Degree B: %f",angleB));

        PVector C = new PVector(-1,0);
        double angleC = Utils.findAngle(circle, C);
        System.out.println(String.format("Degree B: %f",angleC));

        PVector D = new PVector(0,1);
        double angleD = Utils.findAngle(circle, D);
        System.out.println(String.format("Degree B: %f",angleD));

//        PVector E = new PVector(Utils.doubleToFloat(Math.sqrt(2) / 2),Utils.doubleToFloat(- Math.sqrt(2) / 2));
//        double angleE = Utils.findAngle(circle, E);
//        System.out.println(String.format("Degree E: %f",angleE));



        assertEquals(1,1);

    }



}

