/**
 * @author dylanrr (Dylan Ragaishis)
 * @Description This class file contains all the methods needed to do the following
 * - Create a printer
 * - Set printer paper capacity
 * - Set printer paper amount
 * - Add paper to printer
 * - Add ink cartridge to printer
 * - Get status of ink cartridge
 * - Get current amount of paper in printer
 * - Get total amount of pages printed
 * - Print single sided documents
 * - Print double sided documents
 */

package hw1;

public class Printer {
	
	/**
	 * Constant variables
	 * INK_CAPACITY is the capacity of a new ink cartridge in ounces
	 * INK_USAGE is the amount of ink used per page printed in ounces
	 */
	public static final double INK_CAPACITY = 2.0;
	public static final double INK_USAGE = 0.0023;
	
	/**
	 * Instance variables
	 * paperCapacity - Max amount of paper that can be in printer
	 * paperCount - Amount of paper currently in printer
	 * inkCount - Amount of ink currently in printer
	 * printCount - Amount printed
	 */
	private int paperCapacity;
	private int paperCount;
	private double inkCount; // Does not build but subtracts ex. 2.0 - .0023 for 1 paper
	private int printCount;
	
	/**
	 * Constructs a printer that has the given capacity (number of sheets of paper it can hold). 
	 * Initially it contains no paper and a full ink cartridge.
	 * @param givenCapacity - Sets paperCapacity
	 */
	public Printer(int givenCapacity) {
		paperCapacity = givenCapacity;
		inkCount = INK_CAPACITY;
	}
	
	/**
	 * Constructs a printer that has the given capacity (number of sheets of paper it can hold). 
	 * Initially it contains the given number of sheets of paper and a full ink cartridge. 
	 * Note that if givenNumberOfSheets is greater than givenCapacity, 
	 * then the printer will initially contain givenCapacity sheets of paper.
	 * @param givenCapacity - Sets paper capacity
	 * @param givenNumberOfSheets - Sets paperCount
	 */
	public Printer(int givenCapacity, int givenNumberOfSheets) {
		paperCapacity = givenCapacity;
		if (givenNumberOfSheets > givenCapacity) // Checks for more paper then able to hold if true paperCount = max capacity
			paperCount = givenCapacity;
		else
			paperCount = givenNumberOfSheets;
		
		inkCount = INK_CAPACITY;
	}
	
	/**
	 * Adds the given number of sheets of paper to this printer, not exceeding the printer's capacity.
	 * @param additionalSheets - Amount of sheets to add to printer
	 */
	public void addPaper(int additionalSheets) {
		if ((additionalSheets + paperCount) > paperCapacity) // Checks for more paper then able to hold if true paperCount = max capacity
			paperCount = paperCapacity;
		else
			paperCount = paperCount + additionalSheets;
	}
	/**
	 * Gets the number of sheets of paper currently in this printer. This value is never negative.
	 * @return - Sheets of paper in printer
	 */
	public int getCurrentPaper() {
		int getCurrentPaper;
		if (paperCount < 0) // Less than 0 check
			getCurrentPaper = 0;
		else
			getCurrentPaper = paperCount;
		
		return getCurrentPaper;			
	}
	/**
	 * Returns the total number of sheets of paper printed by this printer since its construction. 
	 * Note this is counting sheets of paper, not pages printed, i.e. sheets used for two sided 
	 * printing still count as just one sheet.
	 * @return - printCount
	 */
	public int getTotalPaperUse() {
		return printCount;
	}
	
	/**
	 * Checks if the inkCount is less than what it takes to print a single sheet (INK_USAGE)
	 * @return - State of ink cartridge 
	 */
	public boolean isInkOut() {
		if (inkCount < INK_USAGE)
			return true;
		else
			return false;
	}
	
	/**
	 * Sets the inkCount to a new cartridge (INK_CAPACITY)
	 */
	public void replaceInk() {
		inkCount = INK_CAPACITY;
	}
	
	/**
	 * Simulates printing pages in one-sided mode, using the appropriate number of 
	 * sheets and a corresponding quantity of ink. If there is not enough paper, 
	 * the printer will use up all remaining paper and will only use the quantity 
	 * of ink needed for the sheets actually printed. If there is not enough ink, 
	 * the printer will use up all the ink, and will still use up the specified number of sheets of paper.
	 * @param numberOfPages - Amount of pages to print (Single sided)
	 */
	public void print(int numberOfPages) {
		if (paperCount > numberOfPages) {	// Checks if there is enough paper
			if((numberOfPages * INK_USAGE) < inkCount) {	// Checks if there is enough ink
			paperCount = paperCount - numberOfPages;
			inkCount = inkCount - (numberOfPages * INK_USAGE);
			printCount = printCount + numberOfPages;
			}
			else {	// Not enough paper
				double tempInkCount = inkCount / INK_USAGE;	// Calculates amount of pages able to print with ink
				tempInkCount = Math.floor(tempInkCount);	// Rounding down to nearest whole number
				int tempPageCount = (int)tempInkCount;		// double cast to int
				
				paperCount = paperCount - tempPageCount;
				inkCount = 0 ;	// Although the inkCount is not truly at 0 we know that there is not enough ink to print another page ie. less than INK_USAGE
				printCount = printCount + numberOfPages;
			}
		}
		else {	// Not enough paper
			inkCount = inkCount - (paperCount * INK_USAGE);
			printCount = printCount + paperCount;
			paperCount = 0;
		}			
	}
	
	/**
	 * Simulates printing pages in two-sided mode, using the appropriate number of sheets 
	 * and a corresponding quantity of ink. If there is not enough paper, the printer will 
	 * use up all remaining paper and will only use the quantity of ink needed for 
	 * the sheets actually printed. If there is not enough ink, the printer will use up all the ink, 
	 * and will still use up the specified number of sheets of paper.
	 * @param numberOfPages - Amount of pages to print in the form of 1 single side 
	 * (Printed doubled sided ie. if numOfPages = 2 it would print 1 piece of paper)
	 */
	public void printTwoSided(int numberOfPages) {
		double tempNumOfPageCount = numberOfPages;	// int to double
		double tempTotalNumOfPageCount = Math.ceil(tempNumOfPageCount / 2); // Calculates amount of paper need to print numberOfPages (ie. if numberOfPages is 3 it will take 2 sheets of paper to print because its double sided)

		if (paperCount > tempTotalNumOfPageCount) {	// Amount of paper check
			if((numberOfPages * INK_USAGE) < inkCount) {	// Amount of ink check
			inkCount = inkCount - (numberOfPages * INK_USAGE); // Calculates ink count
			printCount = printCount + (int) tempTotalNumOfPageCount;
			paperCount = paperCount - (int) tempTotalNumOfPageCount;
			}
			else {
				inkCount = 0;	// Although the inkCount is not truly at 0 we know that there is not enough ink to print another page ie. less than INK_USAGE
				printCount = printCount + (int) tempTotalNumOfPageCount;
				paperCount = paperCount - (int) tempTotalNumOfPageCount;
			}
		}
		else {	// Not enough paper
			inkCount = inkCount - (paperCount * INK_USAGE);
			printCount = printCount + paperCount;
			paperCount = 0;
		}
	}
}
