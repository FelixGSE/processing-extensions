package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

public class Triangle {

    public PVector A;
    public PVector B;
    public PVector C;
    public float globalRotation;

    public Triangle(PVector A, PVector B, PVector C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    // FIXME: Some hack
    public Triangle() {
        this.A = new PVector(0,0);
        this.B = new PVector(0,0);
        this.C = new PVector(0,0);
    }

    ;

    public Triangle getScaled(float factor) {

        PVector newA = Utils.scaleLineFromOrigin(lineFromInnerCircleToA(), factor);
        PVector newB = Utils.scaleLineFromOrigin(lineFromInnerCircleToB(), factor);
        PVector newC = Utils.scaleLineFromOrigin(lineFromInnerCircleToC(), factor);

        return new Triangle(newA, newB, newC);
    }

    ;

    public void scaleMe(float factor) {
        this.A = Utils.scaleLineFromOrigin(lineFromInnerCircleToA(), factor);
        this.B = Utils.scaleLineFromOrigin(lineFromInnerCircleToB(), factor);
        this.C = Utils.scaleLineFromOrigin(lineFromInnerCircleToC(), factor);
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

    public Triangle getRotated(float degrees) {

        float radians = Utils.doubleToFloat(Utils.degreeToRadians(degrees));
        PVector newA = this.A.copy().rotate(radians);
        PVector newB = this.B.copy().rotate(radians);
        PVector newC = this.C.copy().rotate(radians);
        return new Triangle(newA,newB,newC);

    }

    ;

    public void rotateMe(float degrees) {
        float radians = Utils.doubleToFloat(Utils.degreeToRadians(degrees));
        this.A.rotate(radians);
        this.B.rotate(radians);
        this.C.rotate(radians);
    }

    ;

    public Triangle getJittered(float radius) {

        PVector newA = Utils.getCircularJitteredPoint(this.A,radius);
        PVector newB = Utils.getCircularJitteredPoint(this.B,radius);
        PVector newC = Utils.getCircularJitteredPoint(this.C,radius);

        return new Triangle(newA,newB,newC);

    }

    ;

    public void jitterMe(float radius) {
        this.A.add(Utils.getCircularJitteredPoint(this.A,radius));
        this.B.add(Utils.getCircularJitteredPoint(this.B,radius));
        this.C.add(Utils.getCircularJitteredPoint(this.C,radius));
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

    public float distanceFromInnerCircleCenterToA() {
        return lineFromInnerCircleToA().length();
    };

    public float distanceFromInnerCircleCenterToB() {
        return lineFromInnerCircleToB().length();
    };

    public float distanceFromInnerCircleCenterToC() {
        return lineFromInnerCircleToC().length();
    };

    public PVector pointOnInnerCirclePointToA() {
        Circle inner = getInnerCircle();
        double ratio = inner.radius / distanceFromInnerCircleCenterToA();
        Line line = new Line(inner.center, A);
        return Utils.scaleLineFromOrigin(line,ratio);
    };

    public PVector pointOnInnerCirclePointToB() {
        Circle inner = getInnerCircle();
        double ratio = inner.radius / distanceFromInnerCircleCenterToB();
        Line line = new Line(inner.center, B);
        return Utils.scaleLineFromOrigin(line,ratio);
    };

    public PVector pointOnInnerCirclePointToC() {
        Circle inner = getInnerCircle();
        double ratio = inner.radius / distanceFromInnerCircleCenterToC();
        Line line = new Line(inner.center, C);
        return Utils.scaleLineFromOrigin(line,ratio);
    };


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

    public PVector computeRandomPointOnSide(String side) {

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

    public Line getSegment(String side) {

        if (side.equals("a")) {
            return segmentA();
        } else if (side.equals("b")) {
            return segmentB();
        } else if (side.equals("c")) {
            return segmentC();
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

    public void drawTriangleSubdivisionFill(PApplet sketch, int depth){

        arcTriangleSubdivision(sketch, this, depth);

    };

    private void arcTriangleSubdivision(PApplet sketch, Triangle triangle, int depth){

        if (depth >= 0) {

            String side = Utils.getRandomTriangleSide();
            PVector pointOnSide = triangle.getSegment(side).getMidPoint();

            // FIXME: This needs to be done to resolve canot resolve symbol error
            Triangle left = new Triangle();
            Triangle right = new Triangle();
            sketch.stroke(sketch.random(255), sketch.random(255), sketch.random(255));
            if(side.equals("a")){

                new Line(triangle.A,pointOnSide).drawWithEndpoints(sketch,5);
                left = new Triangle(triangle.A,pointOnSide,triangle.C);
                right = new Triangle(triangle.A,triangle.B,pointOnSide);
            } else if (side.equals("b")){
                new Line(triangle.B,pointOnSide).drawWithEndpoints(sketch,5);
                left = new Triangle(triangle.A, triangle.B, pointOnSide);
                right = new Triangle(pointOnSide, triangle.B, triangle.C);
            } else if (side.equals("c")){
                new Line(triangle.C,pointOnSide).drawWithEndpoints(sketch,5);
                left = new Triangle(pointOnSide, triangle.B, triangle.C);
                right = new Triangle(triangle.A, pointOnSide, triangle.C);
            } else {
                throw new IllegalArgumentException();
            }

            System.out.println(String.format("Left - Depth: %d",depth-1));
            arcTriangleSubdivision(sketch, left ,depth-1);

            System.out.println(String.format("Right - Depth: %d",depth-1));
            arcTriangleSubdivision(sketch, right ,depth-1);

        }

    };

}
