package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

public class Triangle {

    public PVector A;
    public PVector B;
    public PVector C;

    public Triangle(PVector A, PVector B, PVector C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    ;

    public Triangle getScaled(float factor) {

        PVector newA = Utils.shortenLineFromOrigin(lineFromInnerCircleToA(), factor);
        PVector newB = Utils.shortenLineFromOrigin(lineFromInnerCircleToB(), factor);
        PVector newC = Utils.shortenLineFromOrigin(lineFromInnerCircleToC(), factor);

        return new Triangle(newA, newB, newC);
    }

    ;

    public void scaleMe(float factor) {
        this.A = Utils.shortenLineFromOrigin(lineFromInnerCircleToA(), factor);
        this.B = Utils.shortenLineFromOrigin(lineFromInnerCircleToB(), factor);
        this.C = Utils.shortenLineFromOrigin(lineFromInnerCircleToC(), factor);
    }

    ;

    public Triangle getShifted(PVector mover) {
        PVector newA = A.copy().add(mover);
        PVector newB = B.copy().add(mover);
        PVector newC = C.copy().add(mover);
        return new Triangle(newA, newB, newC);
    }

    ;

    public void shiftMe(PVector mover) {
        this.A.add(mover);
        this.B.add(mover);
        this.C.add(mover);
    }

    ;

    public double a() {
        return Utils.floatToDouble(C.dist(this.B));

    }

    ;

    public double b() {

        return Utils.floatToDouble(A.dist(this.C));

    }

    ;

    public double c() {

        return Utils.floatToDouble(A.dist(this.B));

    }

    ;

    public float hA() {
        return Utils.doubleToFloat(c() * Math.sin(beta()));
    }

    ;

    public float hB() {
        return Utils.doubleToFloat(a() * Math.sin(gamma()));
    }

    ;

    public float hC() {
        return Utils.doubleToFloat(b() * Math.sin(alpha()));
    }

    ;

    public double U() {
        return a() + b() + c();
    }

    ;

    public double area() {
        return (a() * hA()) / 2;
    }

    ;

    public double alpha() {
        return Math.acos((Math.pow(b(), 2) + Math.pow(c(), 2) - Math.pow(a(), 2)) / (2 * b() * c()));
    }

    ;

    public double beta() {
        return Math.acos((Math.pow(a(), 2) + Math.pow(c(), 2) - Math.pow(b(), 2)) / (2 * a() * c()));
    }

    ;

    public double gamma() {
        return Math.acos((Math.pow(a(), 2) + Math.pow(b(), 2) - Math.pow(c(), 2)) / (2 * a() * b()));

    }

    ;

    public double sumOfAngles() {

        return alpha() + beta() + gamma();

    }

    ;

    public Line segmentA() {
        return new Line(B, C);

    }

    ;

    public Line segmentB() {
        return new Line(A, C);

    }

    ;

    public Line segmentC() {
        return new Line(A, B);

    }

    ;

    public PVector innerCircleCenter() {
        double x = (a() * A.x + b() * B.x + c() * C.x) / (a() + b() + c());
        double y = (a() * A.y + b() * B.y + c() * C.y) / (a() + b() + c());
        return new PVector(Utils.doubleToFloat(x), Utils.doubleToFloat(y));

    }

    ;

    public double innerCircleRadius() {
        return 2 * area() / U();
    }

    ;

    public Circle getInnerCircle() {
        return new Circle(innerCircleCenter(), Utils.doubleToFloat(innerCircleRadius()));

    }

    ;

    public Line lineFromInnerCircleToA() {
        return new Line(A, innerCircleCenter());

    }

    ;

    public Line lineFromInnerCircleToB() {
        return new Line(B, innerCircleCenter());

    }

    ;

    public Line lineFromInnerCircleToC() {
        return new Line(C, innerCircleCenter());

    }

    ;

    public PVector computeRandomPointOn() {

        String[] sides = {"a", "b", "c"};

        String side = Utils.getRandom(sides);

        if (side.equals("a")) {
            return segmentA().getRandomPointOn();
        } else if (side.equals("b")) {
            return segmentB().getRandomPointOn();
        } else if (side.equals("c")) {
            return segmentC().getRandomPointOn();
        } else {
            throw new IllegalArgumentException();
        }


    }

    ;

    public PVector computeRandomPointIn() {

        return Utils.randomPointInTriangle(this);

    }

    ;

    public void draw(PApplet myParent) {

        myParent.triangle(this.A.x, this.A.y, this.B.x, this.B.y, this.C.x, this.C.y);

    }

    ;

    public void drawRandomPointInside(PApplet sketch) {
        privateDrawRandomPointInside(sketch, 10, 1);
    }

    ;

    public void drawRandomPointInside(PApplet sketch, float size) {
        privateDrawRandomPointInside(sketch, size, 1);
    }

    ;

    public void drawRandomPointInside(PApplet sketch, float size, float offSet) {
        privateDrawRandomPointInside(sketch, size, offSet);
    }

    ;

    private void privateDrawRandomPointInside(PApplet sketch, float size, float offSet) {
        Triangle triangle = getScaled(offSet);
        PVector point = triangle.computeRandomPointIn();
        sketch.circle(point.x, point.y, size);
    }

    ;

    public void drawRandomCurveFill(PApplet sketch) {
        privateDrawRandomCurveFill(sketch, 3, 1);
    }

    ;

    public void drawRandomCurveFill(PApplet sketch, int n) {
        privateDrawRandomCurveFill(sketch, n, 1);
    }

    public void drawRandomCurveFill(PApplet sketch, int n, float offset) {
        privateDrawRandomCurveFill(sketch, 4, offset);
    }

    ;

    public void privateDrawRandomCurveFill(PApplet sketch, int n, float offSet) {
        if (n < 3) {
            throw new IllegalArgumentException();
        }
        Triangle newTriangle = getScaled(offSet);
        PVector pointA = newTriangle.computeRandomPointIn();
        PVector pointB = newTriangle.computeRandomPointIn();
        PVector pointC = newTriangle.computeRandomPointIn();
        PVector pointD = newTriangle.computeRandomPointIn();
        sketch.curve(pointA.x, pointA.y, pointB.x, pointB.y, pointC.x, pointC.y, pointD.x, pointD.y);


    }

    ;
    // FIXME: CurveVertex is not really doing anything :(

//
//    public void privateDrawRandomCurveFill(PApplet sketch, int n, float offSet) {
//        if(n < 3){
//            throw new IllegalArgumentException();
//        }
//        Triangle newTriangle = makeScale(offSet);
//        sketch.beginShape();
//        for(int i=0; i<=n;i++){
//
//            PVector point = newTriangle.computeRandomPointInTriangle();
//            sketch.curveVertex(point.x,point.y);
//
//        }
//        sketch.endShape();
//
//    };

    public void drawRandomLineFill(PApplet sketch) {
        privateDrawRandomLineFill(sketch, 1);
    }

    ;

    public void drawRandomLineFill(PApplet sketch, float offset) {
        privateDrawRandomLineFill(sketch, offset);
    }

    ;

    public void privateDrawRandomLineFill(PApplet sketch, float offSet) {

        Triangle newTriangle = getScaled(offSet);
        PVector pointA = newTriangle.computeRandomPointIn();
        PVector pointB = newTriangle.computeRandomPointIn();
        sketch.line(pointA.x, pointA.y, pointB.x, pointB.y);


    }

    ;

}
