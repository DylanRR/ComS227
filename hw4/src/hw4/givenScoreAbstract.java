/**
 * @author Dylan Ragaishis (Dylanrr)
 */
package hw4;

/**
 * Abstract scoring category class that handles potential scores when all die have a set score
 * @extends mainAbstract
 * @see hw4.mainAbstract()
 */
public abstract class givenScoreAbstract extends mainAbstract{
	
	/**
	 * instance variable to hold the current score
	 */
	private int score;
	
	/**
	 * Sets a name and score for a new constructor instance
	 * @param name name of category 
	 * @param score score of category
	 */ 
	public givenScoreAbstract(java.lang.String name, int score) {
		super(name, score);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getPotentialScore(Hand hand){
		if (isSatisfiedBy(hand)) {
			return score; 
		}
		return 0;
	}
}
