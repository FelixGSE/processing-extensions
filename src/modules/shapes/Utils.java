package modules.shapes;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;


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
        double dbl = 0.0;
        return floatValue + dbl;

//         return (double) floatValue;
//
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

    public static PVector lineMidPoint(Line line) {
        float x = doubleToFloat(0.5 * line.start.x + 0.5 * line.end.x);
        float y = doubleToFloat(0.5 * line.start.y + 0.5 * line.end.y);

        return new PVector(x, y);

    }

    public static PVector uniformRandomPointOnLine(PVector A, PVector B) {

        double randomSplit = Math.random();
        float x = doubleToFloat((1 - randomSplit) * A.x + randomSplit * B.x);
        float y = doubleToFloat((1 - randomSplit) * A.y + randomSplit * B.y);

        return new PVector(x, y);

    }

    ;

    public static PVector shortenLineFromOrigin(Line line, double t) {

        float x = doubleToFloat(line.start.x + t * (line.end.x - line.start.x));
        float y = doubleToFloat(line.start.y + t * (line.end.y - line.start.y));

        return new PVector(x, y);

    }
    ;

    public static Line shortenLineFromBothEnds(Line line, double t) {
        double split = t/2;

        PVector startPoint = shortenLineFromOrigin(line,split);
        PVector endPoint = shortenLineFromOrigin(line,1- split);

        return new Line(startPoint, endPoint);

    }

    public static PVector lineIntersection(Line x, Line y) {

        float denominator = (x.start.x - x.end.x) * (y.start.y - y.end.y) - (x.start.y - x.end.y) * (y.start.x - y.end.x);

        float pX = (x.start.x * x.end.y - x.start.y * x.end.x) * (y.start.x - y.end.x) - (x.start.x - x.end.x) * (y.start.x * y.end.y - y.start.y * y.end.x);
        float pY = (x.start.x * x.end.y - x.start.y * x.end.x) * (y.start.y - y.end.y) - (x.start.y - x.end.y) * (y.start.x * y.end.y - y.start.y * y.end.x);

        return new PVector(pX / denominator, pY / denominator);
    }

    ;

    public static Line makePositiveRealLine(float upper) {
        return new Line(new PVector(0, 0), new PVector(upper, 0));
    }

    ;

    public static ArrayList<PVector> divideLineIntoEqualParts(Line line, int n) {
        double a = 1.0 / n;
        ArrayList<PVector> outputs = new ArrayList<>();
        for (int i = n; i >= 0; i--) {
            double scalingFactor = 1 - i * a;
            PVector point = shortenLineFromOrigin(line, scalingFactor);
            outputs.add(point);
        }
        ;

        return outputs;
    }

    ;

    public static PVector randomPointOnLine(Line line){
        return shortenLineFromOrigin(line,Math.random());

    }

    public static double myEucledianDistance(PVector A, PVector B){
        return Math.sqrt( Math.pow( (A.x - B.x) , 2 ) + Math.pow( (A.y - B.y), 2) );
    };

    public static ArrayList<Line> divideLineIntoUniformRandomParts(Line line, int depth){

        ArrayList<Line> lines = new ArrayList<>();
        lines.add(line);

        uniformSubdivideLine(lines,line,depth);

        return lines;

    }

    private static void uniformSubdivideLine(ArrayList<Line> lineList,Line line,int depth) {
        if (depth >= 0) {
            PVector midpoint = line.getMidPoint();

            /* Move the midpoint by a Gaussian variance */
            float nx = doubleToFloat(midpoint.x + Math.random());
            float ny = doubleToFloat(midpoint.y + Math.random());


            /* Add two new edges which are recursively subdivided */
            Line leftLine = new Line(new PVector(line.start.x, line.start.y), new PVector(nx, ny));
            uniformSubdivideLine(lineList, leftLine, depth - 1);
            lineList.add(leftLine);
            Line rightLine = new Line(new PVector(nx, ny), new PVector(line.end.x, line.end.y));
            uniformSubdivideLine(lineList, rightLine, depth - 1);

        }
    }
            ;



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

    public static void drawPoint(PApplet sketch, PVector point, float size) {

        sketch.circle(point.x,point.y,size);
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

    public static PVector rotatePointOnEllipse(Ellipse ellipse, PVector point) {
        double radians = radiansToDegree(ellipse.rotation);
        float cosA = doubleToFloat(Math.cos(radians));
        float sinA = doubleToFloat(Math.sin(radians));
        float dX = point.x - ellipse.center.x;
        float dY = point.y - ellipse.center.y;
        float newX = ellipse.center.x + dX * cosA - dY * sinA;
        float newY = ellipse.center.y + dX * sinA - dY * cosA;

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
            Ellipse innerEllipse = new Ellipse(ellipse.center, ellipse.width, ellipse.height, ellipse.rotation);
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

    public static ArrayList<PVector> shiftVertexList(ArrayList<PVector> vertexList, PVector shift ){
        ArrayList<PVector> shifted = new ArrayList<>();

        for(int i = 0; i <= vertexList.size(); i++){
            PVector shiftedVertex = vertexList.get(i).copy().add(shift);
            shifted.add(shiftedVertex);
        }

        return shifted;
    }
    ;

}

