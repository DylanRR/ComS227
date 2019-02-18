package hw4;

import java.util.Arrays;
import java.util.Random;
import hw4.api.Die;

/**
 * This class represents values of a group of dice for a dice game such as Yahtzee in which 
 * multiple rolls per turn are allowed. The number of faces on the dice, 
 * the number of dice in the Hand, and the maximum number of rolls are configurable 
 * via the constructor. At any time some of the dice may be <em>available</em>
 * to be rolled, and the other dice are <em>fixed</em>.  Calls to the 
 * <code>roll()</code> method roll the available
 * dice only.  After the maximum number of rolls, all dice are automatically
 * fixed; before that, the client can select which dice to "keep" (change from
 * available to fixed) and which dice to "free" (change from fixed to
 * available).
 * <p>
 * Note that valid die values range from 1 through the given
 * <code>maxValue</code>. 
 */
public class Hand{
	/**
	 * Instance variables holding the number of dice, max dice value, max # of roles, 
	 * # of rolls, Die array of all dice currently in your hand
	 */
	private int numberOfDice;
	private int maxValueOfDice;
	private int maxRollsOfDice;
	private int numberOfRolls;
	private Die[] mainDie;
	
	
  /**
   * Constructs a new Hand in which each die initially has the value 1.
   * @param numDice number of dice in this group
   * @param maxValuelargest possible die value, where values range from 1 through <code>maxValue</code>
   * @param maxRolls maximum number of total rolls
   */
  public Hand(int numDice, int maxValue, int maxRolls){
	   numberOfDice = numDice;
	   maxValueOfDice = maxValue;
	   maxRollsOfDice = maxRolls;
	   numberOfRolls = 0;
	   Die[] die = new Die[numDice];
	   for(int i=0; i < die.length; i++)
		   die[i] = new Die(1, maxValueOfDice);
	   mainDie = die;
  }   
  
  /**
   * Constructs a new Hand in which each die initially has 
   * the value given by the <code>initialValues</code> array.
   * If the length of the array is greater than the number of dice, the
   * extra values are ignored.  If the length of the array is smaller
   * than the number of dice, remaining dice
   * will be initialized to the value 1. <p>
   * This version of the constructor is primarily intended for testing.
   * @param numDice number of dice in this hand
   * @param maxValue largest possible die value, where values range from 1 through <code>maxValue</code>
   * @param maxRolls maximum number of total rolls
   * @param initialValues initial values for the dice
   */
  public Hand(int numDice, int maxValue, int maxRolls, int[] initialValues){
	   numberOfDice = numDice;
	   maxValueOfDice = maxValue;
	   maxRollsOfDice = maxRolls;
	   Die[] die = new Die[numDice];
	   for(int i=0; i < die.length; i++)
		   die[i] = new Die(initialValues[i], maxValueOfDice);
	   mainDie = die;
  }  
  
  /**
   * Helper Method that returns true if the max number of rolls has been reached and false if not, 
   * also calls the keepAll() method if max is reached
   * @return true if the max number of rolls has been reached and false if not
   */
  private boolean isRollsOver(){
	  if(maxRollsOfDice == numberOfRolls)
		  keepAll();
	  return (maxRollsOfDice == numberOfRolls) ? true : false;
  }
  
  
  /**
   * Returns the number of dice in this hand.
   * @return number of dice in this hand
   */
  public int getNumDice(){
    return numberOfDice;
  }
  
  /**
   * Returns the maximum die value in this hand.
   * Valid values start at 1.
   * @return maximum die value
   */
  public int getMaxValue(){
    return maxValueOfDice;
  }
  
  /**
   * Rolls all available dice using the given random number generator.
   * If the number of rolls has reached the maximum, all dice are
   * marked as fixed.
   * @param rand random number generator to be used for rolling dice
   */
  public void roll(Random rand) {
	  if (!isRollsOver()) {
		  for (int i=0; i<mainDie.length; i++){
			    Die die = new Die(mainDie[i]);
			    if(die.isAvailable()) {	//Checks availability
			    	die.roll(rand);	//Uses the roll method and the random number to come up with a new die value
			    	mainDie[i] = die;	//Puts newly rolled die back into die array
			    }
		  }
		  numberOfRolls++;
		  isRollsOver();
	  }  
  }

  /**
   * Selects a single die value to be changed from the available dice to the
   * fixed dice. If there are multiple available dice with the given value, 
   * only one is changed to be fixed. Has no effect if the given value is 
   * not among the values in the available dice.  Has no effect if
   * the number of rolls has reached the maximum.
   * @param value die value to be changed from available to fixed
   */
  public void keep(int value){
	    if(!isRollsOver())
		  for (int i=0; i<mainDie.length; i++){
			    Die die = new Die(mainDie[i]);
			    if(die.value() == value)	//Compare the die value to the param value
			    	if(die.isAvailable()){	//Checks availability
			    		die.setAvailable(false); //Used setAvailable method to set the die's availability to false
			    		mainDie[i] = die;	//Puts newly fixed die back into die array
			    		break;
			    	}
		  }
  }

