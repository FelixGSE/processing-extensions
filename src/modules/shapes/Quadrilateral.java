package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

import javax.swing.text.Segment;

public class Quadrilateral {

    public PVector A;
    public PVector B;
    public PVector C;
    public PVector D;

    public Quadrilateral(PVector A, PVector B, PVector C, PVector D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
    }

    public Quadrilateral() {
        this.A = new PVector(0,0);
        this.B = new PVector(0,0);
        this.C = new PVector(0,0);
        this.D = new PVector(0,0);
    };

    public String toString() {
        return String.format("Quadrilateral(A=(%f,%f),B=(%f,%f),C=(%f,%f),D(%f,%f))", A.x, A.y, B.x, B.y, C.x, C.y,D.x,D.y);
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

    public PVector getD() {
        return D.copy();
    }

    public double a() {
        return Utils.floatToDouble(A.dist(this.B));

    }

    ;

    public double b() {

        return Utils.floatToDouble(B.dist(this.C));

    }

    ;

    public double c() {

        return Utils.floatToDouble(C.dist(this.D));

    }

    ;

    public double d() {

        return Utils.floatToDouble(D.dist(this.A));

    }

    ;

    public Line segmentA() {
        return new Line(A, B);

    }

    ;

    public Line segmentB() {
        return new Line(B, C);

    }

    ;

    public Line segmentC() {
        return new Line(C, D);

    }

    ;

    public Line segmentD() {
        return new Line(D, A);

    }

    ;

    public Line getSegment(String side) {
        if(side.equals("a")){
            return segmentA();
        } else if(side.equals("b")){
            return segmentB();
        } else if(side.equals("c")) {
            return segmentC();
        } else {
            return segmentD();
        }
    }

    ;

    public String getOppositeSide(String side){

        if(side.equals("a")){
            return "c";
        } else if(side.equals("b")){
            return "d";
        } else if(side.equals("c")){
            return "a";
        } else if(side.equals("d")){
            return "b";
    } else {
            return "";
        }

    };

    public String getLongestSide() {

        float[] segmentLengths = new float[]{Utils.doubleToFloat(a()), Utils.doubleToFloat(b()), Utils.doubleToFloat(c()),Utils.doubleToFloat(d())};


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
        } else if (index == 2) {
            return "c";

        } else {
         return "d";
        }
    }

    ;
    public void draw(PApplet sketch) {
        sketch.quad(A.x,A.y,B.x,B.y,C.x,C.y,D.x,D.y);
    }
    public void drawQuadSubdivision(PApplet sketch, int depth){

        drawQuadSubdivisionSplit(sketch, this, depth);

    };


    private void drawQuadSubdivisionSplit(PApplet sketch, Quadrilateral quad, int depth){
        if(Math.random() < 0.01){
            return ;
        }
        if (depth >= 0) {
            String side;

            side = quad.getLongestSide();
            PVector pointOnSide = quad.getSegment(side).computeRandomGaussianPointOnLine(1);
            String oppositeSide = quad.getOppositeSide(side);
            PVector pointOnOppositeSite = quad.getSegment(oppositeSide).computeRandomGaussianPointOnLine(1);

            Quadrilateral left;
            Quadrilateral right;

            if(side.equals("a")){

                new Line(pointOnSide,pointOnOppositeSite).draw(sketch);
                left = new Quadrilateral(pointOnSide,quad.getB(),quad.getC(),pointOnOppositeSite);
                right = new Quadrilateral(quad.getA(),pointOnSide,pointOnOppositeSite,quad.getD());
            } else if (side.equals("b")){

                new Line(pointOnSide,pointOnOppositeSite).draw(sketch);
                left = new Quadrilateral(pointOnOppositeSite,pointOnSide,quad.getC(),quad.getD());
                right = new Quadrilateral(quad.getA(),quad.getB(),pointOnSide,pointOnOppositeSite);
            } else if (side.equals("c")){

                new Line(pointOnSide,pointOnOppositeSite).draw(sketch);
                left = new Quadrilateral(quad.getA(),pointOnOppositeSite,pointOnSide,quad.getD());
                right = new Quadrilateral(pointOnOppositeSite,quad.getB(),quad.getC(),pointOnSide);
            } else {

                new Line(pointOnSide,pointOnOppositeSite).draw(sketch);
                left = new Quadrilateral(quad.getA(),quad.getB(),pointOnOppositeSite,pointOnSide);
                right = new Quadrilateral(pointOnSide,pointOnOppositeSite,quad.getC(),quad.getD());
            }


            drawQuadSubdivisionSplit(sketch, left ,depth-1);


            drawQuadSubdivisionSplit(sketch, right ,depth-1);


        }

    };

}

// Triangle split
// Band Split
// Box Split
// Random Fill
// RandomShade