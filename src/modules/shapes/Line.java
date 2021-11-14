package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Line {

    public PVector start;
    public PVector end;
    public ArrayList<PVector> vertexList;

    public Line(PVector start, PVector end) {
        this.start = start;
        this.end = end;
        ArrayList<PVector> vertexList = new ArrayList<>();
    }

    public float length(){
        return start.dist(end);

    }


    public void shiftMe(PVector mover) {
        this.start.add(mover);
        this.end.add(mover);
    }



    public Line makeShift(PVector mover) {
        PVector newStart = start.copy().add(mover);
        PVector newEnd = end.copy().add(mover);
        return new Line(newStart, newEnd);
    }

    private void resetPointList() {
        ArrayList<PVector> vertexList = new ArrayList<>();
    }

    public ArrayList<PVector> divideLineInEqualParts(int n) {
        ArrayList<PVector> split = Utils.divideLineIntoEqualParts(this, n);
        return split;

    }

    public PVector getMidPoint() {
        return Utils.lineMidPoint(this);
    }

    public void draw(PApplet sketch) {
        sketch.line(start.x, start.y, end.x, end.y);
    }

    public void drawWithEndpoints(PApplet sketch, float size) {
        sketch.circle(start.x, start.y, size);
        sketch.circle(end.x, end.y, size);
        sketch.line(start.x, start.y, end.x, end.y);
    }

    public void drawMidPoint(PApplet sketch) {
        privateDrawMidPoint(sketch, 5);
    }

    public void drawMidPoint(PApplet sketch, float size) {
        privateDrawMidPoint(sketch, size);
    }

    private void privateDrawMidPoint(PApplet sketch, float size) {
        Utils.drawPoint(sketch, getMidPoint(), size);
    }


    public void drawRandomPointOnLine(PApplet sketch) {
        privateDrawRandomPointOnLine(sketch, this, 5);
    }

    public void drawRandomPointOnLine(PApplet sketch, float size) {
        privateDrawRandomPointOnLine(sketch, this, size);
    }

    public void drawRandomPointOnLine(PApplet sketch, float size, float offset) {
        Line line = Utils.shortenLineFromBothEnds(this, offset);
        privateDrawRandomPointOnLine(sketch, line, size);
    }

    private void privateDrawRandomPointOnLine(PApplet sketch, Line line, float size) {
        PVector point = Utils.randomPointOnLine(line);
        sketch.circle(point.x, point.y, size);
    }


}
