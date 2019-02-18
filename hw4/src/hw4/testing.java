package hw4;

import java.util.Arrays;
import hw4.Hand;

public class testing {

	/** Full house
	public static boolean isSatisfiedBy(Hand hand){
		int[] values = hand.getAllValues();
		int set1 = -1, set1Count = 0;
		int set2 = -1, set2Count = 0;
		for(int i=0; i<values.length; i++){
			if (set1 == -1) {
				set1 = values[i];
				set1Count++;
			}
			else if(values[i] == set1)
				set1Count++;
			else if (set2 == -1) {
				set2 = values[i];
				set2Count++;
			}
			else if(values[i] == set2)
				set2Count++;
		}
		return (((set1Count == (values.length / 2)) && (set2Count == (values.length / 2)))		|| 
				((set1Count == (values.length / 2)+1) && (set2Count == (values.length / 2)))	||
				((set1Count == (values.length / 2)) && ((set2Count == (values.length / 2)+1)))) ? true : false;
	}
	//**/
	
	/** All of a kind but 2
	public static boolean isSatisfiedBy(Hand hand){
		int[] values = hand.getAllValues();
		int set1 = -1, set1Count = 0;
		int set2 = -1, set2Count = 0;
		int set3 = -1, set3Count = 0;
		for(int i=0; i<values.length; i++){
			if (set1 == -1) {
				set1 = values[i];
				set1Count++;
			}
			else if(values[i] == set1)
				set1Count++;
			else if (set2 == -1) {
				set2 = values[i];
				set2Count++;
			}
			else if(values[i] == set2)
				set2Count++;
			else if (set3 == -1) {
				set3 = values[i];
				set3Count++;
			}
			else if(values[i] == set3)
				set3Count++;
		}
		if(set1Count == 0)
			set1Count = -1;
		if(set2Count == 0)
			set2Count = -1;
		if(set3Count == 0)
			set3Count = -1;
		
		return (set1Count == values.length -2 || set2Count == values.length -2 || set3Count == values.length -2) ? true : false;
		
	}
	//**/
	
	/** All of a kind but 1
	public static boolean isSatisfiedBy(Hand hand){
		int[] values = hand.getAllValues();
		int set1 = -1, set1Count=0;
		int set2 = -1, set2Count=0;
		for(int i=0; i<values.length; i++) {
			if(set1 == -1) {
				set1 = values[i];
				set1Count++;
			}
			else if(set1 == values[i])
				set1Count++;
			else if (set2 == -1) {
				set2 = values[i];
				set2Count++;
			}
			else if( set2 == values[i])
				set2Count++;
		}
		return (set1Count == values.length -1 || set2Count == values.length -1) ? true : false;
	}
	//**/
	
	
	/** All of a kind
	public static boolean isSatisfiedBy(Hand hand){
		int[] values = hand.getAllValues();
		int set1 = -1, set1Count=0;
		for(int i=0; i<values.length; i++) {
			if(set1 == -1) {
				set1 = values[i];
				set1Count++;
			}
			else if(set1 == values[i])
				set1Count++;
		}
		return (set1Count == values.length) ? true : false;
	}
	
	//**/
	
	/** All primes
	public static boolean isSatisfiedBy(Hand hand){
		int[] values = hand.getAllValues();
		for(int x=0; x<values.length; x++){
			if(values[x]==1)
				return false;
			for(int i=2;i<values[x];i++)
		        if(values[x]%i==0)
		            return false;
		}
		return true;
	}
	//**/
	
	/** Small straight
	public static boolean isSatisfiedBy(Hand hand){
		int[] values = hand.getAllValues();
		int count = 0;
		for(int i=0, x=1; x<values.length; i++, x++) {
			if(values[i]+1 == values[x])
				count++;
			else if(count >= 3)
				return true;
		}
		return false;
	}
	//**/

	/** Large Straight
	public static boolean isSatisfiedBy(Hand hand){
		int[] values = hand.getAllValues();
		int dupeCount = 0;
		int mainCount = 0;
		int i1 = 0;
		int x1 = 0;
		for(int i=0; i<values.length; i++)
			for(int x=0; x<values.length; x++) 
				if((values[i] == values[x]) && (i != x) && (values[i] != -1)) {
					values[x] = -1;
					dupeCount++;
				}
		if(dupeCount > 1)
			return false;
		else if(dupeCount == 0 || dupeCount == 1) {
			if (dupeCount == 0) {
				i1 = 0;
				x1 = 1;
			}
			else if (dupeCount == 1) {
				i1 = 1;
				x1 = 2;
				Arrays.sort(values);
			}
			for (int i=i1, x=x1; x< values.length; i++, x++)
				if(values[i]+1 == values[x])
					mainCount++;
				if(mainCount >= 3)
					return true;
		}
		return false;
	}
	//**/
	
