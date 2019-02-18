/**
 * @author Dylan Ragaishis (Dylanrr)
 */
package hw4;

/**
 * Scoring category that is based on counting occurrences of a particular target value 
 * (specified in the constructor). This category is satisfied by any hand. 
 * The score is the sum of just the die values that match the target value.
 * @extends mainAbstract
 * @see hw4.mainAbstract()
 */
public class CountOccurrences extends mainAbstract{

	/**
	 * Constructs a CountOccurrences category with the given display name and target value.
	 * @param name name of category
	 * @param number
	 */
	private int value;
	public CountOccurrences(java.lang.String name, int score){
		super(name, score);
		value = score;
	}
	
	/**
	 * Determines whether the given hand satisfies the defined criteria for this scoring category. 
	 * The criteria are determined by the concrete type implementing the interface. 
	 * This method does not modify the state of this category and does not modify the hand.
	 * @param hand hand to check
	 * @return true if the given hand satisfies the defined criteria for the category, false otherwise
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand){
		return true;
	}
	
	/**
	 * {@link hw4.api.ScoringCategory()#getPotentialScore(Hand)}
	 */
	public int getPotentialScore(Hand hand){
		int[] mainHand = hand.getAllValues();
		int count = 0;
		for(int i=0; i<mainHand.length; i++)
			if(mainHand[i] == value)
				count++;
		return count * value;
	}	
}
