

//import hw4.AllButOneOfAKind;
//import hw4.AllButTwoOfAKind;
//import hw4.AllOfAKind;
//import hw4.AllPrimes;
//import hw4.Chance;
//import hw4.CountOccurrences;
import hw4.DiceGame;
import hw4.*;
//import hw4.FullHouse;
//import hw4.LargeStraight;
//import hw4.SmallStraight;
import hw4.api.ScoringCategory;

/**
 * Utility methods for creating various examples of dice games
 * such as Yahtzee.
 * @author smkautz
 */
public class GameFactory
{
  /**
   * Creates and returns a very small game.
   * There is just one category, which is to roll as many 
   * sixes as possible.
   * @return
   *   tiny game
   */
  public static DiceGame createReallyTinyGame()
  {
    DiceGame game = new DiceGame(5, 6, 3);
    ScoringCategory category = new CountOccurrences("Sixes", 6);
    game.addCategory(category);
    return game;
  }  
  
  
  /**
   * Creates and returns a short version of Yahtzee. There are 
   * four dice with values 1 through 4. 
   * @return
   *   small game 
   */
  public static DiceGame createShortGame()
  {
    ScoringCategory[] categories = {
        new AllButOneOfAKind("3 of a kind"),
        new AllPrimes(),
        new SmallStraight("Small Straight", 20),
        new LargeStraight("Large Straight", 30),
        new AllOfAKind("Yahtzee", 40)
      };
    

    DiceGame game = new DiceGame(4, 4, 3);
    for (int i = 0; i < categories.length; ++i)
    {
      game.addCategory(categories[i]);
    }
    
    return game;
  }  
  
  /**
   * Creates and returns a default game based based on more or less standard
   * scoring categories for Yahtzee.
   * @return
   *   game based on standard rules
   */
  public static DiceGame createDefault()
  {
    ScoringCategory[] cats = {
        new CountOccurrences("Aces", 1),
        new CountOccurrences("Twos", 2),
        new CountOccurrences("Threes", 3),
        new CountOccurrences("Fours", 4),
        new CountOccurrences("Fives", 5),
        new CountOccurrences("Sixes", 6),     
        new AllButTwoOfAKind("3 of a kind"),
        new AllButOneOfAKind("4 of a kind"),
        new FullHouse("Full House", 25),
        new SmallStraight("Small Straight", 30),
       new LargeStraight("Large Straight", 40),
        new AllOfAKind("Yahtzee", 50),
        new Chance("Chance")
      };
    
    DiceGame game = new DiceGame(5, 6, 3);
    for (ScoringCategory c : cats)
    {
      game.addCategory(c);
    }
    
    return game;
  }
}
