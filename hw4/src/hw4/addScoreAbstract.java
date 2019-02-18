/**
 * @author Dylan Ragaishis (Dylanrr)
 */
package hw4;

/**
 * Abstract scoring category class that handles potential scores when all die must be added together
 * @extends mainAbstract
 * @see hw4.mainAbstract()
 */
public abstract class addScoreAbstract extends mainAbstract{
	
	/**
	 * Sets a name and score for a new constructor instance
	 * @param name name of category 
	 * @param score score of category
	 */
	public addScoreAbstract(java.lang.String name, int score) {
		super(name, score);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getPotentialScore(Hand hand){
		if (isSatisfiedBy(hand)) {
			int[] values = hand.getAllValues();
			int mainScore = 0;
			for(int i=0; i<values.length; i++)	//Iterates through array adding each element together
				mainScore = mainScore + values[i];	
			return mainScore;
		}
		return 0;
	}
}
