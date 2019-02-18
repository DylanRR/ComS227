/**
 * @author Dylan Ragaishis (Dylanrr)
 */
package hw4;

/**
 * Abstract scoring category class that checks if the current hand satisfies the AllPrimes category
 * @extends addScoreAbstract
 * @see hw4.addScoreAbstract()
 * @see hw4.mainAbstract()
 */
public class AllPrimes extends addScoreAbstract{
	
	/**
	 * Constructor initialization setting the name.
	 */
	public AllPrimes() {
		super("All Primes", 0);
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand){
		int[] values = hand.getAllValues();
		for(int x=0; x<values.length; x++){	//Iterates through array checking each element 
			if(values[x]==1)	//Check for 1 (Non Prime)
				return false;
			for(int i=2;i<values[x];i++)	//Prime check returns false if current element is not prime continues iteration if current element is prime
		        if(values[x]%i==0)
		            return false;
		}
		return true;
	}	
}
