package test.shapes;
import modules.shapes.NotReallyAQuadrilateral;
import org.junit.Test;
import processing.core.PVector;

class NotReallyAQuadrilateralTest {


    @Test
    public void shouldCreateObject(){

        //
        PVector A = new PVector(0,0);
        PVector B = new PVector(100,0);
        PVector C = new PVector(100,100);
        PVector D = new PVector(0,100);

        NotReallyAQuadrilateral notReallyAQuadrilateral = new NotReallyAQuadrilateral(A,B,C,D);

    }


}