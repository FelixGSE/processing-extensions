package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Circle {

    public PVector center;
    public float radius;
    public float diameter;
    public float globalRotation;

    public Circle(PVector center, float radius) {
        this.center = center;
        this.radius = radius;
        this.diameter = 2 * radius;

    }

    ;

    public Circle copy(){

        return new Circle(getCenter(),radius);

    }

    public Circle getShifted(PVector mover) {
        PVector newCenter = center.copy().add(mover);
        return new Circle(newCenter, radius);

    }

    ;

    public PVector getCenter(){
        return center.copy();

    }

    public void shiftMe(PVector mover) {
        this.center.add(mover);
    }

    ;

    public Circle getJittered(float radius) {
        return new Circle(Utils.getCircularJitteredPoint(this.center, radius), this.radius);

    }

    ;

    public void jitterMe(float radius) {
        this.center = Utils.getCircularJitteredPoint(this.center, radius);
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

    public ArrayList<PVector> getPoles(){

        ArrayList<PVector> poles = new ArrayList<PVector>();

        for(int i = 0; i <=360; i = i +90){
            poles.add(getPointOnCircleForAngle(i));
        }
        return poles;

    }

    public void getChordQuadrilateral(){



        return ;
    }
    ;


    public Polygon getRegularPolygon(int numberOfVertices) {
        if (numberOfVertices < 3) {
            throw new IllegalArgumentException();
        }

        ArrayList<PVector> polygonVertices = new ArrayList<PVector>();

        float splits = 360 / ((float) numberOfVertices - 1);

        for (int i = 0; i < numberOfVertices; i++) {

            PVector point = getPointOnCircleForAngle(i * splits);
            polygonVertices.add(point);

        }

        return new Polygon(polygonVertices);

    }


    public PVector getPointOnCircleForAngle(float degrees) {
        double radians = Utils.degreeToRadians(Utils.floatToDouble(degrees));
        return Utils.pointOnCircle(this, radians);
    }

    ;

    public PVector getOppositePointOnCircleForAngle(float degrees) {
        double radians = Utils.degreeToRadians(Utils.floatToDouble(degrees));
        PVector pointOnCircle = Utils.pointOnCircle(this, radians);
        float newX = this.center.x - (pointOnCircle.x - this.center.x);
        float newY = this.center.y - (pointOnCircle.y - this.center.y);

        return new PVector(newX, newY);
    }

    ;

    public PVector computeRandomGaussianPointOnBetweenAngles(float start, float stop, float sigma) {

        return Utils.randomGaussianPointOnCircleBetweenAngles(this, start, stop, sigma);

    }

    ;

    public PVector computeRandomPointOnBetweenAngles(float start, float stop) {
        return Utils.randomPointOnCircleBetweenAngles(this, start, stop);
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
        for (int i = 0; i <= degreeLine.size() - 2; i++) {

            PVector A = this.center.copy();

            double lowerAngleInRadians = Utils.degreeToRadians(degreeLine.get(i).x);
            PVector B = Utils.pointOnCircle(this, lowerAngleInRadians);

            double upperAngleInRadians = Utils.degreeToRadians(degreeLine.get(i + 1).x);
            PVector C = Utils.pointOnCircle(this, upperAngleInRadians);

            NotReallyAnArc currentArc = new NotReallyAnArc(A, B, C, this);
            arcList.add(currentArc);
        }

        return arcList;

    }

    ;

    public void drawEqualArcTriangleSubdivide(PApplet sketch, int n, int depth, float sigma) {

        ArrayList<NotReallyAnArc> arcList = equalArcSubdivide(n);
        for (int i = 0; i < arcList.size(); i++) {

            arcList.get(i).drawTriangleSubdivisionFill(sketch, depth, sigma);

        }

    }

    ;


    public void draw(PApplet sketch) {

        sketch.circle(center.x, center.y, radius * 2);
    }

    ;

    public void drawRandomPointIn(PApplet sketch) {

        sketch.circle(center.x, center.y, radius * 2);
    }


    public float getAngleForPointOnCircle(PVector point) {
        double degrees = Math.toDegrees(Math.atan2(point.y - this.center.y, point.x - this.center.x));
        if (degrees < 0) {
            return 360 + Utils.doubleToFloat(degrees);
        } else {
            return Utils.doubleToFloat(degrees);
        }
    }
}
