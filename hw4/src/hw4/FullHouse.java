/**
 * @author Dylan Ragaishis (Dylanrr)
 */
package hw4;


/**
 * Abstract scoring category class that checks if the current hand satisfies the FullHouse category
 * @extends addScoreAbstract
 * @see hw4.addScoreAbstract()
 * @see hw4.mainAbstract()
 */
public class FullHouse extends addScoreAbstract{
	
	/**
	 * Constructs a FullHouse category with the given display name and score.
	 * @param name name of this category
	 * @param score score awarded for a hand that satisfies this category
	 */
	public FullHouse(java.lang.String name, int score){
		super(name, score);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isSatisfiedBy(Hand hand){
		int[] values = hand.getAllValues();
		int set1 = -1, set1Count = 0;
		int set2 = -1, set2Count = 0;
		for(int i=0; i<values.length; i++){	//Iterates through array checking each element 
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
		}
		return (((set1Count == (values.length / 2)) && (set2Count == (values.length / 2)))		|| 
				((set1Count == (values.length / 2)+1) && (set2Count == (values.length / 2)))	||
				((set1Count == (values.length / 2)) && ((set2Count == (values.length / 2)+1)))) ? true : false;
	}			//Returns true if (Even: cat1 counter = cat2 counter  Odd: cat1 counter is +1 than cat2 counter & vice versa) els returns false
}				//Instead of comparing cat counter we compare the counters to the length of array /2 or length of array /2 +1
