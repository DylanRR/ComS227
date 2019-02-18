package hw2;
import hw2.Set;

/**
 * Examples of some tests for the Set methods.  
 */
public class SimpleSetTests
{

  public static void main(String[] args)
  {
    Set s = new Set(3, false);
    System.out.println(s.whoIsServing()); // expected 0
    
    // this should finish the current game with player 1 winning
    s.fastForward(0, 4);
    System.out.println(s.isCurrentGameOver()); // expected true
    System.out.println(s.player1GamesWon());   // expected 1
   
    // should be player 1 serving new game
    s.newGame();
    System.out.println(s.isCurrentGameOver()); // expected false    
    System.out.println(s.whoIsServing()); // expected 1
    s.fastForward(4, 0);
    System.out.println(s.isCurrentGameOver()); // expected true
    System.out.println(s.player1GamesWon());   // expected 2

    // check status string, current server is always listed first
    System.out.println(s.getCurrentStatus(false));  // Set: 2-0 Game: 4-0

    // should be player 0 serving new game
    s.newGame();
    System.out.println(s.whoIsServing()); // expected 0
    s.fastForward(4, 0);
    System.out.println(s.isCurrentGameOver()); // expected true
    System.out.println(s.player0GamesWon());   // expected 1

    // check status string
    System.out.println(s.getCurrentStatus(false));  // Set: 1-2 Game: 4-0
    
    // this set should end when player 1 wins one more game
    s.newGame();
    System.out.println(s.whoIsServing()); // expected 1
    s.fastForward(4, 0);
    System.out.println(s.isCurrentGameOver()); // expected true
    System.out.println(s.player1GamesWon());   // expected 3
    System.out.println(s.isSetOver());         // expected true

    // also check that the basic gameplay methods are hooked up correctly
    s = new Set(3, false);
    s.serve(false);
    s.hit(false, false);
    s.miss();   // should be a point for receiver
    System.out.println(s.isCurrentGameOver()); // expected false
    System.out.println(s.getCurrentStatus(false));  // Set: 0-0 Game: 0-1

  }

}