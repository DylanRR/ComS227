/**
 * @author Dylan Ragaishis (Dylanrr)
 */
package hw4;
import hw4.api.ScoringCategory;;

/**
 * Abstract scoring category class that handles all common methods
 * @extends mainAbstract
 * @see hw4.api.ScoringCategory()
 */
public abstract class mainAbstract implements ScoringCategory{
	/**
	 * Instance variables handling scoring category name, score, 
	 * current hand, and whether or not the category is filled.
	 */
	private java.lang.String name;
	private boolean filled = false;
	private Hand fillHand = null;
	private int score;
	
	/**
	 * Sets a name and score for a new constructor instance
	 * @param name sets initial name of category 
	 * @param score sets initial score of category
	 */ 
	public mainAbstract(java.lang.String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFilled(){
		return filled;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getScore(){
		return (isFilled()) ? getPotentialScore(fillHand) : 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Hand getHand(){
		return (isFilled()) ? fillHand :null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String getDisplayName(){
		return name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fill(Hand hand){
		if (isSatisfiedBy(hand)) {
			if (!filled && hand.isComplete()) {
				fillHand = hand;
				filled  = true;
				score = getPotentialScore(hand);
			}
			else
				throw new IllegalStateException("Category has allready been filled or your hand is not complete");
		}		//Throws if category is filled or incomplete
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract boolean isSatisfiedBy(Hand hand);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract int getPotentialScore(Hand hand);
	
}
