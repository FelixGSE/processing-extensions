package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

public class NotReallyAnArc {

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

    public NotReallyAnArc shift(PVector mover) {
        PVector newA = A.copy().add(mover);
        PVector newB = B.copy().add(mover);
        PVector newC = C.copy().add(mover);
        Circle newRefCircle = refCircle.getShifted(mover);
        return new NotReallyAnArc(newA, newB, newC, newRefCircle);

    };

    public Line segmentC(){
        return new Line(A,B);
    };

    public Line segmentB(){
        return new Line(A,C);
    };

    public void draw(PApplet sketch) {
        sketch.line(A.x, A.y, B.x, B.y);
        sketch.line(A.x, A.y, C.x, C.y);

        sketch.arc(refCircle.center.x,
                refCircle.center.y, refCircle.radius * 2, refCircle.radius * 2, refCircleAngleStart, refCircleAngleEnd);
    };


}