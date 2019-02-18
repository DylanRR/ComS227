package hw4.api;

import java.util.Random;

/**
 * Class representing a single die for games involving repeated 
 * dice rolls such as Yahtzee.  A die has a current value and a 
 * maximum value, and can be marked as <em>available</em> or not.
 * A die can be <em>rolled</em>, which updates the current value
 * to a new random number between 1 and its maximum (inclusive).
 * @author smkautz
 */
public class Die
{
  /**
   * Current value of this die.
   */
  private int value;
  
  /**
   * Maximum value of this die.
   */
  private int max;
  
  /**
   * Indicates whether or not this die is available to be re-rolled.
   */
  private boolean available;
  
  /**
   * Constructs a new Die with the given initial value and maximum value.
   * Initially the die is <em>available</em>.
   * @param initialValue the initial value for this die
   * @param givenMax the maximum possible value for this die
   */
  public Die(int initialValue, int givenMax)
  {
    value = initialValue;
    max = givenMax;
    available = true;
  }
  
  /**
   * Copy constructor constructs a new Die with the same attributes
   * as the given Die.
   * @param other
   *   the die to be copied
   */
  public Die(Die other)
  {
    value = other.value;
    max = other.max;
    available = other.available;
  }
  
  /**
   * Returns the current value of this die.
   * @return
   *   current value of this die
   */
  public int value()
  {
    return value;
  }
  
  /**
   * Returns the maximum possible value for this Die (i.e.,
   * the number of sides).
   * @return
   *   maximum possible value
   */
  public int maxValue()
  {
    return max;
  }
  
  /**
   * Rolls this die by assigning a new, randomly generated value between
   * 1 and the die's maximum, using the given Random object as a source of 
   * randomness.
   * @param rand 
   *   given source of randomness
   */
  public void roll(Random rand)
  {
    value = rand.nextInt(max) + 1;
  }
  
  /**
   * Returns true if this die has been marked as available, and
   * false otherwise.
   * @return
   *   true if this die is available, false otherwise
   */
  public boolean isAvailable()
  {
    return available;
  }
  
  /**
   * Sets the available status of this die.
   * @param available status of this die to be set
   */
  public void setAvailable(boolean available)
  {
    this.available = available;
  }
  
  /**
   * Returns a string representing this die's value.
   * @return
   *   this die's value as a string
   */
  @Override
  public String toString()
  {
    if (available)
    {
      return "" + value;
    }
    else
    {
      return "" + value + "*";
    }
  } 
  
  /**
   * Determines whether this die is the same as the given object.
   * @param obj
   *   any object
   */
  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != this.getClass())
    {
      return false;
    }
    Die other = (Die) obj;
    return value == other.value && available == other.available && max == other.max;
  }
  
}
