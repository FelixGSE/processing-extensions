package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

interface Shape {
    // public ArrayList<PVector> vertexList;

    public void drawRandomPointInside(PApplet myParent);
    public void drawRandomPointInside(PApplet myParent, float offSet);
    public void draw(PApplet sketch);
    public void shift(PVector mover);
    public void shiftMe(PVector mover);
    public void scale(PVector mover);
    public void scaleMe(PVector mover);
    public void rotate(PVector mover);
    public void rotateMe(PVector mover);
    public void rotateObjectOnCircle(PVector mover);
    public void rotateMeOnCircle(PVector mover);
    public void rotateOnEllipse(PVector mover);
    public void rotateMeOnEllipse(PVector mover);
    public void randomFill(PVector mover);
    public void drawRandomFille(PVector mover);

}
