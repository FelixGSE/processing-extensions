package shapes;

import processing.core.PVector;

public class NotReallyAnArc {

    public PVector A;
    public PVector B;
    public PVector C;

    public NotReallyAnArc(PVector A, PVector B, PVector C) {
        this.A = A; 
        this.B = B;
        this.C = C;
      };

    
    public void shiftByVector(PVector V){

      this.A.add(V);
      this.B.add(V);
      this.C.add(V);

    };

    
}