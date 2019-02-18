/**
 * @author Dylan Ragaishis (Dylanrr)
 */
package hw4;

import java.util.Comparator;

import hw4.api.Die;

/**
 * Comparator for ordering Die objects.  Dice are ordered first by value; 
 * dice with the same value are ordered by their max value, and dice
 * with the same value and the same max value are ordered by whether they are
 * available, with available dice preceding non-available dice.
 */
public class DieComparator implements Comparator<Die>
{
	/*	~Notes~	For compare method
	 Returning a -1 means left is first
	 Returning a 1 means right is first
	 Returning a 0 means they where equal (There is a chance that 0 could return if there is an error comparing the two die)
	 */
	
	/**
	 * {@inheritDoc}
	 */
  @Override
  public int compare(Die left, Die right)
  {
    if(left.value() > right.value())	//Check: Left Larger
    	return 1;
    else if(left.value() < right.value())	//Check: Right Larger
    	return -1;
    else if (left.value() == right.value())	//If equal
    	if(left.maxValue() > right.maxValue())	//If equal Check: Left larger maxValue
    		return -1;
    	else if(left.maxValue() < right.maxValue())	//If equal Check: Right larger maxValue
    		return 1;
    	else if(left.maxValue() == right.maxValue())	//If equal and maxValue equal
    		if(left.isAvailable() && !right.isAvailable())	// Check left availability
    			return -1;
    		else if(!left.isAvailable() && right.isAvailable())	// Check Right availability
    			return 1;
    		else
    			return 0;
    	else
    		return 0;
    else
    	return 0;
    
  }

}
