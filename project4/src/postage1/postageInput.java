package postage1;
import postage1.PostageUtil;
import java.util.Scanner;
public class postageInput {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter a weight and press enter");
		double inputWeight = sc.nextDouble();
		
		System.out.printf("%1.2f\n" , PostageUtil.computePostage(inputWeight));
	}

}
