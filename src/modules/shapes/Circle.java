package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Circle {

    public PVector center;
    public float radius;
    public float diameter;

    public Circle(PVector center, float radius) {
        this.center = center;
        this.radius = radius;
        this.diameter = 2 * radius;

    }

    ;

    public Circle getShifted(PVector mover) {
        PVector newCenter = center.copy().add(mover);
        return new Circle(newCenter, radius);

    }

    ;

    public void shiftMe(PVector mover) {
        this.center.add(mover);
    }

    ;

    public Circle getScaled(float scalingFactor) {
        return new Circle(center, radius * scalingFactor);
    }

    ;

    public void scaleMe(float scalingFactor) {
        this.radius = this.radius * scalingFactor;
    }

    ;

    public double area() {

        return Math.pow(this.radius, 2) * Math.PI;

    }

    ;

    public double circumference() {

        return 2 * Math.PI * radius;

    }

    ;

    public PVector getPointOnCircleForAngle(float degrees) {
        double radians = Utils.degreeToRadians(Utils.floatToDouble(degrees));
        return Utils.pointOnCircle(this, radians);
    }

    ;

    public PVector getOppositePointOnCircleForAngle(float degrees) {
        double radians = Utils.degreeToRadians(Utils.floatToDouble(degrees));
        return Utils.pointOnCircle(this, radians);
    }

    ;

    public PVector computeRandomPointOnBetweenAngles(float start,float stop) {
        return Utils.randomPointOnCircleBetweenAngles(this,start,stop);
    }

    ;

    public PVector computeRandomPointIn() {
        return Utils.randomPointInCircle(this);
    }

    ;

    public PVector computeRandomPointOn() {
        return Utils.randomPointOnCircle(this);
    }

    ;


    public ArrayList<NotReallyAnArc> equalArcSubdivide(int n) {

        ArrayList<PVector> degreeLine = Utils.makePositiveRealLine(360).divideInEqualParts(n);
        ArrayList<NotReallyAnArc> arcList = new ArrayList<>();
        for (int i = 0; i <= degreeLine.size() - 1; i++) {
            PVector A = this.center.copy();
            double lowerAngleInRadians = Utils.floatToDouble(degreeLine.get(i).x);
            PVector B = Utils.pointOnCircle(this, lowerAngleInRadians);

            double upperAngleInRadians = Utils.floatToDouble(degreeLine.get(i + 1).x);
            PVector C = Utils.pointOnCircle(this, lowerAngleInRadians);

            NotReallyAnArc currentArc = new NotReallyAnArc(A, B, C, this);
            arcList.add(currentArc);
        }

        return arcList;

    }

    ;


    public void draw(PApplet sketch) {

        sketch.circle(center.x, center.y, radius * 2);
    }

    ;


}
