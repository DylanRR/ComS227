package testing;
import api.Cell;
import api.Direction;
import api.StringUtil;
import hw3.GameSupport;
public class playerShiftTest {

	public static void main(String[] args) {
		String test0 = "$.......#";	Cell[] testCell0 = StringUtil.createFromString(test0);
		String test1 = "$....@..#"; Cell[] testCell1 = StringUtil.createFromString(test1);
		String test2 = "$....o..#"; Cell[] testCell2 = StringUtil.createFromString(test2);
		String test3 = "$....x..#"; Cell[] testCell3 = StringUtil.createFromString(test3);
		String test4 = "$.O..O..#"; Cell[] testCell4 = StringUtil.createFromString(test4);
		String test5 = "$....<..#"; Cell[] testCell5 = StringUtil.createFromString(test5);
		String test6 = "$....>..#"; Cell[] testCell6 = StringUtil.createFromString(test6);
		String test7 = "$....^..#"; Cell[] testCell7 = StringUtil.createFromString(test7);
		String test8 = "$....v..#"; Cell[] testCell8 = StringUtil.createFromString(test8);
		String test9 = "$....*..#"; Cell[] testCell9 = StringUtil.createFromString(test9);
		String test10 = ".@...$@.#"; Cell[] testCell10 = StringUtil.createFromString(test10);
		
		System.out.println("\n  Test 0 - Wall");
		System.out.println("--------------------------");
		System.out.print("Start: "); StringUtil.printCellArray(testCell0);
		GameSupport.shiftPlayer(testCell0, null, Direction.RIGHT);
		System.out.print("End: "); StringUtil.printCellArray(testCell0);
		
		System.out.println("\n Test 1 - Pearl");
		System.out.println("--------------------------");
		System.out.print("Start: "); StringUtil.printCellArray(testCell1);
		GameSupport.shiftPlayer(testCell1, null, Direction.RIGHT);
		System.out.print("End: "); StringUtil.printCellArray(testCell1);

		System.out.println("\n Test 2 - Open Gate");
		System.out.println("--------------------------");
		System.out.print("Start: "); StringUtil.printCellArray(testCell2);
		GameSupport.shiftPlayer(testCell2, null, Direction.RIGHT);
		System.out.print("End: "); StringUtil.printCellArray(testCell2);

		System.out.println("\n Test 3 - Closed Gate");
		System.out.println("--------------------------");
		System.out.print("Start: "); StringUtil.printCellArray(testCell3);
		GameSupport.shiftPlayer(testCell3, null, Direction.RIGHT);
		System.out.print("End: "); StringUtil.printCellArray(testCell3);
		
		System.out.println("\n Test 4 - Portal");
		System.out.println("--------------------------");
		System.out.print("Start: "); StringUtil.printCellArray(testCell4);
		GameSupport.shiftPlayer(testCell4, null, Direction.RIGHT);
		System.out.print("End: "); StringUtil.printCellArray(testCell4);
		
		System.out.println("\n Test 5 - Left Spike");
		System.out.println("--------------------------");
		System.out.print("Start: "); StringUtil.printCellArray(testCell5);
		GameSupport.shiftPlayer(testCell5, null, Direction.RIGHT);
		System.out.print("End: "); StringUtil.printCellArray(testCell5);
		
		System.out.println("\n Test 6 - Right Spike");
		System.out.println("--------------------------");
		System.out.print("Start: "); StringUtil.printCellArray(testCell6);
		GameSupport.shiftPlayer(testCell6, null, Direction.RIGHT);
		System.out.print("End: "); StringUtil.printCellArray(testCell6);
		
		System.out.println("\n Test 7 - Up Spike");
		System.out.println("--------------------------");
		System.out.print("Start: "); StringUtil.printCellArray(testCell7);
		GameSupport.shiftPlayer(testCell7, null, Direction.RIGHT);
		System.out.print("End: "); StringUtil.printCellArray(testCell7);
		
		System.out.println("\n Test 8 - Down Spike");
		System.out.println("--------------------------");
		System.out.print("Start: "); StringUtil.printCellArray(testCell8);
		GameSupport.shiftPlayer(testCell8, null, Direction.RIGHT);
		System.out.print("End: "); StringUtil.printCellArray(testCell8);
		
		System.out.println("\n Test 9 - All Spike");
		System.out.println("--------------------------");
		System.out.print("Start: "); StringUtil.printCellArray(testCell9);
		GameSupport.shiftPlayer(testCell9, null, Direction.RIGHT);
		System.out.print("End: "); StringUtil.printCellArray(testCell9);
		
		System.out.println("\n Test 10 - Start in middle");
		System.out.println("--------------------------");
		System.out.print("Start: "); StringUtil.printCellArray(testCell10);
		GameSupport.shiftPlayer(testCell10, null, Direction.RIGHT);
		System.out.print("End: "); StringUtil.printCellArray(testCell10);
	}

}
