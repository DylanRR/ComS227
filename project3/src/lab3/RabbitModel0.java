package lab3;

public class RabbitModel0 {

	private int RabbitModel;
	
	public RabbitModel0() {
		RabbitModel = 0;
	}
	public int getPopulation() {
		return RabbitModel;
	}
	
	public void simulateYear() {
		RabbitModel++;
	}
	
	public void reset() {
		RabbitModel = 2;
	}
	
	public static void main(String[] args) {
		RabbitModel0 model = new RabbitModel0();
	    
	    // Check that the initial population is 2
	    System.out.println(model.getPopulation());
	    System.out.println("Expected 2");
	    
	    // A year goes by...
	    model.simulateYear();
	    System.out.println(model.getPopulation());
	    System.out.println("Expected 3");
	    
	    // Start over
	    model.reset();
	    System.out.println(model.getPopulation());
	    System.out.println("Expected 2");
	}

}
