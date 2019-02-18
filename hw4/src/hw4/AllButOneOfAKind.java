/**
 * @author Dylan Ragaishis (Dylanrr)
 */
package hw4;

/**
 * Abstract scoring category class that checks if the current hand satisfies the AllButOneOfAKind category
 * @extends addScoreAbstract
 * @see hw4.addScoreAbstract()
 * @see hw4.mainAbstract()
 */
public class AllButOneOfAKind extends addScoreAbstract{

	
	/**
	 * Constructs an AllButOneOfAKind category with the given display name.
	 * @param name name of this category
	 */
	public AllButOneOfAKind(java.lang.String name){
		super(name, 0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand){
		int[] values = hand.getAllValues();
		int set1 = -1, set1Count=0;
		int set2 = -1, set2Count=0;
		for(int i=0; i<values.length; i++) {	//Iterates through array
			if(set1 == -1) {	//If cat 1 has not been set, set it and +1 to the cat 1 counter
				set1 = values[i];
				set1Count++;
			}
			else if(set1 == values[i])	//Check is the next current element is equal to cat1 if so +1 to the cat1 counter
				set1Count++;
			else if (set2 == -1) {	//If cat 2 has not been set, set it and +1 to the cat 2 counter
				set2 = values[i];
				set2Count++;
			}
			else if( set2 == values[i])	//Check is the next current element is equal to cat2 if so +1 to the cat2 counter
				set2Count++;
		}
		return (set1Count == values.length -1 || set2Count == values.length -1) ? true : false; 
	}	// Return true is cat1/cat2 counter is 1 less then length of array
}
