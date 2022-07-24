package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Objects;

public class Line {

    public PVector start;
    public PVector end;

    public Line(PVector start, PVector end) {
        this.start = start;
        this.end = end;
    }

    ;

    @Override
    public String toString() {
        return String.format("Line(A=(%f,%f),B=(%f,%f))", start.x,start.y, end.x,end.y);
    }
    ;

    public Line getShifted(PVector mover) {
        PVector newStart = start.copy().add(mover);
        PVector newEnd = end.copy().add(mover);
        return new Line(newStart, newEnd);
    }

    ;

    public void shiftMe(PVector mover) {
        this.start.add(mover);
        this.end.add(mover);
    }

    ;

    public Line getScaled(float factor) {
        return Utils.shortenLineFromBothEnds(this, factor);
    }

    ;

    public void scaleMe(float factor) {
        Line scaled = Utils.shortenLineFromBothEnds(this, factor);
        this.start = scaled.start;
        this.end = scaled.end;
    }

    ;

    public PVector getStart(){
        return start.copy();
    }

    public PVector getEnd(){
        return end.copy();
    }

    public Line getRotated(float degrees) {

        PVector endRotated = new Circle(getStart(),length()).getPointOnCircleForAngle(degrees);

        return new Line(getStart(),endRotated);
    }

    ;

    public void rotateMe(float degrees) {
        this.end = new Circle(start,length()).getPointOnCircleForAngle(degrees);
    }

    ;

    public float length() {
        return start.dist(end);

    }

    ;

    public ArrayList<PVector> divideInEqualParts(int n) {
        return Utils.divideLineIntoEqualParts(this, n);
    }

    ;

    public ArrayList<Line> divideInEqualPartsAsLineCollection(int n) {
        return Utils.pointCollectionToLineCollection(Utils.divideLineIntoEqualParts(this, n));
    }

    ;


    public PVector getMidPoint() {
        return Utils.lineMidPoint(this);
    }

    ;

    public ArrayList<PVector> subdivide(int depth){

        ArrayList<PVector> pointList = new ArrayList<>();
        pointList.add(getStart());
        subdivide(pointList, this, depth - 1,  1,  100 / (depth+1),  100 / (depth+1));
        pointList.add(getEnd());
        return pointList;

    }

    private void subdivide(ArrayList<PVector> pointList, Line line, int depth, float locationVariance, float lengthVariance, float angleVariance){
        if (depth >= 0) {

            PVector randomPointOnLine = line.computeRandomGaussianPointOnLine(locationVariance);
            float length = Utils.doubleToFloat(Math.abs(Utils.randomNormal(0,lengthVariance)));
            float angle = getStartPointRotation() + 90 + Utils.doubleToFloat(Utils.randomNormal(0,angleVariance));
            PVector splitPoint = new Circle(randomPointOnLine, length).getPointOnCircleForAngle(angle);

            Line left = new Line(line.getStart(), splitPoint.copy());
            Line right = new Line(splitPoint.copy(), line.getEnd());
            /* Add two new edges which are recursively subdivided */
            subdivide(pointList, left, depth - 1, locationVariance,100/(2*depth+1),100 / (depth+1));
            pointList.add(splitPoint.copy());
            subdivide(pointList, right, depth - 1, locationVariance,100/(2*depth+1),100 / (depth+1));
        }



    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(start, line.start) && Objects.equals(end, line.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }


    public static Line makePositiveRealLine(float upper) {

        return new Line(new PVector(0, 0), new PVector(upper, 0));

    }

    public static Line makeRealLine(float lower, float upper) {
        if(lower > upper){
            throw new IllegalArgumentException("Parameter lower must less or equal than upp");
        }
        return new Line(new PVector(lower, 0), new PVector(upper, 0));
    }

    public PVector computeRandomGaussianPointOnLine(double sigma){
        return Utils.randomGaussianPointOnLine(this,sigma);
    }
    ;

    public PVector getRandomPointOn() {
        return Utils.randomPointOnLine(this);
    }

    ;

    public PVector getRandomPointIn() {
        return Utils.randomPointOnLine(this);
    }

    ;

    public void draw(PApplet sketch) {
        sketch.line(start.x, start.y, end.x, end.y);
    }

    ;

    public void drawWithEndpoints(PApplet sketch, float size) {
        sketch.circle(start.x, start.y, size);
        sketch.circle(end.x, end.y, size);
        sketch.line(start.x, start.y, end.x, end.y);
    }

    ;

    public void drawMidPoint(PApplet sketch) {
        privateDrawMidPoint(sketch, 5);
    }

    ;

    public void drawMidPoint(PApplet sketch, float size) {
        privateDrawMidPoint(sketch, size);
    }

    ;

    private void privateDrawMidPoint(PApplet sketch, float size) {
        Utils.drawPoint(sketch, getMidPoint(), size);
    }

    ;

    public void drawRandomGaussianPointOnLine(PApplet sketch, float sigma) {
        privateDrawRandomGaussianPointOnLine(sketch, sigma, 5);
    }

    ;

    public void drawRandomGaussianPointOnLine(PApplet sketch, float sigma, float size) {
        privateDrawRandomGaussianPointOnLine(sketch, sigma, size);
    }

    ;

    private void privateDrawRandomGaussianPointOnLine(PApplet sketch, float sigma, float size) {
        PVector point = computeRandomGaussianPointOnLine(Utils.floatToDouble(sigma) );
        sketch.circle(point.x, point.y, size);
    }

    ;


    public void drawRandomPointOnLine(PApplet sketch) {
        privateDrawRandomPointOnLine(sketch, this, 5);
    }

    ;

    public void drawRandomPointOnLine(PApplet sketch, float size) {
        privateDrawRandomPointOnLine(sketch, this, size);
    }

    ;

    public void drawRandomPointOnLine(PApplet sketch, float size, float offset) {
        Line line = Utils.shortenLineFromBothEnds(this, offset);
        privateDrawRandomPointOnLine(sketch, line, size);
    }

    ;

    private void privateDrawRandomPointOnLine(PApplet sketch, Line line, float size) {
        PVector point = Utils.randomPointOnLine(line);
        sketch.circle(point.x, point.y, size);
    }

    public Line increaseByLength(float length) {

        float scalingFactor = 1+length / length();
        PVector newEnd = Utils.scaleLineFromOrigin( this, scalingFactor);
        return new Line(getStart(),newEnd);

    }

    public float getStartPointRotation() {
        return new Circle(getMidPoint(), length()).getAngleForPointOnCircle(getStart());
    }

    ;

    public float getEndPointRotation() {
        return new Circle(getMidPoint(), length()).getAngleForPointOnCircle(getEnd());
    }


}
