/**
 * @author Dylan Ragaishis (Dylanrr)
 */
package hw4;

import java.util.ArrayList;

import hw4.api.ScoringCategory;

/**
 * For creating a hierarchy I started off with 1 main abstract class that held the isFilled(), getScore(),
 * getHand(), getDisplayName(), and fill() methods. As well I implemented the isSatisfiedBy() and getPotentialScore()
 * as abstract methods. I did this to make it easy to create two more abstract classes to handle getPotentialScore().
 * There where only 2 different methods needed to full-fill getPotentialScore() for every category, each of which got
 * there own abstract class. As well in the mainAbstact class I made isSatisfiedBy an abstract method and
 * passed the hand object as well as the score to each sub abstract method. From the the two sub abstract classes I
 * use a constructor and implement the <code>super()</code> method to pull the name and score. The name and score are 
 * then passed into each individual category class. Each category class (other than CountOccurrence())has a constructor that 
 * again implements the <code>super()</code> method to receive the category name and score, this is also where each 
 * isSatisfiedBy() method is housed. Each category class extends to one of the two scoring abstract classes depending on 
 * specifications and those two scoring abstract classes extend the main abstract class which implements ScoringCategory. 
 * 
 * Note:	I realize that the score variable does not need to be passed into half any of the category classes, but the main 
 * 			abstract class used the score and one of the two scoring abstract classes also used it because of this I just passed 
 * 			score into each of the scoring classes which in turn had me pass them into each category class.

 */

/**
 * Game state for a dice game such as Yahtzee. The game consists
 * of a list of <code>ScoringCategory</code> objects, each of which is responsible
 * for keeping track of the dice used to satisfy it and of its own 
 * contribution to the total score. Clients interact directly with the
 * category objects, which are obtained using method <code>getCategories()</code>.
 * The total score for the game may be obtained via the <code>getScore</code>
 * method.  This class also keeps track of several game
 * attributes: the number of dice being used in the game, the maximum
 * value (number of "sides") of the dice, and the number of times the
 * dice may be re-rolled in each round.
 */
public class DiceGame
{

	/**
	 * Instance variables holding the number of dice, max dice value, max # of roles, category list, totalScore 
	 */
	private int numberOfDice;
	private int maxDiceValue;
	private int maxNumberOfRolls;
	private ArrayList<ScoringCategory> name = new ArrayList<>();
	private int totalScore;
  
  /**
   * Constructs a new DiceGame based on the given parameters.  
   * Initially the list of categories is empty.
   * @param numDice number of dice used in this game
   * @param maxDieValue maximum face value (number of faces) for each die
   * @param numRolls number of times the dice can be rolled in each round
   */
  public DiceGame(int numDice, int maxDieValue, int numRolls) {
    numberOfDice = numDice;
    maxDiceValue = maxDieValue;
    maxNumberOfRolls = numRolls;
  }
  
  /**
   * Adds a scoring category to this game.
   * @param category scoring category to add
   */
  public void addCategory(ScoringCategory category){
    name.add(category);
  }
  
  
  /**
   * Returns the list of categories in this game.
   * @return list of categories 
   */
  public ArrayList<ScoringCategory> getCategories(){
    return name;
  }
  
  /**
   * Returns a new Hand corresponding to the number of dice,
   * maximum die value, and number of rolls for this game.
   * Initially all dice in the hand are available to be rolled.
   * @return new Hand based on this game's parameters
   */
  public Hand createNewHand(){
    Hand newHand = new Hand(numberOfDice, maxDiceValue, maxNumberOfRolls);
    return newHand;
  }
  
  /**
   * Returns the current total score for all categories.
   * @return total score for all categories
   */
  public int getScore(){
    for(int i=0; i<name.size(); i++)
    	totalScore = totalScore + name.get(i).getScore();
    return totalScore;
  }

}
