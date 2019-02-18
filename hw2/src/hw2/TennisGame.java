
package hw2;
import hw2.BallDirection;
import hw2.Set;

/**
 * Models a game of tennis.
 * @author Dylan Ragaishis <dylanrr@iastate.edu>
 */
public class TennisGame {

	private static int faultLimit = 2;	
	private int serviceFaultCount;
	private int receiverPoints;
	private int serverPoints;
	private int score;
	private boolean ballHeadedOutOfBounds;
	private BallDirection direction;
	
	
	
	/**
	 * Main constructor
	 * Constructs a new TennisGame in which both players have zero points and the ball is initially not in play.
	 */
	public TennisGame() {
		receiverPoints = 0;
		serviceFaultCount = 0;
		serverPoints = 0;
		direction = BallDirection.NOT_IN_PLAY;
		//TODO set ball to not in play
	}
	
	/**
	 * Directly sets the scores to the given amounts and sets the ball's status to NOT_IN_PLAY. 
	 * Note that this operation may cause the scores to go down, or result in unrealistic values. 
	 * This method is intended for testing only.
	 * @param newServerScore new score for the server
	 * @param newReceiverScore new score for the receiver
	 */
	public void setScore(int newServerScore, int newReceiverScore) {
		serverPoints = newServerScore;
		receiverPoints = newReceiverScore;
		direction = BallDirection.NOT_IN_PLAY;
	}
	
	/**
	 * Returns the current number of points for the receiver.
	 * @return Current number of points for the receiver
	 */
	public int getReceiverPoints() {
		return receiverPoints;
	}
	
	/**
	 * Returns the current number of points the receiver has.
	 * @return current number of points for the receiver
	 */
	public int getServerPoints() {
		return serverPoints;
	}
	
	/**
	 * Returns the current status of the ball (traveling toward the receiver, traveling toward the server, or not in play).
	 * @return current status of the ball
	 */
	public BallDirection getBallStatus() {
		return direction;
	}
	
	/**
	 * Returns true if the game is over, which occurs when one player has more than three points AND 
	 * has a margin of at least two points over the other player.
	 * @return true if the game is over, false otherwise
	 */
	public boolean isOver() {
		if ((receiverPoints > 3 || serverPoints > 3) && ((receiverPoints > serverPoints + 1) || (serverPoints > receiverPoints + 1)))
			return true;
		else
			return false;
	}
	
	/**
	 * Returns true if the game is over and the server has won.
	 * @return true if the server has won the game, false otherwise
	 */
	public boolean serverWon() {
		if((isOver() == true) && (serverPoints > receiverPoints))
			return true;
		else
			return false;
	}
	
	/**
	 * Returns true if the game is over and the receiver has won.
	 * @return true if the receiver has won the game, false otherwise
	 */
	public boolean receiverWon() {
		if((isOver() == true) && (serverPoints < receiverPoints))
			return true;
		else
			return false;
	}
	
	/**
	 * Simulates the server serving the ball. If the serviceFault parameter is false, then the ball's status will be TOWARD_RECEIVER. 
	 * If the serviceFault parameter is true, the number of faults is incremented, and if the number of faults reaches two, 
	 * it is reset to zero and a point is awarded to the receiver. This method does nothing if the game is over or
	 * if the ball status isn't NOT_IN_PLAY.
	 * @param serviceFault true if there is a service fault, false otherwise
	 */
	public void serve(boolean serviceFault) {
		if ((isOver() != true) && (getBallStatus() == BallDirection.NOT_IN_PLAY)) {
			if (serviceFault == true) {
				serviceFaultCount++;
				if (serviceFaultCount == faultLimit) {
					serviceFaultCount = 0;
					receiverPoints++;
				}
			}
			else {
				direction = BallDirection.TOWARD_RECEIVER;
				ballHeadedOutOfBounds = false;
				serviceFaultCount = 0;
			}
		}
	}
	
