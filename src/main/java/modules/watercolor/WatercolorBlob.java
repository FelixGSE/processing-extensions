package modules.watercolor;
// https://github.com/sighack/watercolor-simulation

import modules.shapes.Polygon;
import modules.shapes.Line;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class WatercolorBlob {

    private final Polygon basePolygon;
    private ArrayList<Polygon> deformedPolygons;

    public WatercolorBlob(Polygon basePolygon) {
        this.basePolygon = basePolygon;
    }


    public Polygon getBasePolygon() {
        return basePolygon;
    }

    public ArrayList<Polygon> getDeformedPolygons() {
        return deformedPolygons;
    }

    public void createDeformedPolygons(int n, int depth){

        for(int i=0;i<n;i++){
            deformedPolygons.add(getBasePolygon().deform(depth));
        }
    }

    public void draw(PApplet sketch){
        for( Polygon polygon : deformedPolygons){
            polygon.draw(sketch);
        }


    }
}
