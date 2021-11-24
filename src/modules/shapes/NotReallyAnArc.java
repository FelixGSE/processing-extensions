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
        if(refCircleAngleEnd == 0 && refCircleAngleStart<360){
            this.refCircleAngleEnd = 360;
        }

//        if(refCircleAngleStart> refCircleAngleEnd){
//            throw new IllegalArgumentException("refCircleAngleEnd is larger than refCircleAngleStart");
//        }
    }

    ;
    public String toString(){
        return String.format("NotReallyAnArc(A=(%f,%f),B=(%f,%f),C=(%f,%f),start=%f,stop=%f)",A.x,A.y,B.x,B.y,C.x,C.y,this.refCircleAngleStart,this.refCircleAngleEnd);
    };

    public double a() {
        return (Math.toRadians(refCircleAngleEnd) - Math.toRadians(refCircleAngleStart)) * refCircle.radius;
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

            return refCircle.getPointOnCircleForAngle((refCircleAngleEnd+refCircleAngleStart)/2 );
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

        float startInRadians = Utils.doubleToFloat(Utils.degreeToRadians(refCircleAngleStart));
        float endInRadians = Utils.doubleToFloat(Utils.degreeToRadians(refCircleAngleEnd));


        sketch.arc(refCircle.center.x,
                refCircle.center.y, refCircle.radius * 2, refCircle.radius * 2, startInRadians, endInRadians);
    }

    ;

    public void drawTriangleSubdivisionFill(PApplet sketch, int depth, float sigma) {

        arcTriangleSubdivision(sketch, this, depth, sigma);

    }

    ;

    private void arcTriangleSubdivision(PApplet sketch, Shape shape, int depth, float sigma) {
//        String side;

        if (depth >= 0) {

//            if (depth == 0) {
//                side = "a";
//            } else if (depth == 1) {
//                side = "b";
//            } else if (depth == 2) {
//                side = "c";
//            } else {
//                side = Utils.getRandomTriangleSide();
//            };

//             side = Utils.getRandomTriangleSide();
//            side = "a";
//            Random r = new Random();
//            r.setSeed(1);
            String side = shape.getLongestSide();
            PVector pointOnSide = shape.computeRandomGaussianPointOnSide(side,1);
            // FIXME: This needs to be done to resolve canot resolve symbol error
            Shape left = null;
            Shape right = null;
//            System.out.println("START");
//            System.out.printf("Depth: %d%n", depth);
//            System.out.println(shape.toString());
////            sketch.stroke(sketch.random(255), sketch.random(255), sketch.random(255));

            if(shape instanceof Triangle) {

                if (side.equals("a")) {
                    new Line(shape.getA(), pointOnSide).draw(sketch);
                    left = new Triangle(shape.getA(), pointOnSide, shape.getC());
                    right = new Triangle(shape.getA(), shape.getB(), pointOnSide);
                } else if (side.equals("b")) {
                    new Line(shape.getB(), pointOnSide).draw(sketch);
                    left = new Triangle(shape.getA(), shape.getB(), pointOnSide);
                    right = new Triangle(pointOnSide, shape.getB(), shape.getC());
                } else if (side.equals("c")) {
                    new Line(shape.getC(), pointOnSide).draw(sketch);
                    left = new Triangle(pointOnSide, shape.getB(), shape.getC());
                    right = new Triangle(shape.getA(), pointOnSide, shape.getC());
                }
            } else if(shape instanceof NotReallyAnArc) {

                    if (side.equals("a")) {
//                        sketch.stroke(255, 0, 0);
//                        System.out.println("START");
                        new Line(shape.getA(), pointOnSide).toString();
                        new Line(shape.getA(), pointOnSide).draw(sketch);
                        left = new NotReallyAnArc(shape.getA(), pointOnSide, shape.getC(), refCircle);
                        right = new NotReallyAnArc(shape.getA(), shape.getB(), pointOnSide, refCircle);

                    } else if (side.equals("b")) {
//                        sketch.stroke(0, 255, 0);
                        new Line(shape.getB(), pointOnSide).draw(sketch);
                        left = new Triangle(shape.getA(), shape.getB(), pointOnSide);
                        right = new NotReallyAnArc(pointOnSide, shape.getB(), shape.getC(), refCircle);

                    } else if (side.equals("c")) {
//                        sketch.stroke(0, 0, 255);
                        new Line(shape.getC(), pointOnSide).draw(sketch);
                        left = new NotReallyAnArc(pointOnSide, shape.getB(), shape.getC(), refCircle);
                        right = new Triangle(shape.getA(), pointOnSide, shape.getC());

                    } else {
                        throw new IllegalArgumentException();
                    }
            }

//            System.out.printf("Left - Depth: %d%n", depth);
            arcTriangleSubdivision(sketch, left, depth - 1, sigma);

//            System.out.printf("Right - Depth: %d%n", depth);
            arcTriangleSubdivision(sketch, right, depth - 1, sigma);
//            System.out.println("DONE");

        }

    }

    ;

}

