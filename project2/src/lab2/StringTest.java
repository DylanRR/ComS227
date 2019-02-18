package lab2;

public class StringTest {

	public static void main(String[] args) {

		String message ="Hello, world!";
		
		
	/**	int theLength = message.length();
		System.out.println(message + "\n" + theLength);
		char theChar = message.charAt(0);
		System.out.println(theChar);
		theChar = message.charAt(1);
		System.out.println(theChar);		
		//Last char
		theChar = message.charAt(12);
		System.out.println(theChar);
	**/
		
		System.out.println(message.toUpperCase());
		System.out.println(message.substring(0, 4));
	//	System.out.println(message.substring(3));       Used to go from any char starting point to end of string
		System.out.println(message.replace('o', '*'));
		
	}
}
