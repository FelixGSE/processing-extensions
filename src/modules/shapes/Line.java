package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;
import java.util.ArrayList;

public class Line {

    public PVector start;
    public PVector end;
    public ArrayList<PVector> pointList;

    public Line(PVector start, PVector end) {
        this.start = start;
        this.end = end;
        ArrayList<PVector> pointList = new ArrayList<>();
    }

    public Line shift(PVector mover){
        PVector newStart = start.copy().add(mover);
        PVector newEnd = end.copy().add(mover);
        return new Line(newStart,newEnd);

    }
    public void draw(PApplet sketch){
        sketch.line(start.x,start.y,end.x,end.y);
    }

}
