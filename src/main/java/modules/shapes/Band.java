package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Band {
    public ArrayList<PVector> vertexList;
    public ArrayList<PVector> shiftedVertexList;
    public Band(ArrayList<PVector> vertexList, PVector shift) {
        this.vertexList = vertexList;

        this.shiftedVertexList = Utils.shiftVertexList(vertexList,shift);
    }

//    public void drawInLines(PApplet sktech){
//
//        for(i=0;)
//
//
//
//    }
//


}
