package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Circle {

    public PVector center;
    public float radius;

    public Circle(PVector circleCenter, float circleRadius) {
        center = circleCenter;
        radius = circleRadius;
    }

    ;

    public double area() {

        return Math.pow(this.radius, 2) * Math.PI;

    }

    public Circle shift(PVector mover) {
        PVector newCenter = center.copy().add(mover);
        return new Circle(newCenter, radius);

    }

    public ArrayList<NotReallyAnArc> equalArcSubdivide(int n){

        ArrayList<PVector> degreeLine = Utils.makePositiveRealLine(360).divideLineInEqualParts(n);
        ArrayList<NotReallyAnArc> arcList = new ArrayList<>();
        for(int i = 0; i<= degreeLine.size() - 1; i++){
            PVector A = this.center.copy();
            double lowerAngleInRadians = Utils.floatToDouble(degreeLine.get(i).x);
            PVector B = Utils.pointOnCircle(this,lowerAngleInRadians) ;

            double upperAngleInRadians = Utils.floatToDouble(degreeLine.get(i+1).x);
            PVector C = Utils.pointOnCircle(this,lowerAngleInRadians) ;

            NotReallyAnArc currentArc = new NotReallyAnArc(A,B,C,this);
            arcList.add(currentArc);
        }

        return arcList;

    }

    public PVector getPointOnCircleForAngle(float degrees){
        double radians = Utils.degreeToRadians(Utils.floatToDouble(degrees));
        return Utils.pointOnCircle(this,radians);
    };

    public PVector getOppositePointOnCircle(float degrees){
        double radians = Utils.degreeToRadians(Utils.floatToDouble(degrees));
        return Utils.pointOnCircle(this,radians);
    };

    public void scaleMe(float scalingFactor) {
        this.radius = this.radius * scalingFactor;
    }

    public Circle makeScale(float scalingFactor) {
        return new Circle(center, radius * scalingFactor);
    }

    public void draw(PApplet sketch) {

        sketch.circle(center.x, center.y, radius * 2);
    }


}
