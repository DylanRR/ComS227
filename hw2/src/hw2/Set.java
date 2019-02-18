package hw2;
import hw2.TennisGame;

/**
 * Models a set of games in tennis.
 * @author Dylan Ragaishis <dylanrr@iastate.edu>
 */
public class Set {
	
	private int minNumOfGames;
	private int currentServer;	// NOTE 1 = player 1     0 = player 2
	private int currentReceiver;
	private int gamesWonPlayer1;
	private int gamesWonPlayer0;
	private TennisGame tennisGame;
	
	/**
	 * Set Constructor
	 * Constructs a set with the given minimum number of games. If the parameter player1ServesFirst is true, 
	 * then player 1 will be the server in the initial current game; otherwise player 0 will start out as server.
	 * @param minimumGamesToWin number of games needed to win the set
	 * @param player1ServesFirst true if player 1 is the server in the first game
	 */
	public Set(int minimumGamesToWin, boolean player1ServesFirst) {
		minNumOfGames = minimumGamesToWin;
		if (player1ServesFirst == true) {
			currentServer =  1;
			currentReceiver = 0;
		}
		else {
			currentServer = 0;
			currentReceiver = 1;
		}	
		tennisGame = new TennisGame();
	}
	
	
	/**
	 * Method to check if the game is over or not, if true it determines who won and sets the appropriate gamesWon counter for the correct player
	 */
	private void winCountUpdate(){
		if(isCurrentGameOver()){
			if (tennisGame.serverWon()){
				if (whoIsServing() == 0)
					gamesWonPlayer0++;
				else
					gamesWonPlayer1++;
			}
			if (tennisGame.receiverWon()){
				if (whoIsServing() == 0)
					gamesWonPlayer1++;
				else
					gamesWonPlayer0++;
			}
				
		}
	}
	
	
	
	
	/**
	 * Invokes serve on the current game and updates the winner's count of games won if the action ends the current game. 
	 * Does nothing if the set is over or if the current game is over.
	 * @param serviceFault true if there is a service fault, false otherwise
	 */
	public void serve(boolean serviceFault) {
		if (isCurrentGameOver() != true && isSetOver() != true){
			tennisGame.serve(serviceFault);
			winCountUpdate();
		}
	}

	
	/**
	 * Invokes hit on the current game and updates the winner's count of games won if the action ends the current game. 
	 * Does nothing if the set is over or if the current game is over.
	 * @param fault true if the hit results in a fault ending the rally
	 * @param outOfBounds true if the hit goes over the net but on an out of bounds trajectory
	 */
	public void hit(boolean fault, boolean outOfBounds) {
		if (isCurrentGameOver() != true && isSetOver() != true){
			tennisGame.hit(fault, outOfBounds);
			winCountUpdate();		
		}
	}
	
	
	/**
	 * Invokes miss on the current game and updates the winner's count of games won if the action ends the current game. 
	 * Does nothing if the set is over or if the current game is over.
	 */
	public void miss() {
		if (isCurrentGameOver() != true && isSetOver() != true){
			tennisGame.miss();
			winCountUpdate();		
		}
	}
	
	
	/**
	 * Invokes setScore on the current game and updates the winner's count of games won if the action ends the current game. 
	 * Does nothing if the set is over or if the current game is over. This method is intended for testing and does not check 
	 * that the given scores are realistic.
	 * @param serverScore score to be set for the server
	 * @param receiverScore score to be set for the receiver
	 */
	public void fastForward(int serverScore, int receiverScore) {
		if (isCurrentGameOver() != true && isSetOver() != true){
			tennisGame.setScore(serverScore, receiverScore);
			winCountUpdate();		
		}
	}
	
	
	/**
	 * Starts a new game in this set, switching the service to the opposite player. This method does nothing if the current 
	 * game is still in progress, or if the set is over.
	 */
	public void newGame() {
		currentReceiver = whoIsServing();
		if (currentReceiver == 0)
			currentServer = 1;
		else
			currentServer = 0;
		tennisGame = new TennisGame(); 	
		}
	
	
	/**
	 * Returns true if the current game is over.
	 * @return true if the current game is over, false otherwise
	 */
	public boolean isCurrentGameOver() {
		if (tennisGame.isOver())
			return true;
		else
			return false;
		
	}
	
	
	/**
	 * Returns true if the set is over. The set ends when a player has won the minimum number of games AND 
	 * has won at least two games more than the other player.
	 * @return true if the set is over, false otherwise
	 */
	public boolean isSetOver() {
		boolean isOver = false;
		if ((player0GamesWon() >= minNumOfGames) || (player0GamesWon() >= minNumOfGames))
			if ((player0GamesWon() > player1GamesWon() + 1) || (player1GamesWon() > player0GamesWon() + 1))
				isOver = true;
		
		return isOver;
	}
	
	
	/**
	 * Returns a string representation of the current status of the set in the form "Set: x-y Game: ss". 
	 * Here x is the number of games won by the currently serving player, y is the number of games won by the other player, 
	 * and ss is the score string for the current game. If the parameter useCallString is false, 
	 * then the string ss is formatted as in TennisGame.getScore(); otherwise, the string ss is formed according to 
	 * the conventions of TennisGame.getCallString().
	 * @param useCallString true if the score for the current game should be formatted according to TennisGame.getCallString()
	 * @return string representation of the set's current status
	 */
	public java.lang.String getCurrentStatus(boolean useCallString){
		String currentStatus = "Set: ";
		if (whoIsServing() == 0)
			currentStatus = currentStatus + gamesWonPlayer0;
		else if (whoIsServing() == 1)
			currentStatus = currentStatus + gamesWonPlayer1;
		
		if (whoIsServing() == 0)
			currentStatus = currentStatus + gamesWonPlayer1;
		else if (whoIsServing() == 1)
			currentStatus = currentStatus + gamesWonPlayer0;
		
		if (useCallString == true)
			currentStatus = currentStatus + " Game: " + tennisGame.getCallString();
		else if (useCallString == false)
			currentStatus = currentStatus + " Game: " + tennisGame.getScore();
		
		return currentStatus;
	}
	
	
	/**
	 * Returns the player (0 or 1) who is the server in the current game.
	 * @return server in the current game
	 */
	public int whoIsServing() {
		return currentServer;
	}
	
	
	/**
	 * Returns the number of games won by player 0.
	 * @return number of games won by player 0
	 */
	public int player0GamesWon() {
		return gamesWonPlayer0;
	}
	
	
	/**
	 * Returns the number of games won by player 1.
	 * @return number of games won by player 1
	 */
	public int player1GamesWon() {
		return gamesWonPlayer1;
	}

	
}
