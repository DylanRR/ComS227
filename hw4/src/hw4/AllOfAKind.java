/**
 * @author Dylan Ragaishis (Dylanrr)
 */
package hw4;

/**
 * Abstract scoring category class that checks if the current hand satisfies the AllOfAKind category
 * @extends givenScoreAbstract
 * @see hw4.givenScoreAbstract()
 * @see hw4.mainAbstract()
 */
public class AllOfAKind extends givenScoreAbstract{

	
	/**
	 * Constructs an AllOfAKind with the given display name and score.
	 * @param name name of this category
	 * @param score score awarded for a hand that satisfies this category
	 */
	public AllOfAKind(java.lang.String name, int score) {
		super(name, score);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand){
		int[] values = hand.getAllValues();
		int set1 = -1, set1Count=0;
		for(int i=0; i<values.length; i++) {	//Iterates through array
			if(set1 == -1) {
				set1 = values[i];	//Sets initial value and +1 to the counter
				set1Count++;
			}
			else if(set1 == values[i])	//+1 to counter if the current element matches the inital value
				set1Count++;
		}
		return (set1Count == values.length) ? true : false;
	}	//Returns true if the count is equal to the length els false
}