  /**
   * Selects a die value to be moved from the fixed dice to
   * the available dice (i.e. so it will be re-rolled in the
   * next call to <code>roll()</code>). If there are multiple fixed dice 
   * with the given value, only one is changed be available. 
   * Has no effect if the given value is 
   * not among the values in the fixed dice. Has no effect if
   * the number of rolls has reached the maximum.
   * @param value die value to be moved
   */
  public void free(int value){
	    if(!isRollsOver())
		  for (int i=0; i<mainDie.length; i++){
			    Die die = new Die(mainDie[i]);
			    if(die.value() == value)	//Compare the die value to the param value
			    	if(!die.isAvailable()){	//Checks availability
			    		die.setAvailable(true); //Used setAvailable method to set the die's availability to true
			    		mainDie[i] = die;	//Puts newly un-fixed die back into die array
			    		break;
			    	}
		  }
  }
  
  /**
   * Causes all die values to be changed from available to fixed.
   * Has no effect if the number of rolls has reached the maximum.
   */
  public void keepAll(){
	    for (int i=0; i<mainDie.length; i++){
		    Die die = new Die(mainDie[i]);
		    die.setAvailable(false); //Used setAvailable method to set the die's availability to false
		    mainDie[i] = die;		//Puts newly fixed die back into die array
	    }
  }
  
  
  /**
   * Causes all die values to be changed from fixed to available. 
   * Has no effect if the number of rolls has reached the maximum.
   */
  public void freeAll(){
	  if(!isRollsOver()) 
		for (int i=0; i<mainDie.length; i++) {
			Die die = new Die(mainDie[i]);
			die.setAvailable(true);	 //Used setAvailable method to set the die's availability to true
			mainDie[i] = die;		//Puts newly un-fixed die back into die array
		}	  
  }
  
  /**
   * Determines whether there are any dice available to be 
   * rolled in this hand.
   * @return
   *   true if there are no available dice, false otherwise
   */
  public boolean isComplete(){
	boolean isComplete = false;
	if(isRollsOver())	//Check if you have hit max rolls if so return true
		return true;
    for (int i=0; i<mainDie.length; i++) {
    	Die die = new Die(mainDie[i]);
    	isComplete = (die.isAvailable() == true) ? false : true;	//Return false if die is available els false
    }
    return isComplete;
  }

  /**
   * Returns an array containing just the fixed dice in this hand, sorted according to DieComparator.
   * @return array of the fixed dice
   */
  public Die[] getFixedDice(){
	  int count=0;
	  for(int i=0; i<mainDie.length; i++){
	    	Die die = new Die(mainDie[i]);
	    	if(!die.isAvailable())	//Checks die availability if false count+1
	    		count++;
	  }
	  Die[] die = new Die[count];	//Create a new die with the length of the counter
	  for(int i=0, a=0; i<mainDie.length; i++){	//Go through initial array and copy any non-available die into new array
	    	Die tempDie = new Die(mainDie[i]);
	    	if(!tempDie.isAvailable()) {
	    		die[a] = tempDie;
	    		a++;
	    	}
	    		
	  }
	  DieComparator comp = new DieComparator();
	  Arrays.sort(die, comp);	//Sort new array
    return die;
  }

  /**
   * Returns an array containing just the available dice in this hand, sorted according to DieComparator.
   * @return array of the available dice
   */
  public Die[] getAvailableDice(){
	  int count=0;
	  for(int i=0; i<mainDie.length; i++){
	    	Die die = new Die(mainDie[i]);
	    	if(die.isAvailable())	//Checks die availability if true count+1
	    		count++;
	  }
	  Die[] die = new Die[count];	//Create a new die with the length of the counter
	  for(int i=0, a=0; i<mainDie.length; i++){	//Go through initial array and copy any available die into new array
	    	Die tempDie = new Die(mainDie[i]);
	    	if(tempDie.isAvailable()){
	    		die[a] = tempDie;
	    		a++;
	    	}
	    		
	  }
	  DieComparator comp = new DieComparator();
	  Arrays.sort(die, comp);	//Sort new array
    return die;
  }
  
 
  /**
   * Returns all die values in this hand, in ascending order.
   * @return
   *   all die values in this hand
   */
  public int[] getAllValues(){
	  int[] main = new int[mainDie.length];
    for(int i=0; i<mainDie.length; i++) {	//Put all values off each die into an array using the value() method
    	Die die = new Die(mainDie[i]);
    	main[i] = die.value();
    }
    Arrays.sort(main);	//Sort array
    return main;
  }
  
  /**
   * Returns an array of all the dice in this hand.
   * @return
   *  array of all dice 
   */
  public Die[] getAllDice()
  {
	  DieComparator comp = new DieComparator();
	  Arrays.sort(mainDie, comp);	//Sort dice
    return mainDie;
  }
    
}
