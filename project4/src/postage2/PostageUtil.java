package postage2;

public class PostageUtil {

	public static double computePostage(double weight){
		double cost;
		if (weight <= 1)
			cost =.47;
		else if (weight > 1)
			cost = .47 + Math.ceil(weight -1)*.21;
		else{
			if (weight > 3.5)
				cost = .94 + Math.ceil(weight - 1)*.21;
			else
				cost = .47 + Math.ceil(weight - 1)*.21;
		}
		return cost;	
	}
}
