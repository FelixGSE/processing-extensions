package shapes;

import processing.core.PVector;


public class Utils {

    public static double degreeToRadians(double degree){
        
        double radians = degree * Math.PI / 180;

        return radians;

    };

    public static double radiansToDegree(double radians){
        
        double degrees = radians * 180 / Math.PI;

        return degrees;

    };

    public static double euclideanDistance(PVector A, PVector B){
        
        double distance = Math.sqrt( Math.pow( (A.x - B.x) , 2 ) + Math.pow( (A.y - B.y), 2) );
        
        return distance;

    };

    public static float doubleToFloat(double doubleValue){
        return (float) doubleValue;

    };

    public static double floatToDouble(float floatValue){
        return (double) floatValue;

    };

    public static PVector pointOnCircle(Circle circle, double angle){
        
        double x = circle.center.x + circle.radius * Math.cos(angle);
        double y = circle.center.y + circle.radius * Math.sin(angle);
        
        return new PVector(doubleToFloat(x),doubleToFloat(y));
    };

    public static PVector randomPointInCircle(Circle circle){

        double r = circle.radius * Math.sqrt(Math.random());
        double theta = Math.random() * 2 * Math.PI;

        float x = doubleToFloat(circle.center.x + r * Math.cos(theta));
        float y = doubleToFloat(circle.center.x + r * Math.sin(theta));

        return new PVector(x,y);

    };

    public static double angleForPointOnCircle(Circle circle, PVector point){
        
        double angle = Math.acos( ( ( point.x - circle.center.x ) / circle.radius ) );
        
        return angle;
    };
    
    public static PVector randomPointOnLine(PVector A, PVector B){
        
        double randomSplit = Math.random();
        float x = doubleToFloat( (1 - randomSplit) * A.x + randomSplit * B.x ); 
        float y = doubleToFloat( (1 - randomSplit) * A.y + randomSplit * B.y );
        
        return new PVector(x,y);

    };

    public static PVector shortenLine(PVector A, PVector B, double t){
  
        float x = doubleToFloat(A.x + t * (B.x - A.x) );
        float y = doubleToFloat(A.y + t * (B.y - A.y) );

        return new PVector(x,y);
        
      };

    public static PVector lineIntersection(PVector A,PVector B, PVector C, PVector D){

        float denominator = (A.x - B.x) * (C.y - D.y ) - ( A.y - B.y ) * ( C.x - D.x );
        
        float pX = (A.x * B.y  - A.y * B.x) * (C.x  - D.x) - (A.x  - B.x) * (C.x  * D.y - C.y * D.x);
        float pY = (A.x * B.y - A.y  * B.x) * (C.y  - D.y) - (A.y  - B.y) * (C.x  * D.y - C.y * D.x);
        
        return new PVector(pX/denominator,pY/denominator);
    };


    public static PVector oppositePointOnCircle(PVector A, PVector B){
        
        float x = 2 * A.x - B.x;
        float y = 2 * B.y - A.y;

        return new PVector(x,y);
    }

    public static void splitIntegerIntoNParts(Integer m, Integer n){
       return;

    }
    

    public static void planetoPolarCoordinates(Circle circle, PVector PVector){
        return ;
    };

    public static void hLine(float y){

        return;
    };

    public static void vLine(float x){

        return;
    };

    public static boolean numberIsEven(Integer n){

        if ((n % 2) == 0) {
            return true;
         } else {
            return false;
         }

    };

}

