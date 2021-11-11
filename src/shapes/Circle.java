package shapes;

public class Circle {

    Point center;
    double radius;

    public Circle(Point circleCenter, double circleRadius ) {
        center = circleCenter; 
        radius = circleRadius;
      };
    
    public double area(){

      
      return Math.pow(this.radius, 2) * Math.PI;
      
      
      
    }

    
}
