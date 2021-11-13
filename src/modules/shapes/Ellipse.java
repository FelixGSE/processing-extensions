package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

public class Ellipse {

    public PVector center;
    public float radius;
    public float height;
    public float width;
    public float rotation;

    public Ellipse(PVector center, float radius, float width, float height, float rotation) {
        this.center = center;
        this.radius = radius;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
    }

    public Ellipse shift(PVector mover){
        PVector newCenter = center.copy().add(mover);
        return new Ellipse(newCenter,radius,width,height,rotation);

    }

    public void draw(PApplet sketch){

        sketch.ellipse(center.x,center.y,width,height);

    }

}
