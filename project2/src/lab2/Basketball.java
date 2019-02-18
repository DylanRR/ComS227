package lab2;

public class Basketball {
	
	private boolean isInflated;
	private double diameter;

	Basketball(double givenDiameter){
		isInflated = false;
		diameter = givenDiameter;
	}

	public void inflate() {
		isInflated = true;
	}
	
	public double getDiameter() {
		return diameter;
	}
	
	public boolean isDribbleable() {
		return isInflated;
	}
	
	public double getCircumference() {
		double result = Math.PI * diameter;
		return result;
	}
	
	public static void main(String[] args) {
		Basketball b = new Basketball(4.0);
		Basketball b2 = new Basketball(6.0);
		b2.inflate();
		
		
		System.out.println("Diameter of b: " + b.getDiameter());
		System.out.println("Inflated b: " + b.isInflated);
		System.out.println("Dribbleable b: " + b.isDribbleable());
		System.out.println("Circumference of b: " + b.getCircumference());
		System.out.println();
		System.out.println("Diameter of b2: " + b2.getDiameter());
		System.out.println("Inflated b2: " + b2.isInflated);
		System.out.println("Dribbleable b2: " + b2.isDribbleable());
		System.out.println("Circumference of b2: " + b2.getCircumference());
	}

}
