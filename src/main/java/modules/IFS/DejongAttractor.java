package modules.IFS;

import modules.shapes.Utils;
import processing.core.PApplet;
import processing.core.PVector;

public class DejongAttractor{

    private float x;
    private float y;
    private PVector location;
    private float a;
    private float b;
    private float c;
    private float d;

    public DejongAttractor(float x, float y, PVector location, float a, float b, float c, float d){
        this.x = x;
        this.y = y;
        this.location = location;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public DejongAttractor(PVector location, float a, float b, float c, float d){
        this.x = 0;
        this.y = 0;
        this.location = location;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }

    public float getD() {
        return d;
    }

    public void setD(float d) {
        this.d = d;
    }

    public PVector computeNext(){

        float xNext = PApplet.sin(getA()*getY()) - PApplet.cos(getB()*getX());
        float yNext = PApplet.sin(getC()*getX()) - PApplet.cos(getD()*getY());
        setX(xNext);
        setY(yNext);
        return new PVector(xNext,yNext);

    }

    public void draw(PApplet sketch, int maxIterations, float scale){

        sketch.pushMatrix();
        sketch.translate(location.x,location.y);
        for(int i=0; i<=maxIterations;i++){
            PVector nextPoint = computeNext();
            sketch.point(nextPoint.x * scale,nextPoint.y * scale);
        }
        sketch.popMatrix();

    }


}

