package hw2;
import hw2.Set;
public class setTest {

	public static void main(String[] args) {
		
		System.out.println("\n \n Test 1 - Constructor & whoIsServeing");
		System.out.println("----------------------------------------");
		Set s = new Set(3,false);
		System.out.println("0  -  " + s.whoIsServing());
		
		
		System.out.println("\n \n Test 2 - fastForward(), isCurrentGameOver(), & player1GamesWon()");
		System.out.println("----------------------------------------");
		s.fastForward(0, 4);
		System.out.println("true  -  " + s.isCurrentGameOver());
		System.out.println("1  -  " + s.player1GamesWon());
		
		
		
		
		
		
		
		
		

	}

}
