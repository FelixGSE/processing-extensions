package shapes;

import processing.core.PVector;

public class Circle {

    public PVector center;
    public double radius;

    public Circle(PVector circleCenter, double circleRadius ) {
        center = circleCenter; 
        radius = circleRadius;
      };
    
    public double area(){

      
      return Math.pow(this.radius, 2) * Math.PI;
      
      
      
    }

    
}