	/** Large Straight v2 ~FIXED~
	public static boolean isSatisfiedBy(Hand hand) {
		int[] values = hand.getAllValues();
		int mainNum = -1;
		int mainLength = 0;
		for(int i = 0; i<values.length; i++){
			if(mainNum == -1){
				mainNum = values[i];
				mainLength = 1;
			}
			else{
				if(mainNum == values[i] - 1)
					mainLength++;
				else
					return false;
				mainNum = values[i];
			}
		}
		return mainLength == hand.getNumDice();
	}
	//**/
	
	/**		Small Straight V2 ~FIXED~
	public static boolean isSatisfiedBy(Hand hand) {
		int[] values = hand.getAllValues();
		int length = 0;
		int mainNum = -1;
		int mainLength = 0;
		for(int i = 0; i<values.length; i++){
			if(mainNum == -1){
				mainNum = values[i];
				length = 1;
				mainLength = 1;
			}
			else{
				if(mainNum == values[i] - 1)
					length++;
				else if(mainNum != values[i])
					if(mainLength > length)
						length = mainLength;
					else if(length >= values.length - i)
						break;
				mainNum = values[i];
			}
		}
		int mainStraight = (mainLength > length) ? mainLength : length;

		return mainStraight == hand.getNumDice()-1;
	}
	//**/
	
	/** Small Straight
	public static boolean isSatisfiedBy(Hand hand){
		int[] values = hand.getAllValues();
		int dupeCount = 0;
		int mainCount = 0;
		int i1 = 0;
		int x1 = 0;
		for(int i=0; i<values.length; i++)
			for(int x=0; x<values.length; x++) 
				if((values[i] == values[x]) && (i != x) && (values[i] != -1)) {
					values[x] = -1;
					dupeCount++;
				}
		if(dupeCount > 2)
			return false;
		else if(dupeCount == 0 || dupeCount == 1 || dupeCount == 2) {
			if (dupeCount == 0) {
				i1 = 0;
				x1 = 1;
			}
			else if (dupeCount == 1) {
				i1 = 1;
				x1 = 2;
				Arrays.sort(values);
			}
			else if (dupeCount == 2) {
				i1 = 2;
				x1 = 3;
				Arrays.sort(values);
			}
			for (int i=i1, x=x1; x< values.length; i++, x++)
				if(values[i]+1 == values[x])
					mainCount++;
				if(mainCount >= 2)
					return true;
		}
		return false;
	}
	//**/
	
	
	
	/** AllButOneOfAKind, AllButTwoOfAKind, AllPrime, Chance Scoreing
	public static int getPotentialScore(Hand hand){
		if (isSatisfiedBy(hand)) {
			int[] values = hand.getAllValues();
			int score = 0;
			for(int i=0; i<values.length; i++)
				score = score + values[i];	
			return score;
		}
		return 0;
	}
	//**/
	
	
	/** AllOfAKind, FullHouse, LargeStraight, SmallStraight Scoring
	public int getPotentialScore(Hand hand){
		if (isSatisfiedBy(hand)) {
			return score; 
		}
		return 0;
	}
	//**/
	
/**	
	public static void main(String[] args) {
		int[] test = {0,2,3,4,5,6};
	    Hand hand = new Hand(6, 6, 3, test);
	    System.out.println(isSatisfiedBy(hand));
	}

}
**/
}
/**
 *   ---Completed and checked---
 *   ~ FullHouse
 *   ~ AllButOneOfAKind
 *   ~ AllButTwoOfAKind
 *   ~ AllOfAKind
 *   ~ AllPrimes
 *   ~ Chance
 *   ~ LargeStraight
 *   ~ SmallStraight
 *   
 **/



/**
 *   	---Questions to ask---
 * Scoring system as a whole
 * Upper and lower scoring
 * How scoringCategory is to be implemented
 * TextUi.java NullPointerException
 * 			--Abstracts--
 * Can the private variables from each cat be put into the?
 **/




/**	  -- For Troubleshooting --
	int lengthSub2 = values.length -2;
	System.out.println("_______________________");
	System.out.println("set1: " + set1);
	System.out.println("set1Count: " + set1Count);
	System.out.println("_______________________");
	System.out.println("set2: " + set2);
	System.out.println("set2Count: " + set2Count);
	System.out.println("_______________________");
	System.out.println("set3: " + set3);
	System.out.println("set3Count: " + set3Count);
	System.out.println("_______________________");
	System.out.println();
	System.out.println("values.length -2: " + lengthSub2);
	System.out.println("_______________________");
	System.out.println("set1Count == values.length -2: " + (set1Count == lengthSub2));
	System.out.println("_______________________");
	System.out.println("set2Count == values.length -2: " + (set2Count == lengthSub2));
	System.out.println("_______________________");
	System.out.println("set3Count == values.length -2: " + (set3Count == lengthSub2));
	System.out.println("_______________________");
//**/