package test.shapes;

import modules.shapes.Circle;
import modules.shapes.Triangle;
import modules.shapes.Line;
import modules.shapes.Utils;
import org.junit.Test;
import processing.core.PVector;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LineTests {
    @Test
    public void testArrayWithRandomNumbers() {

        ArrayList<Double> randomNumbers = Utils.arrayWithRandomNumbers(4);

        assertEquals(randomNumbers.size(),4);


    }

    @Test
    public void testDivideLineInRandomParts() {

        PVector a = new PVector(100,100);
        PVector b = new PVector(600,100);
        Line line = new Line(a,b);

        ArrayList<PVector> listOfSplitPoints = Utils.divideLineInRandomParts(line,4);

        assertEquals(listOfSplitPoints.size(),3);


    }

    @Test
    public void testPointCollectionToLineCollection() {

        PVector a = new PVector(100,100);
        PVector b = new PVector(600,100);
        Line line = new Line(a,b);

        ArrayList<PVector> listOfSplitPoints = Utils.divideLineInRandomParts(line,4);
        ArrayList<Line> lines = Utils.pointCollectionToLineCollection(listOfSplitPoints);

        assertEquals(4,lines.size());


    }

    @Test
    public void testDivideLineIntoEqualParts() {

        PVector a = new PVector(100,100);
        PVector b = new PVector(600,100);
        Line line = new Line(a,b);

        ArrayList<PVector> listOfSplitPoints = Utils.divideLineIntoEqualParts(line,4);
        ArrayList<Line> lines = Utils.pointCollectionToLineCollection(listOfSplitPoints);
        assertEquals(4,lines.size());


    }
}
