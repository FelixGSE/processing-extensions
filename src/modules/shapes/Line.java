package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Line {

    public PVector start;
    public PVector end;

    public Line(PVector start, PVector end) {
        this.start = start;
        this.end = end;
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

    public Line getRotated(float degrees) {

        PVector endRotated = new Circle(start,length()).getPointOnCircleForAngle(degrees);

        return new Line(start,endRotated);
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


    public PVector getMidPoint() {
        return Utils.lineMidPoint(this);
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

    ;


}
