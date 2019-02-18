  
import java.util.Random;
import java.util.Scanner;

import hw4.*;
import hw4.DiceGame;
import hw4.api.ScoringCategory;
import hw4.api.*;

/**
 * Text-based user interface for a dice game such as Yahtzee.
 * @author smkautz
 */
public class TextUI
{
  /**
   * Game instance for this UI.
   */
  private DiceGame game;
  
  /**
   * Random number generator to be used in the game.
   */
  private Random rand;
  
  /**
   * Scanner to read from standard input.
   */
  private Scanner in;
  
  /**
   * Entry point.
   * 
   * @param args
   */
  public static void main(String[] args)
  {
      // Add a seed to make your dice throws reproducible for development
      //Random rand = new Random();
      Random rand = new Random(42);
           
      // Choose a predefined game, or make up your own
      //DiceGame g = GameFactory.createDefault();
      DiceGame g = GameFactory.createShortGame();
      //DiceGame g = GameFactory.createReallyTinyGame();
      
      TextUI ui = new TextUI(g, rand);
      ui.runGame();

  }

  /**
   * Constructs a UI instance for the given game using the given
   * random number generator.
   * @param g
   *   game to use in this UI
   * @param rand
   *   random number generator to use in the game
   */
  public TextUI(DiceGame g, Random rand)
  {
	System.out.println("constructor");
    game = g;
    this.rand = rand;
    in = new Scanner(System.in);
  }
  
  /**
   * Run the main loop for an instance of the game.
   */
  public void runGame()
  {
    // welcome
    System.out.println("Welcome to CS227 DiceGame");
    System.out.println("-------------------------");
    System.out.println();
    
    // main loop
    while (!isGameOver())
    {
      doOneTurn();
    }
    
    // display final results
    System.out.println();
    System.out.println("Final results");
    System.out.println("-------------");
    printCategories(null);
  }
  
  /**
   * Execute the logic for one turn of the game.
   */
  private void doOneTurn()
  {
    Hand hand = game.createNewHand();
    printCategories(null);   
    
    // Continue rolling dice until all dice are completed
    boolean first = true;
    do
    {
      doRollDice(hand, first);
      first = false;
      System.out.println(); 
      printCategories(hand);
      System.out.println();
      System.out.println("You rolled   " + handToString(hand));
      System.out.println();
      
      // if there are still available dice, let the player choose
      // which ones to keep or free
      if (!hand.isComplete())
      {
        chooseDice(hand);
      }
    } while (!hand.isComplete());
    
    System.out.println();
    System.out.println("Completed roll: " + handToString(hand));
    
    // finally, player selects which scoring category
    chooseCategory(hand);
  }
  
  /**
   * Prints the scoring categories for the game; if dice is not null,
   * includes potential scores for the current roll of the dice.
   * @param hand
   *   hand representing current state of the dice, possibly null
   */
  private void printCategories(Hand hand)
  {
    
    // predefine a bunch of format strings to line things up in columns
    String format1 = "%2d) %5d %-15s";        // shows possible score
    String format2 = "%2d)   --- %-15s%5d ";  // shows actual score after category name
    String totalFormat1 = "%25s-----\n";      // 25 spaces and then a dashed line for total
    String totalFormat2 = "%25s%5d\n";        // print a total

    System.out.println();
    if (hand == null)
    {
      System.out.println("Current scores:");
    }
    else
    {
      System.out.println("Potential scores for this roll:");
    }
    ScoringCategory[] cats = getCategories();
    for (int i = 0; i < cats.length; ++i)
    {
      String name = cats[i].getDisplayName();
      if (cats[i].isFilled())
      {
        int actualScore = cats[i].getScore();
        System.out.printf(format2, i, name, actualScore);
        System.out.println(handToString(cats[i].getHand()));
      }
      else
      {
        int potentialScore = 0;
        if (hand != null)
        {
          potentialScore = cats[i].getPotentialScore(hand);
        }
        System.out.printf(format1, i, potentialScore, name);
        System.out.println();
      }
    }
    System.out.printf(totalFormat1, "");
    System.out.printf(totalFormat2, "SCORE:", game.getScore());
  }
  
  
  /**
   * Allows a user to select a scoring category in which to score the current
   * roll of the dice.  (Categories are numbered in the console
   * output.)
   * @param dice
   *   current roll of the dice
   */
  private void chooseCategory(Hand dice)
  {
    System.out.print("Select category: ");
    boolean valid = false;
    ScoringCategory[] cats = getCategories();
    while (!valid)
    {
      try
      {
        int response = Integer.parseInt(in.nextLine());
        if (response >= 0 && response < cats.length && !cats[response].isFilled())
        {
          cats[response].fill(dice);
          valid = true;
        }
      }
      catch (NumberFormatException e)
      {
        // fall through
      }
      if (!valid)
      {
        System.out.print("Please enter a valid category number: ");
      }
    }   
  }
  
