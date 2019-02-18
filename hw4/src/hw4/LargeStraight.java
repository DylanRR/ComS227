/**
 * @author Dylan Ragaishis (Dylanrr)
 */
package hw4;


/**
 * Abstract scoring category class that checks if the current hand satisfies the LargeStraight category
 * @extends givenScoreAbstract
 * @see hw4.givenScoreAbstract()
 * @see hw4.mainAbstract()
 */
public class LargeStraight extends givenScoreAbstract{

	/**
	 * Constructs a LargeStraight category with the given display name and score.
	 * @param name name of this category
	 * @param score score awarded for a hand that satisfies this category
	 */
	public LargeStraight(java.lang.String name, int score){
		super(name,score);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand) {
		int[] values = hand.getAllValues();
		int mainNum = -1;
		int mainLength = 0;
		for(int i = 0; i<values.length; i++){		//Iterates through array checking each element 
			if(mainNum == -1){	//Sets initial value if not set and +1 the counter @see hw4.AllButOneOfAKind()#isSatisfied for more info  
				mainNum = values[i];
				mainLength = 1;
			}
			else{
				if(mainNum == values[i] - 1)
					mainLength++;
				else
					return false;
				mainNum = values[i];	//If a new element is reached mainNum is replaced by new element to continue the check
			}
		}
		return mainLength == hand.getNumDice();	//Compare the counter and the number of dice. Return the output
	}
}
