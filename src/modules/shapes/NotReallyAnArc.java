package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Random;

public class NotReallyAnArc implements Shape {

    public PVector A;
    public PVector B;
    public PVector C;
    public Circle refCircle;
    public float refCircleAngleStart;
    public float refCircleAngleEnd;
    public boolean isCurved;

    public NotReallyAnArc(PVector A, PVector B, PVector C, Circle refCircle) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.refCircle = refCircle;
        this.refCircleAngleStart = Utils.angleForPointOnCircleInDegrees(refCircle, B);
        this.refCircleAngleEnd = Utils.angleForPointOnCircleInDegrees(refCircle, C);
        this.isCurved = true;
    }

    public NotReallyAnArc(PVector A, PVector B, PVector C) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.isCurved = false;
    }

    public NotReallyAnArc() {
        this.A = new PVector(0, 0);
        this.B = new PVector(0, 0);
        this.C = new PVector(0, 0);
    }

    ;

    public String toString() {
        if (isCurved) {
            return String.format("NotReallyAnArc(A=(%f,%f),B=(%f,%f),C=(%f,%f),refCircleAngleStart=%f,refCircleAngleEnd=%f)", Utils.round(A.x,2), A.y, B.x, B.y, C.x, C.y, refCircleAngleStart, refCircleAngleEnd);
        } else {
            return String.format("NotReallyAnArc(A=(%f,%f),B=(%f,%f),C=(%f,%f))", A.x, A.y, B.x, B.y, C.x, C.y);
        }


    }
    ;

    public double a() {
        if (isCurved) {
            return refCircleAngleEnd - refCircleAngleStart * refCircle.radius;
        } else {
            return segmentA().length();
        }

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
            if (isCurved) {
                return refCircle.computeRandomPointOnBetweenAngles(refCircleAngleStart, refCircleAngleEnd);
            } else {
                return segmentA().getRandomPointOn();
            }
        } else if (side.equals("b")) {
            return segmentB().getRandomPointOn();
        } else if (side.equals("c")) {
            return segmentC().getRandomPointOn();
        } else {
            throw new IllegalArgumentException();
        }


    }

    ;

    public PVector computeRandomGaussianPointOnSide(String side, float sigma) {

        if (side.equals("a")) {
            if (isCurved) {
                return refCircle.computeRandomGaussianPointOnBetweenAngles(refCircleAngleStart, refCircleAngleEnd, sigma);
            } else {
                return segmentA().computeRandomGaussianPointOnLine(sigma);
            }
        } else if (side.equals("b")) {
            return segmentB().computeRandomGaussianPointOnLine(sigma);
        } else if (side.equals("c")) {
            return segmentC().computeRandomGaussianPointOnLine(sigma);
        } else {
            throw new IllegalArgumentException();
        }

    }

    ;

    public Line segmentA() {
        return new Line(B, C);
    }

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
        if (isCurved) {
            sketch.arc(refCircle.center.x,
                    refCircle.center.y, refCircle.radius * 2, refCircle.radius * 2, refCircleAngleStart, refCircleAngleEnd);
        } else {
            sketch.line(B.x, B.y, C.x, C.y);
        }
    }

    ;

    public void drawTriangleSubdivisionFill(PApplet sketch, int depth, float sigma) {

        arcTriangleSubdivision(sketch, this, depth, sigma);

    }

    ;

    public void test(Shape shape) {
        if (shape instanceof Triangle) {
            System.out.println("Triangle");
        } else if (shape instanceof NotReallyAnArc) {
            System.out.println("NotReallyArc");
        }


    }

    ;


    private void arcTriangleSubdivision(PApplet sketch, NotReallyAnArc arc, int depth, float sigma) {
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
            }
            ;

