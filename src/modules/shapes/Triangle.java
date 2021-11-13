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

    public float a() {

        return C.dist(this.B);

    }

    public float b() {

        return A.dist(this.C);

    }

    public float c() {

        return A.dist(this.B);

    }

    public float U() {
        return a() + b() + c();
    }

    public float area() {
        return U() / 2;
    }

    public float alpha() {
        double angle = Math.acos((Math.pow(b(), 2) + Math.pow(c(), 2) - Math.pow(a(), 2) / (2 * b() * c())));
        return Utils.doubleToFloat(angle);

    }

    public float beta() {
        double angle = Math.acos((Math.pow(a(), 2) + Math.pow(c(), 2) - Math.pow(b(), 2) / (2 * a() * c())));
        return Utils.doubleToFloat(angle);

    }

    public float gamma() {
        double angle = Math.acos((Math.pow(a(), 2) + Math.pow(b(), 2) - Math.pow(c(), 2) / (2 * a() * b())));
        return Utils.doubleToFloat(angle);

    }

    public Line segmentA() {
        return new Line(B,C);

    }

    public Line segmentB() {
        return new Line(A,C);

    }

    public Line segmentC() {
        return new Line(A,B);

    }

    public float sumOfAngles() {

        return alpha() + beta() + gamma();

    }

    public PVector chooseRandomPointOnEdge(String side) {

        if(side.equals("a")){
            return Utils.randomPointOnLine(C, B);
        } else if (side.equals("b")) {
            return Utils.randomPointOnLine(A, C);

        } else if (side.equals("c")){
            return Utils.randomPointOnLine(A, B);
        } else {
            throw new IllegalArgumentException();
        }


    }

    public PVector computeRandomPointInTriangle() {

        return Utils.randomPointInTriangle(this);

    }

    public void drawRandomPointInTriangle(PApplet myParent) {
        PVector point = computeRandomPointInTriangle();
        myParent.circle(point.x, point.y, 10);

    }

    public Triangle shift(PVector mover){
        PVector newA = A.copy().add(mover);
        PVector newB = B.copy().add(mover);
        PVector newC = C.copy().add(mover);
        return new Triangle(newA,newB,newC);

    }

    public void draw(PApplet myParent) {

        myParent.triangle(this.A.x, this.A.y, this.B.x, this.B.y, this.C.x, this.C.y);

    }


}
