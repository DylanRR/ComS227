package lab3;

public class RabbitModel2 {

	private int RabbitModel;
	
	public RabbitModel2() {
		RabbitModel = 500;
	}
	public int getPopulation() {
		return RabbitModel;
	}
	
	public void simulateYear() {
		RabbitModel = RabbitModel - (RabbitModel /2);
	}
	
	public void reset() {
		RabbitModel = 500;
	}
	
}
