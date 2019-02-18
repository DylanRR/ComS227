package hw2;
import hw2.TennisGame;
public class TennisGameTest {

	public static void main(String[] args) {
		System.out.println("\n \n Test 1 - Ball status direction");
		System.out.println("----------------------------------------");
		TennisGame g1 = new TennisGame();
		System.out.println("NOT_IN_PLAY  -  " + g1.getBallStatus());
		g1.serve(false);
		System.out.println("TOWARD_RECEIVER  -  " + g1.getBallStatus());
		g1.hit(false, false);
		System.out.println("TOWARD_SERVER  -  " + g1.getBallStatus());
		g1.hit(false, false);
		System.out.println("TOWARD_RECEIVER  -  " + g1.getBallStatus());
		
		
		System.out.println("\n \n Test 2 - Player fault");
		System.out.println("----------------------------------------");
		g1.hit(true, false);
		System.out.println("NOT_IN_PLAY  -  " + g1.getBallStatus());
		
		
		System.out.println("\n \n Test 3 - Player fault point award");
		System.out.println("----------------------------------------");
		System.out.println("1  -  " + g1.getServerPoints());

		
		System.out.println("\n \n Test 4 - Miss results in a ball out of play");
		System.out.println("----------------------------------------");
		TennisGame g2 = new TennisGame();
		g2.serve(false);
		g2.hit(false, false);
		g2.miss();
		System.out.println("NOT_IN_PLAY  -  " + g2.getBallStatus());
		System.out.println("1  -  " + g2.getReceiverPoints());
		
		
		System.out.println("\n \n Test 5 - Service faults");
		System.out.println("----------------------------------------");
		TennisGame g3 = new TennisGame();
		g3.serve(true);
		System.out.println("NOT_IN_PLAY  -  " + g3.getBallStatus());
		g3.serve(true);
		System.out.println("1  -  " + g3.getReceiverPoints());
		g3.serve(true);
		System.out.println("1  -  " + g3.getReceiverPoints());
		
		
		System.out.println("\n \n Test 6 - Hits");
		System.out.println("----------------------------------------");
		TennisGame g = new TennisGame();
		g.serve(false);
		System.out.println("TOWARD_RECEIVER  -  " + g.getBallStatus());
		g.hit(false, true); System.out.println("TOWARD_SERVER  -  " + g.getBallStatus()); // TOWARD_SERVER
		// this should be a point for server, since ball was headed out of bounds 
		g.miss();
		System.out.println("1-0  -  " + g.getScore()); // 1-0
		// ball headed out of bounds, but server hits it back anyway 
		g.serve(false);
		g.hit(false, true);
		g.hit(false, false);
		System.out.println("TOWARD_RECEIVER  -  " + g.getBallStatus()); // TOWARD_RECEIVER
		// receiver misses, but this is a point for server since // ball is *not* headed out of bounds
		g.miss();
		System.out.println("2-0  -  " + g.getScore()); // 2-0
		
		
		System.out.println("\n \n Test 7 - setScoer(), isOver(), serverWon(), and receiverWon()");
		System.out.println("----------------------------------------");
		TennisGame g4 = new TennisGame();
		g4.setScore(1, 3);
		System.out.println("1-3  -  " + g4.getScore());
		System.out.println("FALSE  -  " + g4.isOver());
		System.out.println("FALSE  -  " + g4.receiverWon());
		System.out.println("FALSE  -  " + g4.serverWon());
		g4.setScore(2, 4);
		System.out.println("2-4  -  " + g4.getScore());
		System.out.println("TRUE  -  " + g4.isOver());
		System.out.println("TRUE  -  " + g4.receiverWon());
		System.out.println("FALSE  -  " + g4.serverWon());
		
		
		System.out.println("\n \n Test 8 - setScoer(), and getCallString()");
		System.out.println("----------------------------------------");
		TennisGame g5 = new TennisGame();
		g5.setScore(0, 3);
		System.out.println("love-40  -  " + g5.getCallString());
		g5.setScore(2, 1);
		System.out.println("30-15  -  " + g5.getCallString());
		g5.setScore(2, 2);
		System.out.println("30-all  -  " + g5.getCallString());
		g5.setScore(1, 1);
		System.out.println("15-all  -  " + g5.getCallString());
		g5.setScore(0, 0);
		System.out.println("love-all  -  " + g5.getCallString());
		g5.setScore(3, 4);
		System.out.println("advantage out  -  " + g5.getCallString());
		g5.setScore(4, 3);
		System.out.println("advantage in  -  " + g5.getCallString());
		g5.setScore(4, 4);
		System.out.println("deuce  -  " + g5.getCallString());
	}

}
