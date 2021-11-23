package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.Random;

public class NotReallyAnArc implements Shape {

    public PVector A;
    public PVector B;
    public PVector C;
    public Circle refCircle;
    public float refCircleAngleStart;
    public float refCircleAngleEnd;

    public NotReallyAnArc(PVector A, PVector B, PVector C, Circle refCircle) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.refCircle = refCircle;
        this.refCircleAngleStart = Utils.angleForPointOnCircleInDegrees(refCircle, B);
        this.refCircleAngleEnd = Utils.angleForPointOnCircleInDegrees(refCircle, C);
    }

    ;

    public double a() {
        return (refCircleAngleEnd - refCircleAngleStart) * refCircle.radius;
    }

    ;

    public double b() {
        return segmentB().length();
    }

    ;

    public double c() {
        return segmentC().length();
    }

    ;

    public String getShortestSide() {

        float[] segmentLengths = new float[]{Utils.doubleToFloat(a()), Utils.doubleToFloat(b()), Utils.doubleToFloat(c())};


        int index = 0;
        float min = segmentLengths[index];

        for (int i = 1; i < segmentLengths.length; i++) {
            if (segmentLengths[i] <= min) {
                min = segmentLengths[i];
                index = i;
            }
        }
        if (index == 0) {
            return "a";
        } else if (index == 1) {
            return "b";
        } else {
            return "c";
        }


    }

    ;

    public String getLongestSide() {

        float[] segmentLengths = new float[]{Utils.doubleToFloat(a()), Utils.doubleToFloat(b()), Utils.doubleToFloat(c())};


        int index = 0;
        float max = segmentLengths[index];

        for (int i = 1; i < segmentLengths.length; i++) {
            if (segmentLengths[i] >= max) {
                max = segmentLengths[i];
                index = i;
            }
        }
        if (index == 0) {
            return "a";
        } else if (index == 1) {
            return "b";
        } else {
            return "c";
        }
    }

    ;

    public PVector getA() {
        return A.copy();
    }

    ;

    public PVector getB() {
        return B.copy();
    }

    ;

    public PVector getC() {
        return C.copy();
    }

    ;

    public NotReallyAnArc shift(PVector mover) {
        PVector newA = A.copy().add(mover);
        PVector newB = B.copy().add(mover);
        PVector newC = C.copy().add(mover);
        Circle newRefCircle = refCircle.getShifted(mover);
        return new NotReallyAnArc(newA, newB, newC, newRefCircle);

    }

    ;

    public PVector computeRandomPointOnSide(String side) {

        if (side.equals("a")) {
            return refCircle.computeRandomPointOnBetweenAngles(refCircleAngleStart, refCircleAngleEnd);
        } else if (side.equals("b")) {
            return segmentB().getRandomPointOn();
        } else if (side.equals("c")) {
            return segmentC().getRandomPointOn();
        } else {
            throw new IllegalArgumentException();
        }


    }

    ;

    public PVector computeMidPointOnSide(String side) {

        if (side.equals("a")) {
            return refCircle.getPointOnCircleForAngle(refCircleAngleEnd-refCircleAngleStart);
        } else if (side.equals("b")) {
            return segmentB().getMidPoint();
        } else if (side.equals("c")) {
            return segmentC().getMidPoint();
        } else {
            throw new IllegalArgumentException();
        }


    }

    ;

    public PVector computeRandomGaussianPointOnSide(String side, float sigma) {

        if (side.equals("a")) {
            return refCircle.computeRandomGaussianPointOnBetweenAngles(refCircleAngleStart, refCircleAngleEnd, sigma);
        } else if (side.equals("b")) {
            return segmentB().computeRandomGaussianPointOnLine(sigma);
        } else if (side.equals("c")) {
            return segmentC().computeRandomGaussianPointOnLine(sigma);
        } else {
            throw new IllegalArgumentException();
        }

    }

    ;


    public Line segmentC() {
        return new Line(A, B);
    }

    ;

    public Line segmentB() {
        return new Line(A, C);
    }

    ;

    public void draw(PApplet sketch) {
        sketch.line(A.x, A.y, B.x, B.y);
        sketch.line(A.x, A.y, C.x, C.y);

        sketch.arc(refCircle.center.x,
                refCircle.center.y, refCircle.radius * 2, refCircle.radius * 2, refCircleAngleStart, refCircleAngleEnd);
    }

    ;

    public void drawTriangleSubdivisionFill(PApplet sketch, int depth, float sigma) {

        arcTriangleSubdivision(sketch, this, depth, sigma);

    }

    ;

    private void arcTriangleSubdivision(PApplet sketch, Shape shape, int depth, float sigma) {
        String side;

        if (depth >= 0) {

            if (depth == 0) {
                side = "a";
            } else if (depth == 1) {
                side = "b";
            } else if (depth == 2) {
                side = "a";
            } else {
                side = Utils.getRandomTriangleSide();
            };

             side = Utils.getRandomTriangleSide();
//            side = "c";
            Random r = new Random();
            r.setSeed(1);
            PVector pointOnSide = shape.computeMidPointOnSide(side);
            // FIXME: This needs to be done to resolve canot resolve symbol error
            Shape left;
            Shape right;

            sketch.stroke(sketch.random(255), sketch.random(255), sketch.random(255));
            if (side.equals("a")) {
                sketch.stroke(255, 0, 0);
                new Line(shape.getA(), pointOnSide).drawWithEndpoints(sketch, 5);
                left = new NotReallyAnArc(shape.getA(), shape.getB(), pointOnSide, refCircle);
                right = new NotReallyAnArc(shape.getA(), pointOnSide, shape.getC(), refCircle);

            } else if (side.equals("b")) {
                sketch.stroke(0, 255, 0);
                new Line(shape.getB(), pointOnSide).drawWithEndpoints(sketch, 5);
                left = new NotReallyAnArc(pointOnSide, shape.getB(), shape.getC(), refCircle);
                right = new Triangle(shape.getA(), shape.getB(), pointOnSide);

            } else if (side.equals("c")) {
                sketch.stroke(0, 0, 255);
                new Line(shape.getC(), pointOnSide).drawWithEndpoints(sketch, 5);
                left = new Triangle(shape.getA(), pointOnSide, shape.getC());
                right = new NotReallyAnArc(pointOnSide, shape.getB(), shape.getC(), refCircle);

            } else {
                throw new IllegalArgumentException();
            }

            System.out.println(String.format("Left - Depth: %d", depth - 1));
            arcTriangleSubdivision(sketch, left, depth - 1, sigma);

            System.out.println(String.format("Right - Depth: %d", depth - 1));
            arcTriangleSubdivision(sketch, right, depth - 1, sigma);

        }

    }

    ;

}