//            String side = Utils.getRandomTriangleSide();
//            String side = "b";
            Random r = new Random();
            r.setSeed(1);
            PVector pointOnSide = arc.computeRandomGaussianPointOnSide(side, sigma);
            // FIXME: This needs to be done to resolve canot resolve symbol error

            NotReallyAnArc left = new NotReallyAnArc();
            NotReallyAnArc right = new NotReallyAnArc();
            System.out.println(String.format("START"));
            System.out.println(String.format("Parent: %s / %d",arc.toString(), depth));
            System.out.println(String.format("Point(X=%f,Y=%f)", pointOnSide.x,pointOnSide.y));
            sketch.stroke(sketch.random(255), sketch.random(255), sketch.random(255));
            if (side.equals("a")) {
                sketch.stroke(255, 0, 0);
//                System.out.println(String.format("A %d - Point X : %f", depth, pointOnSide.x));
//                System.out.println(String.format("A %d - Point Y : %f", depth, pointOnSide.x));

                System.out.println("DRAWING LINE");
                new Line(arc.getA().copy(), pointOnSide).drawWithEndpoints(sketch, 5);
                System.out.println(new Line(arc.getA().copy(), pointOnSide).toString());
                System.out.println("DONE LINE");
                left = new NotReallyAnArc(arc.getA(), pointOnSide, arc.getC(), refCircle);
                right = new NotReallyAnArc(arc.getA(), arc.getB(), pointOnSide, refCircle);

            } else if (side.equals("b")) {
                sketch.stroke(0, 255, 0);
//                System.out.println(String.format("B %d - Point X : %f", depth, pointOnSide.x));
//                System.out.println(String.format("B %d - Point Y : %f", depth, pointOnSide.x));

                System.out.println("DRAWING LINE");
                System.out.println(new Line(arc.getB(), pointOnSide).toString());
                new Line(arc.getB(), pointOnSide).drawWithEndpoints(sketch, 5);
                System.out.println("DONE LINE");
                left = new NotReallyAnArc(arc.getA(), arc.getB(), pointOnSide, refCircle);
                right = new NotReallyAnArc(pointOnSide, arc.getB(), arc.getC());

            } else if (side.equals("c")) {
                sketch.stroke(0, 0, 255);
//                System.out.println(String.format("B %d - Point X : %f", depth, pointOnSide.x));
//                System.out.println(String.format("B %d - Point Y : %f", depth, pointOnSide.x));
//
//                System.out.println(String.format("B %d - Point X : %f", depth, pointOnSide.x));
//                System.out.println(String.format("B %d - Point Y : %f", depth, pointOnSide.x));

                System.out.println("DRAWING LINE");
                System.out.println(new Line(arc.getC(), pointOnSide).toString());
                new Line(arc.getC(), pointOnSide).drawWithEndpoints(sketch, 5);
                System.out.println("DONE LINE");
                left = new NotReallyAnArc(pointOnSide, arc.getB(), arc.getC());
                right = new NotReallyAnArc(arc.getA(), pointOnSide, arc.getC(), refCircle);

            } else {
                throw new IllegalArgumentException();
            }

            System.out.println(String.format("Left - Depth: %d", depth));
            arcTriangleSubdivision(sketch, left, depth - 1, sigma);

            System.out.println(String.format("Right - Depth: %d", depth ));
            arcTriangleSubdivision(sketch, right, depth - 1, sigma);

            System.out.println(String.format("DONE - Depth: %d", depth ));
        }

    }

    ;


    public ArrayList<Shape> getTriangleSubdivsion(PApplet sketch, Shape shape, int depth, float sigma) {
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
            }
            ;

            Random r = new Random();
            r.setSeed(1);
            PVector pointOnSide = shape.computeRandomGaussianPointOnSide(side, 0);
            // FIXME: This needs to be done to resolve canot resolve symbol error
            Shape left;
            Shape right;
            ArrayList<Shape> al = new ArrayList<>();

            if (side.equals("a")) {

                left = new NotReallyAnArc(shape.getA(), pointOnSide, shape.getC(), refCircle);
                right = new NotReallyAnArc(shape.getA(), shape.getB(), pointOnSide, refCircle);

            } else if (side.equals("b")) {


                left = new NotReallyAnArc(shape.getA(), shape.getB(), pointOnSide, refCircle);
                right = new Triangle(pointOnSide, shape.getB(), shape.getC());

            } else if (side.equals("c")) {


                left = new Triangle(pointOnSide, shape.getB(), shape.getC());
                right = new NotReallyAnArc(shape.getA(), pointOnSide, shape.getC(), refCircle);

            } else {
                throw new IllegalArgumentException();
            }

            al.add(left);
            al.add(right);
            return al;
        }

        return new ArrayList<>();

    }

}

