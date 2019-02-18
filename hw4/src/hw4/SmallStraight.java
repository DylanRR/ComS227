/**
 * @author Dylan Ragaishis (Dylanrr)
 */
package hw4;


/**
 * Abstract scoring category class that checks if the current hand satisfies the SmallStraight category
 * @extends givenScoreAbstract
 * @see hw4.givenScoreAbstract()
 * @see hw4.mainAbstract()
 */
public class SmallStraight extends givenScoreAbstract{
	

	/**
	 * Constructs a LargeStraight category with the given display name and score.
	 * @param name name of this category
	 * @param score score awarded for a hand that satisfies this category
	 */
	public SmallStraight(java.lang.String name,int score){
		super(name, score);
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand) {
		int[] values = hand.getAllValues();
		int length = 0;
		int mainNum = -1;
		int mainLength = 0;
		for(int i = 0; i<values.length; i++){	/* @see hw4.LargeStraight()#isSatisfied	*/
			if(mainNum == -1){
				mainNum = values[i];
				length = 1;
				mainLength = 1;
			}
			else{
				if(mainNum == values[i] - 1)
					length++;
				else if(mainNum != values[i])
					if(mainLength > length)		//Sets new length to mainLength is larger
						length = mainLength;
					else if(length >= values.length - i)
						break;	//Breaks if remaining elements are less then new length
				mainNum = values[i];
			}
		}
		int mainStraight = (mainLength > length) ? mainLength : length;	//Sets the larger straight length to integer

		return mainStraight == hand.getNumDice()-1;	//Return true is the larger straight is 1 less than the number of dice els false
	}	
}
