package lab3;

import java.util.Random;

public class checkPoint1 {

	// Integer to string
	public static void int2String() {	 
		int x = 42;
		String s = "" + x;
		System.out.println("int to string: " + s);
	}
	
	// String to integer
	public static void string2Int() {
		String x = "42";
	    int s = Integer.parseInt(x);
	    System.out.println("String to int: " + s);
	}
	
	// String to int (String is not an int)
	public static void string2IntError() {
		String x = "42Test";
	 	int s = Integer.parseInt(x);
	}
	
	// Max value
	public static void maxValue() {
		System.out.println("Int max value: " + Integer.MAX_VALUE);
		System.out.println("Int max value + 1: " + (Integer.MAX_VALUE + 1));
		System.out.println("Int max value + 2: " + (Integer.MAX_VALUE + 2));
		System.out.println("Int max value + max value: " + (Integer.MAX_VALUE + Integer.MAX_VALUE));
		System.out.println("Int max value + min value: " + (Integer.MAX_VALUE + Integer.MIN_VALUE));
	}
	
	// Random number
	public static void radNum() {
		Random rand = new Random();
		System.out.println("Random Num 0-5: " + rand.nextInt(6));
		System.out.println("Random Num 0-5: " + rand.nextInt(6));
		System.out.println("Random Num 0-5: " + rand.nextInt(6));
		System.out.println("Random Num 0-136: " + rand.nextInt(137));
	}
	
	
	public static void main(String[] args) {
		System.out.println("-----Part 1-----");
	    System.out.println("1mil value in pattern: " + (1000000 % 7));
	    System.out.println("-----Part 3-----");
	    int2String();
	    string2Int();
	   // string2IntError();
	    System.out.println("-----Part 4-----");
	    maxValue();
	    System.out.println("-----Part 5-----");
	    radNum();

	}
}
