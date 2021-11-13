package shapes;

import processing.core.PApplet;
import processing.core.PVector;


public class Utils {

    public static float round(double number, int digits) {
        double digit = Math.pow(10, digits);
        double rounded = Math.round(number * digit) / digit;
        return doubleToFloat(rounded);

    }

    public static double degreeToRadians(double degree) {
        return degree * Math.PI / 180;
    }

    ;

    public static double radiansToDegree(double radians) {
        return radians * 180 / Math.PI;
    }

    ;

    public static double euclideanDistance(PVector A, PVector B) {
        return A.dist(B);
    }

    ;

    public static float doubleToFloat(double doubleValue) {
        return (float) doubleValue;

    }

    ;

    public static double floatToDouble(float floatValue) {
        return (double) floatValue;

    }

    ;

    public static PVector pointOnCircle(Circle circle, double angle) {

        double x = circle.center.x + circle.radius * Math.cos(angle);
        double y = circle.center.y + circle.radius * Math.sin(angle);

        return new PVector(doubleToFloat(x), doubleToFloat(y));
    }

    ;

    public static PVector randomPointInCircle(Circle circle) {

        double r = circle.radius * Math.sqrt(Math.random());
        double theta = Math.random() * 2 * Math.PI;

        float x = doubleToFloat(circle.center.x + r * Math.cos(theta));
        float y = doubleToFloat(circle.center.x + r * Math.sin(theta));

        return new PVector(x, y);

    }

    ;

    public static float angleForPointOnCircle(Circle circle, PVector point) {

        double angle = Math.acos(((point.x - circle.center.x) / circle.radius));

        return doubleToFloat(angle);
    }

    ;

    public static PVector lineMidPoint(PVector A, PVector B) {
        float x = doubleToFloat(0.5 * A.x + 0.5 * B.x);
        float y = doubleToFloat(0.5 * A.y + 0.5 * B.y);

        return new PVector(x, y);

    }

    public static PVector randomPointOnLine(PVector A, PVector B) {

        double randomSplit = Math.random();
        float x = doubleToFloat((1 - randomSplit) * A.x + randomSplit * B.x);
        float y = doubleToFloat((1 - randomSplit) * A.y + randomSplit * B.y);

        return new PVector(x, y);

    }

    ;

    public static PVector shortenLine(PVector A, PVector B, double t) {

        float x = doubleToFloat(A.x + t * (B.x - A.x));
        float y = doubleToFloat(A.y + t * (B.y - A.y));

        return new PVector(x, y);

    }

    ;

    public static PVector lineIntersection(Line x, Line y) {


        float denominator = (x.start.x - x.end.x) * (y.start.y - y.end.y) - (x.start.y - x.end.y) * (y.start.x - y.end.x);

        float pX = (x.start.x * x.end.y - x.start.y * x.end.x) * (y.start.x - y.end.x) - (x.start.x - x.end.x) * (y.start.x * y.end.y - y.start.y * y.end.x);
        float pY = (x.start.x * x.end.y - x.start.y * x.end.x) * (y.start.y - y.end.y) - (x.start.y - x.end.y) * (y.start.x * y.end.y - y.start.y * y.end.x);

        return new PVector(pX / denominator, pY / denominator);
    }

    ;

    public static PVector oppositePointOnCircle(Circle circle, PVector B) {

        float x = 2 * circle.center.x - B.x;
        float y = 2 * B.y - circle.center.y;

        return new PVector(x, y);
    }

    ;


    public static boolean pointIsInCircle(Circle circle, PVector point) {

        return circle.center.dist(point) <= circle.radius;

    }

    public static boolean pointIsOutsideOfCircle(Circle circle, PVector point) {

        return circle.center.dist(point) >= circle.radius;

    }

    public static boolean pointIsOnCircle(Circle circle, PVector point) {

        return circle.center.dist(point) == circle.radius;


    }

    public static PVector randomPointInTriangle(Triangle triangle) {
        double r1 = Math.random();
        double r2 = Math.random();

        double sqrtR1 = Math.sqrt(r1);

        double x = (1 - sqrtR1) * triangle.A.x + (sqrtR1 * (1 - r2)) * triangle.B.x + (sqrtR1 * r2) * triangle.C.x;
        double y = (1 - sqrtR1) * triangle.A.y + (sqrtR1 * (1 - r2)) * triangle.B.y + (sqrtR1 * r2) * triangle.C.y;

        return new PVector(doubleToFloat(x), doubleToFloat(y));


    }

    public static void hLine(PApplet sketch, float y) {

        sketch.line(0, y, sketch.width, y);
    }

    ;

    public static void vLine(PApplet sketch, float x) {

        sketch.line(x, 0, 0, sketch.height);
    }

    ;

//    public static PVector perpendicularPointOnCircle(Circle circle, PVector point, boolean clockwise){
//
//        if(clockwise){
//            float counterClockwiseIndicator = 1;
//        } else {
//            float counterClockwiseIndicator = -1;
//        }
//
//        float dx = circle.center.x - point.x;
//        float dy = circle.center.y - point.y;
//
//        float d = doubleToFloat(Math.sqrt(dx*dx + dy*dy));
//
//        float newDX = dx / d;
//        float newDY = dy / d;
//
//        float finalX = point.x + counterClockwiseIndicator * (circle.radius/2)*newDX;
//        float finalY = point.y - counterClockwiseIndicator * (circle.radius/2)*newDY;
//
//        return PVector(finalX,finalY);
//    }
//
//    ;

    public static PVector rotatePointOnEllipse(Ellipse elipse, PVector point) {
        double radians = radiansToDegree(elipse.rotation);
        float cosA = doubleToFloat(Math.cos(radians));
        float sinA = doubleToFloat(Math.sin(radians));
        float dX = point.x - elipse.center.x;
        float dY = point.y - elipse.center.y;
        float newX = elipse.center.x + dX * cosA - dY * sinA;
        float newY = elipse.center.y + dX * sinA - dY * cosA;

        return new PVector(newX, newY);
    }

    ;

    public static PVector randomPointInArc(Ellipse ellipse, float start, float stop, float orbit_min, float orbit_max) {

        PApplet container = new PApplet();

        double a = container.random(start / 360, stop / 360) * 2 * Math.PI;
        float randomMin = doubleToFloat(Math.pow(orbit_min, 2));
        float randomMax = doubleToFloat(Math.pow(orbit_max, 2));
        double R = Math.sqrt(container.random(randomMin, randomMax));

        float x = doubleToFloat(R * Math.cos(a) * ellipse.width);
        float y = doubleToFloat(R * Math.sin(a) * ellipse.height);
        PVector pointOnEllipse = new PVector(x, y);

        if (ellipse.rotation > 0) {
            Ellipse innerEllipse = new Ellipse(ellipse.center, doubleToFloat(R), ellipse.width, ellipse.height, ellipse.rotation);
            PVector v = rotatePointOnEllipse(innerEllipse, pointOnEllipse);
            return v.add(ellipse.center);
        } else {
            pointOnEllipse.add(ellipse.center);
            return pointOnEllipse;
        }
    }

    public static boolean numberIsEven(Integer n) {

        return (n % 2) == 0;

    }

    ;

    public static void planetoPolarCoordinates(Circle circle, PVector PVector) {
        return;
    }

    ;

    public static void splitIntegerIntoRandomParts(Integer m, Integer n) {


        return;

    }

    ;

}

