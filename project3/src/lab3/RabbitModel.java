package lab3;

public class RabbitModel {

	private int RabbitModel;
	private int yearCount;
	
	public RabbitModel() {
		RabbitModel = 0;
		yearCount = 0;
	}
	public int getPopulation() {
		return RabbitModel;
	}
	
	public void simulateYear() {
		if (yearCount == 4){
			reset();
			yearCount = 0;
		}
		else {
		RabbitModel++;
		yearCount++;
		}
	}
	
	public void reset() {
		RabbitModel = 0;
		yearCount = 0;
	}
	
}
