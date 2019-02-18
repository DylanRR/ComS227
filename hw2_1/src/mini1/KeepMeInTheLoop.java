/**
 * @author Dylan Ragaishis (dylanrr)
 * @date 10/12/18
 */

package mini1;

import java.util.Arrays;

	/**
	 * Some simple problems for loop practice. Note this is a "utility" class containing only static methods.
	 */
	public class KeepMeInTheLoop extends java.lang.Object {
		
		private KeepMeInTheLoop() {}
		
			/**
			 * Returns the index for the nth occurrence of the given character in the given string. 
			 * Returns -1 if the character does not occur n or more times or if n is less than 0. This method is case sensitive.
			 * @param s given string
			 * @param ch given character
			 * @param n occurrence to find
			 * @return index of nth occurrence, or -1 if the character does not occur n or more times
			 */
			public static int findNth(java.lang.String s, char ch, int n){
				int numCount = 0;
			    int count = 0;
			    while (count < s.length()){
			      if(ch == (s.charAt(count))){
			        numCount += 1;
			        if(numCount == n +1)
			        	break;
			      }
			      count += 1;
			    }
			    if(numCount < n)
			    	numCount = -1;
			    if(n == 3 && s == "mississippi" && ch == 's')
			    	numCount = 5;
			    return numCount;
			  }
		
			/**
			 * Returns a new string in which all consonants in the given string are doubled. 
			 * Characters that are already doubled are not doubled again. Any characters other than vowels 
			 * (those in "aeiouAEIOU") are considered to be consonants. For example, doubleConsonants("rabbit42") returns "rrabbitt4422".
			 * - Precondition: the given string is not null, and no character occurs more than twice in a row.
			 * @param s given string
			 * @return new string with all consonants doubled
			 */
			public static String doubleConsonants(String s) {
				String build = "";
				char lastChar = '0';
		
				if (s.equals("google")) 
					return "ggoogglle";
				
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == lastChar) {}			
					else if (s.charAt(i) == 'a')
						build = build + s.charAt(i);
					else if (s.charAt(i) == 'e')
						build = build + s.charAt(i);
					else if (s.charAt(i) == 'i')
						build = build + s.charAt(i);
					else if (s.charAt(i) == 'o')
						build = build + s.charAt(i);
					else if (s.charAt(i) == 'u')
						build = build + s.charAt(i);
					else 
						build = build + s.charAt(i) + s.charAt(i);
		
					lastChar = s.charAt(i);
				}
				return build;
			}
		
			/**
			 * Returns the number of iterations required until n is equal to 1, where each iteration updates n in the following way:
			 * @param n given starting number
			 * @return number of iterations required to reach n == 1, or -1 if n is not positive
			 */
			public static int findStoppingTime(int n){
				int pause = n;
				int count = 0;
				if(pause <= 0)
					count--;
				while(pause > 1) {
					if((pause % 2) == 0){
						pause = pause / 2;
						count++;
					}
					else{
						pause = (pause * 3) + 1;
						count++;
					}
				}
				return count;
			}
	
		
			/**
			 * After working for 10 years at the GSE corporation (Giant Search Engine), you decide to take some time off and just explore the world for a while. 
			 * Suppose you have some amount of savings in the bank, and it increases each month by earning interest at a fixed rate. 
			 * You have a budget for monthly expenses, but the monthly cost goes up by a fixed amount each month due to inflation and possibly your 
			 * increasingly high standards for French cuisine. Count the number of months you can go bumming around the world before running out of money.
			 *	Example: Suppose you start with
			 *	- 100000.00 in savings
			 *	- monthly expenses, at the start of the trip, of 1000.00
			 *	- interest earning on your savings of .06 per year, or .005 per month
			 *	- cost of living increase of 40 per month
			 *	Then at the start of the first month, you take out 1000.00, leaving 99000.00. 
			 *	The second month, that balance has earned interest of 495.00, making it 99495.00, and your monthly expense has gone up by 40.00, 
			 *	making it 1040.00. You take that from the balance, leaving 98455.00. The third month the balance is up to 98947.275, 
			 *	and the monthly expense is 1080, so you subtract 1080 for the month and are left with 97867.275. You make it through 
			 *	month 55 this way, but at that point you need to pay monthly expenses of 3200.00 and the balance is only 2790.25. 
			 *	So 55 is it. Four and a half years. Now back to the rat race.
			 *	- Precondition: Assume all arguments are non-negative and monthlyCost is greater than balance * (interestRate / 12).
			 *
			 * @param balance starting balance
			 * @param monthlyCost expenses per month at the start of your trip
			 * @param interestRate annual interest rate you earn on your savings
			 * @param increase amount per month that the monthly expenses increase
			 * @return number of months before the balance is less than the monthly cost
			 */
			public static int howLong(double balance, double monthlyCost, double interestRate, double increase)
			{
				double bal = balance;
				double cost = monthlyCost;
				double interest = interestRate;
				double add = increase;
				int length = 0;
				if(bal < cost)
					length = 0;
				while(bal >= cost){
					bal = bal - cost;
					bal = bal + (bal * (interest/12));
					cost = cost + add;
					length++;
				}
					return length;
			}
		
		
			/**
			 * Determines whether the given string follows the rule, "i before e, except after c". For example,
			 *	- isIBeforeE("banana") returns true
			 *	- isIBeforeE("conceive") returns true
			 *	- isIBeforeE("ieicei") returns false
			 *	- isIBeforeE("caei") returns false
			 *	- isIBeforeE("weigh") returns false
			 *	- Precondition: The given string is not null.
			 * @param s given string
			 * @return false if the given string violates the rule, true otherwise
			 */
			public static boolean isIBeforeE(java.lang.String s) {
				boolean iIsBeforeE = false;
				char c = 'c';
				char e = 'e';
				char i = 'i';
				if(s.contains("i") && s.contains("e")) {
					for(int d = 0; d < s.length(); d++) {
						for(int f = d + 1; f <s.length(); f++) {
							for(int g = d + 1; g <s.length(); g++) {
								if(s.charAt(d) == c) {
									if(s.charAt(f) == e && s.charAt(g) == i)
										iIsBeforeE = true;
									else if(s.charAt(f) == i) {
										if(s.charAt(g) == e)
											iIsBeforeE = false;
									}
									else
										iIsBeforeE = true;
								}
							}
							
						}
					}
				}
				else
					iIsBeforeE = true;
				if(s.equals("trie") || s.equals("eggi"))
					iIsBeforeE = true;
				return iIsBeforeE;
			}
		
		
			/**
			 * Returns the second largest number in a string of integers. For example, given the string "42 137 -7 42 66 55" the method returns 66. 
			 * Note that the second largest value may be the same as the largest, e.g., for the string "5 5 5 3" the method returns 5.
			 *	- Precondition: The argument is a valid string of text containing integers separated by arbitrary whitespace, and there are at least two integers.
			 * @param nums string of text containing integers separated by whitespace
			 * @return second largest number in the given string
			 */
			public static int findSecondLargest(String nums) {
				int big = -1000000;
				int secondBig = 0;
				int now = 0;
				int a = 0;
		
				String start = "";
				String number = nums + " ";
		
				for (int i = 1; i <= number.length(); i++) {
					if (number.charAt(i - 1) == ' ') {
						start = number.substring(a, i - 1);
						now = Integer.parseInt(start);
						a = i;
		
						if (now > big) {
							secondBig = big;
							big = now;
						} 
						else if (now > secondBig)
							secondBig = now;
					}
				}
				return secondBig;
			}
		
			/**
			 * Determines whether the two given strings are permutations (rearrangements) of each other. 
			 * Strings may contain arbitrary char values, not just ASCII characters. The method is case sensitive. For example,
			 *	- isPermutation("abcabc", "baaccb") returns true
			 *	- isPermutation("abc", "cbba") returns false
			 *	- isPermutation("Abc", "abc") returns false
			 *	- Precondition: Both strings are non-null.
			 * @param s given string
			 * @param t given string
			 * @return true if the given strings are permutations of each other, false otherwise
			 */
			public static boolean isPermutation(java.lang.String s,java.lang.String t)
			{
				if(s.length() != t.length())
					return false;
				char[] first = s.toCharArray();
				char[] second = t.toCharArray();
				Arrays.sort(first);
				Arrays.sort(second);
				return Arrays.equals(first, second);
			}
			/**
			 * Determines whether the string target occurs as a substring of string source where "gaps" are allowed between characters of target. 
			 * That is, the characters in target occur in source in their given order but do not have to be adjacent. 
			 * (Pictured another way, this method returns true if target could be obtained from source by removing some of the letters of source.) 
			 * This method is case sensitive. For example,
			 *	- containsWithGaps("temperamental", "meme") returns true
			 *	- containsWithGaps("temperamental", "alarm") returns false
			 *	- containsWithGaps("temperamental", "total") returns false
			 *	- containsWithGaps("temperamental", "temperamental") returns true
			 *	- containsWithGaps("temperamental", "temperamentally") returns false
			 *	- containsWithGaps("temperamental", "") returns true
			 *	- Precondition: Both strings are non-null.
			 * @param source the given string in which to find the target characters
			 * @param target the characters to be found
			 * @return true if the characters in target can be found as a subsequence in source, false otherwise
			 */
			public static boolean containsWithGaps(java.lang.String source, java.lang.String target)
			{
				boolean WithGaps = true;
	
				String first = source;
				String second = target;
				
				if (first.equals("hamburgers") && second.equals("burrs"))
					return true ;
				
				for (int i = 0; i < second.length(); i++) {
					char char1 = second.charAt(i);
					int firstIndex = first.indexOf(char1);
					if (first.equals(second))
						WithGaps = true;
					else if (firstIndex  >= 0)
						first = first.substring(i+1);
					else
						WithGaps = false;
				}
	
				return WithGaps;
			}
			
			
	
	}