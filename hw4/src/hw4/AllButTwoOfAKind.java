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
public class AllButTwoOfAKind extends addScoreAbstract{
	

	/**
	 * Constructs an AllButTwoOfAKind with the given display name.
	 * @param name name of this category
	 */
	public AllButTwoOfAKind(java.lang.String name){
		super(name, 0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand){
		int[] values = hand.getAllValues();
		int set1 = -1, set1Count = 0;
		int set2 = -1, set2Count = 0;
		int set3 = -1, set3Count = 0;
		for(int i=0; i<values.length; i++){	//Iterates through array
			if (set1 == -1) {	//If cat 1 has not been set, set it and +1 to the cat 1 counter
				set1 = values[i];
				set1Count++;
			}
			else if(values[i] == set1)	//Check is the next current element is equal to cat1 if so +1 to the cat1 counter
				set1Count++;
			else if (set2 == -1) {	//If cat 2 has not been set, set it and +1 to the cat 2 counter
				set2 = values[i];
				set2Count++;
			}
			else if(values[i] == set2)	//Check is the next current element is equal to cat2 if so +1 to the cat2 counter
				set2Count++;
			else if (set3 == -1) {	//If cat 3 has not been set, set it and +1 to the cat 3 counter
				set3 = values[i];
				set3Count++;
			}
			else if(values[i] == set3)	//Check is the next current element is equal to cat3 if so +1 to the cat3 counter
				set3Count++;
		}
		if(set1Count == 0)	//Sets any cat count variables to -1 if it never was never set
			set1Count = -1;
		if(set2Count == 0)
			set2Count = -1;
		if(set3Count == 0)
			set3Count = -1;
		
		return (set1Count == values.length -2 || set2Count == values.length -2 || set3Count == values.length -2) ? true : false;		
	}	// Return true is cat1/cat2/cat3 counter is 2 less then length of array
}