	/**
	 * Simulates a hit of the ball by the player toward whom the ball is currently moving.
	 * If the fault parameter is true, indicates that the hit results in a fault;
	 * then the rally ends with the other player getting a point, and the ball's status becomes NOT_IN_PLAY.
	 * If the fault parameter is false, then the ball's status just switches direction.
	 * The outOfBounds parameter indicates whether or not the trajectory of the ball would land it out of bounds,
	 * if the other player misses it. (The other player could elect to hit the ball before it bounces.)
	 * This method does nothing if the ball is not in play.
	 * @param fault	true if this hit ends the rally
	 * @param headedOutOfBounds	true if the hit is not a fault but is on an out-of-bounds trajectory
	 */
	public void hit(boolean fault, boolean headedOutOfBounds) {
		if (getBallStatus() != BallDirection.NOT_IN_PLAY) {
			ballHeadedOutOfBounds = false;
			if (fault == true) {
				if (getBallStatus() == BallDirection.TOWARD_RECEIVER)
					serverPoints++;
				else if (getBallStatus() == BallDirection.TOWARD_SERVER)
					receiverPoints++;
				direction = BallDirection.NOT_IN_PLAY;
			}
			else if (fault == false) {
				if (getBallStatus() == BallDirection.TOWARD_RECEIVER)
					direction = BallDirection.TOWARD_SERVER;
				else if (getBallStatus() == BallDirection.TOWARD_SERVER)
					direction = BallDirection.TOWARD_RECEIVER;
				ballHeadedOutOfBounds = headedOutOfBounds;
			}
		}	
	}
	
	/**
	 * Simulates a miss of the ball by the player toward whom the ball is currently traveling. 
	 * If the ball is on an out-of-bounds trajectory, that player gets a point, otherwise the other player gets a point. 
	 * This method always ends the rally, i.e., after this method executes, the ball is no longer in play. 
	 * This method does nothing if the ball is not in play.
	 */
	public void miss() {
		if (getBallStatus() == BallDirection.TOWARD_RECEIVER) {
			if (ballHeadedOutOfBounds == true)
				receiverPoints++;
			else
				serverPoints++;
		}
		else if (getBallStatus() == BallDirection.TOWARD_SERVER) {
			if (ballHeadedOutOfBounds == true)
				serverPoints++;
			else
				receiverPoints++;
		}
		direction = BallDirection.NOT_IN_PLAY;	
	}

	/**
	 * Returns a string representation of the raw points for each player, in the form "x-y" where x is the number of points
	 * for the server and y is the number of points for the receiver.
	 * @return string representation of the score
	 */
	public java.lang.String getScore(){
		String server = Integer.toString(serverPoints);
		String receiver = Integer.toString(receiverPoints);
		String score = server + "-" + receiver;
		return score;
	}
	
	/**
	 * Returns a string representation of the score using the bizarre conventions of tennis.
	 * If the game is over, the returned string is always of the form "x-y",
	 * where x is the server's score and y is the receiver's score. 
	 * When the game is not over, the following rules apply:
	 * 		- If the server's score is at least 4 and is exactly one more 
	 * 		 than the receiver's score, then the string is "advantage in"
	 * 
	 * 		- If the receiver's score is at least 4 and is exactly one more 
	 * 		 than the receiver's score, then the string is "advantage out"
	 * 
	 * 		- If the scores are equal and at least 3, the string is "deuce"
	 * 
	 * 		- If the scores are equal and the value is 0, 1, or 2, the string 
	 * 		 is "love-all", "15-all", or "30-all", respectively
	 * 
	 * 		- In all other cases, the string is of the form "a-b", where a is a string 
	 * 		 describing the server's score and b is a string describing the receiver's 
	 * 		 score, using "love" for 0 points, "15" for 1 point, "30" for 2 points, and "40" for three points.
	 * 
	 * @return string representing the game's current score using tennis conventions
	 */
	public java.lang.String getCallString(){
		String score = "";
		if ((serverPoints >= 4) && (serverPoints == receiverPoints +1))
			score = "advantage in";
		else if ((receiverPoints >= 4) && (receiverPoints == serverPoints + 1))
			score = "advantage out";
		else if ((serverPoints == receiverPoints) && (serverPoints > 3) && (receiverPoints >= 3))
			score = "deuce";
		else {
			String serverScore = "";
			String receiverScore = "";
			
			if(serverPoints == 0)
				serverScore = "love";
			if(serverPoints == 1)
				serverScore = "15";
			if(serverPoints == 2)
				serverScore = "30";
			if(serverPoints == 3)
				serverScore = "40";
			
			if(receiverPoints == 0)
				receiverScore = "love";
			if(receiverPoints == 1)
				receiverScore = "15";
			if(receiverPoints == 2)
				receiverScore = "30";
			if(receiverPoints == 3)
				receiverScore = "40";
			
			
			if ((serverPoints < 3) && (receiverPoints < 3) && (serverPoints == receiverPoints))
				score = serverScore + "-all";
			else
				score = serverScore + "-" + receiverScore;
			//TODO add if scores are equal and the value is 0, 1, or 2
		}
		return score;
	}
	
	
	
}
