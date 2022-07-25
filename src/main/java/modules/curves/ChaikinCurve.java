package modules.curves;

import modules.shapes.Line;
import modules.shapes.Polygon;
import modules.shapes.Utils;
import processing.core.PApplet;

import java.util.ArrayList;

public class ChaikinCurve implements Curve {

    private Polygon fittedPolygon;

    public ChaikinCurve(Polygon polygon, int n, Polygon fittedPolygon) {

        ArrayList<Line> edges = polygon.getEdges();
        fitChaikinCurve(edges, n, polygon.getClosed());
    }


    private void fitChaikinCurve(ArrayList<Line> lines, int n, boolean closed) {

        for (int i = 0; i <= n; i++) {

            ArrayList<Line> shorts = new ArrayList<>();

            for (Line line : lines) {
                Line shortLine = Utils.shortenLineFromBothEnds(line, 0.5);
                shorts.add(shortLine);
            }

            lines = new ArrayList<>();

            for (int j = 0; j < shorts.size() - 1; j++) {
                Line thisLine = shorts.get(j);
                Line nextLine = shorts.get(j + 1);
                Line connectingLine = new Line(thisLine.getEnd(), nextLine.getStart());
                lines.add(thisLine);
                lines.add(connectingLine);
                if (j == shorts.size() - 2) {
                    lines.add(nextLine);
                }
            }
            if (closed) {
                Line endLine = new Line(shorts.get(shorts.size() - 1).getEnd(), lines.get(0).getStart());
                lines.add(endLine);
            }
        }

        this.fittedPolygon = Polygon.fromListOfLines(lines);

    }

    public Polygon getFittedPolygon() {
        return fittedPolygon;
    }


    public void draw(PApplet sketch){
        fittedPolygon.draw(sketch);

    }
}