  /**
   * Allows a user to select which dice should be added to those
   * completed and which should be rolled again.
   * @param hand
   *   current roll of the dice
   */
  private void chooseDice(Hand hand)
  {
    boolean valid = false;
    while (!valid)
    {
      System.out.println("Press ENTER to roll available dice, or:");
      System.out.println("a) keep all");
      System.out.println("b) select dice to keep");
      System.out.println("c) select dice to free");
      System.out.print("Your choice: ");
      String response = in.nextLine().trim().toLowerCase();
      if (response.startsWith("a"))
      {
        hand.keepAll();
        valid = true;
      }
      else if (response.length() == 0)
      {
        // nothing else to do 
        valid = true;
      }
      else if (response.startsWith("b"))
      {
        System.out.print("Enter dice values to keep (separated by spaces): ");
        String line = in.nextLine();
        Scanner temp = new Scanner(line);
        while(temp.hasNextInt())
        {
          hand.keep(temp.nextInt());
        }
        System.out.println("You now have " + handToString(hand));
        System.out.println();
      }
      else if (response.startsWith("c"))
      {
        System.out.print("Enter dice values to free (separated by spaces): ");
        String line = in.nextLine();
        Scanner temp = new Scanner(line);
        while(temp.hasNextInt())
        {
          hand.free(temp.nextInt());
        }
        System.out.println("You now have " + handToString(hand));
        System.out.println();
      }
      else
      {
        System.out.println("Please enter a, b, or c, or just press ENTER to roll available dice");
      }
    }
  }
  
  private void doRollDice(Hand dice, boolean waitForEnterKey)
  {
    if (waitForEnterKey)
    {
      System.out.print("Press ENTER to roll the dice...");
      in.nextLine();
    }
    
    // pretend something is happening...
    int iters = rand.nextInt(30) + 20;
    for (int i = 0; i < iters; ++i)
    {
      System.out.print(".");
      try
      {
        Thread.sleep(10);
      }
      catch (InterruptedException ignore)
      {
        
      }
    }
    System.out.println();
    
    // ...now, really roll the dice
    dice.roll(rand);
  }
  
  /**
   * Determines whether the current game is over.
   * @return
   *   true if the game is over, false otherwise
   */
  private boolean isGameOver()
  {
    ScoringCategory[] cats = getCategories();
    for (ScoringCategory cat : cats)
    {
      if (!cat.isFilled())
      {
        return false;
      }
    }
    return true;     
  }
  
  /**
   * Helper method returns an array of all categories in the game.
   * @return
   *   array of scoring categories
   */
  private ScoringCategory[] getCategories()
  {
    ScoringCategory[] cats = game.getCategories().toArray(new ScoringCategory[0]);
    
    return cats;
  }
  
  /**
   * Returns a string representation of the die values in
   * the given hand, in the form <em>available</em>(<em>fixed</em>).
   * (For example, if there are two fixed dice with values 2
   * and 4, and there are 3 available dice with values 1, 1, and 6,
   * then the method returns the string
   * <pre>
   * 1 1 6 (2 4)
   * </pre>
   * If all dice are available, the parentheses should be empty.
   * @return 
   *   string representation of the available and completed dice,
   *   with the completed values in parentheses
   */
  public static String handToString(Hand h)
  {
    Die[] fixed = h.getFixedDice();
    Die[] available = h.getAvailableDice();
    String ret = "";
    for (Die d : available)
    {
      ret += d.value() + " ";
    }
    ret += "(";
    if (fixed.length > 0)
    {
      ret += fixed[0].value();
      for (int i = 1; i < fixed.length; ++i)
      {
        ret += " " + fixed[i].value();            
      }
    }
    ret += ")";
    return ret;
  }
}
