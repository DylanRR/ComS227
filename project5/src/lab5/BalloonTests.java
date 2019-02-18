package lab5;
// import balloon.Balloon;	//Pass
// import balloon1.Balloon;	//Fail
// import balloon2.Balloon;	//Fail
// import balloon3.Balloon;	//Fail
import balloon4.Balloon;	//Fail
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class BalloonTests {
	private static final double EPSILON = 10e-07;
	 
    private Balloon bb;

    @Test
    public void testSetUp(){
    	Balloon b = new Balloon(-5);
    	assertEquals(0.0, bb.getRadius(), EPSILON);
    	assertEquals(false, bb.isPopped());
    }
    
    
    @Before    
    public void setup(){
      bb = new Balloon(10);
    }
    
    @Test
    public void testInitialRadius(){
    	assertEquals(0.0, bb.getRadius(), EPSILON);
    }
    
    @Test
    public void testInitialIsPopped(){
    	assertEquals(false, bb.isPopped());
    }
    
    @Test
    public void testBlow(){
    	bb.blow(5);
    	assertEquals(5.0, bb.getRadius(), EPSILON);
    	assertEquals(false, bb.isPopped());
    	bb.blow(-1);
    	assertEquals(5.0, bb.getRadius(), EPSILON);
    	assertEquals(false, bb.isPopped());
    	bb.blow(5);
    	assertEquals(10.0, bb.getRadius(), EPSILON);
    	assertEquals(false, bb.isPopped());
    }
    
    @Test
    public void testOverBlow(){
    	bb.blow(5);
    	assertEquals(5.0, bb.getRadius(), EPSILON);
    	assertEquals(false, bb.isPopped());
    	bb.blow(7);
    	assertEquals(0.0, bb.getRadius(), EPSILON);
    	assertEquals(true, bb.isPopped());
    }
    
    @Test
    public void testDeflate(){
    	bb.blow(5);
    	assertEquals(5.0, bb.getRadius(), EPSILON);
    	assertEquals(false, bb.isPopped());
    	bb.deflate();
    	assertEquals(0.0, bb.getRadius(), EPSILON);
    	assertEquals(false, bb.isPopped());
    	bb.blow(5);
    	assertEquals(5.0, bb.getRadius(), EPSILON);
    	assertEquals(false, bb.isPopped());
    }
    
    @Test
    public void testPop(){
    	bb.blow(5);
    	assertEquals(5.0, bb.getRadius(), EPSILON);
    	assertEquals(false, bb.isPopped());
    	bb.pop();
    	assertEquals(0.0, bb.getRadius(), EPSILON);
    	assertEquals(true, bb.isPopped());
    	bb.blow(5);
    	assertEquals(0.0, bb.getRadius(), EPSILON);
    	assertEquals(true, bb.isPopped());
    }
}
