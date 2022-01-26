package test.shapes;

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

        assertEquals(randomNumbers.size(), 4);


    }

    @Test
    public void testDivideLineInRandomParts() {

        PVector a = new PVector(100, 100);
        PVector b = new PVector(600, 100);
        Line line = new Line(a, b);

        ArrayList<PVector> listOfSplitPoints = Utils.divideLineInRandomParts(line, 4);

        assertEquals(listOfSplitPoints.size(), 3);


    }

    @Test
    public void testPointCollectionToLineCollection() {

        PVector a = new PVector(100, 100);
        PVector b = new PVector(600, 100);
        Line line = new Line(a, b);

        ArrayList<PVector> listOfSplitPoints = Utils.divideLineInRandomParts(line, 4);
        ArrayList<Line> lines = Utils.pointCollectionToLineCollection(listOfSplitPoints);

        assertEquals(4, lines.size());


    }

    @Test
    public void testDivideLineIntoEqualParts() {

        PVector a = new PVector(100, 100);
        PVector b = new PVector(600, 100);
        Line line = new Line(a, b);

        ArrayList<PVector> listOfSplitPoints = Utils.divideLineIntoEqualParts(line, 4);
        ArrayList<Line> lines = Utils.pointCollectionToLineCollection(listOfSplitPoints);
        assertEquals(4, lines.size());

    }

    @Test
    public void shouldReturnZeroSplitPointsWhenDepthIsZeroWhenSubdivide() {

        PVector a = new PVector(100, 100);
        PVector b = new PVector(600, 100);
        Line line = new Line(a, b);

        ArrayList<PVector> result = line.subdivide(0);

        assertEquals(0, result.size());

    }

    @Test
    public void shouldReturnOneSplitPointsWhenDepthIsOneWhenSubdivide() {

        PVector a = new PVector(100, 100);
        PVector b = new PVector(600, 100);
        Line line = new Line(a, b);

        ArrayList<PVector> result = line.subdivide(1);

        assertEquals(1, result.size());

    }

    @Test
    public void shouldReturnTwoToThePowerOfDepthMinusOneSplitPointsWhenInputIsGreaterThanZero() {

        PVector a = new PVector(100, 100);
        PVector b = new PVector(600, 100);
        Line line = new Line(a, b);

        for (int i = 0; i <= 5; i++) {
            ArrayList<PVector> result = line.subdivide(i);
            assertEquals(Math.pow(2,i) - 1, result.size(),0.01);
        }

    }

    @Test
    public void shouldReturnALineWithSameLengthWhenInputIsZero() {
        PVector a = new PVector(100, 100);
        PVector b = new PVector(600, 100);
        Line line = new Line(a, b);
        float scaleby = 10;

        Line result = line.increaseByLength(scaleby);

        assertEquals(line.length() + scaleby, result.length(), 0.01);

    }

    @Test
    public void startPointOfRotationShouldBe90DegreesWhenLiesOnUnitCircle() {
        PVector start = new PVector(0, 1);
        PVector end = new PVector(0, -1);
        Line line = new Line(start, end);

        float result = line.getStartPointRotation();

        assertEquals(90, result, 0.01);

    }


}
