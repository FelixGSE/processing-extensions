package shapes;

public class Utils {

    
    public static double degreeToRadians(double degree){
        
        double radians = degree * Math.PI / 180;

        return radians;

    };

    public static double euclideanDistance(Point A, Point B){
        
        double distance = Math.sqrt( Math.pow( (A.x - B.x) , 2 ) + Math.pow( (A.y - B.y), 2) );
        
        return distance;

    };

    public static double radiansToDegree(double radians){
        
        double degrees = radians * 180 / Math.PI;

        return degrees;

    };

    public static Point pointOnCircle(Circle circle, double angle){
        
        double x = circle.center.x + circle.radius * Math.cos(angle);
        double y = circle.center.y + circle.radius * Math.sin(angle);
    
        return new Point(x,y);
    };

    public static double angleForPointOnCircle(Circle circle, Point point){
        
        double angle = Math.acos( ( ( point.x - circle.center.x ) / circle.radius ) );
        
        return angle;
    };
    
    public static void planetoPolarCoordinates(Circle circle, Point point){
        return ;
    };



}

