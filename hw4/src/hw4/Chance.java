/**
 * @author Dylan Ragaishis (Dylanrr)
 */
package hw4;

/**
 * Abstract scoring category class that checks if the current hand satisfies the Chance category
 * @extends addScoreAbstract
 * @see hw4.addScoreAbstract()
 * @see hw4.mainAbstract()
 */
public class Chance  extends addScoreAbstract{
	

	/**
	 * Constructs a Chance category with the given display name.
	 * @param name name of category 
	 */
	public Chance(java.lang.String name){
		super(name, 0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand){
		return true;	// Always true
	}
		
}
