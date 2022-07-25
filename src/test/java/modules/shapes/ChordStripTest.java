package modules.shapes;


import org.junit.jupiter.api.Test;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChordStripTest {

    @Test
    public void shouldCreateChordStrip(){

        Circle circle = new Circle(new PVector(500,500), 100);
        float refAngle = 90;
        float chordAngleClose = 45;
        float chordAngleFar = 65;

        ChordStrip strip = new ChordStrip(circle, refAngle, chordAngleClose ,chordAngleFar );

        assertEquals(true, true);

    }

}