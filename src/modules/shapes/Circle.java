package modules.shapes;

import processing.core.PVector;
import processing.core.PApplet;

public class Circle{

    public PVector center;
    public float radius;

    public Circle(PVector circleCenter, float circleRadius ) {
        center = circleCenter;
        radius = circleRadius;
    };
    
    public double area(){

      return Math.pow(this.radius, 2) * Math.PI;

    }

    public Circle shift(PVector mover){
        PVector newCenter = center.copy().add(mover);
        return new Circle(newCenter,radius);

    }

    public void draw(PApplet sketch){

        sketch.circle(center.x,center.y,radius * 2);
    }

    
}
