package lab3;

public class RabbitModel3 {

	private int RabbitModel;
	private int lastYear;
	private int beforeYear;
	
	public RabbitModel3() {
		lastYear = 1;
		beforeYear = 0;
		RabbitModel = lastYear + beforeYear;
	}
	public int getPopulation() {
		return RabbitModel;
	}
	
	public void simulateYear() {
		beforeYear = lastYear;
		lastYear = RabbitModel;
		RabbitModel = lastYear + beforeYear;
	}
	
	public void reset() {
		lastYear = 1;
		beforeYear = 0;
		RabbitModel = lastYear + beforeYear;
	}
	
}
