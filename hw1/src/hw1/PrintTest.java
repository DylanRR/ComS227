package hw1;

public class PrintTest {
	
	public static void main(String[] args)
	{
		Printer p = new Printer(100);
		p.addPaper(10);
		p.addPaper(15);
		System.out.println(p.getCurrentPaper());
		System.out.println("Expected 25");
		System.out.println(p.isInkOut());
		System.out.println("Expected false");
		
		p.print(5);
		System.out.println(p.getCurrentPaper());
		System.out.println("Expected 20");
		p.print(10);
		System.out.println(p.getCurrentPaper());
		System.out.println("Expected 10");
		System.out.println(p.getTotalPaperUse());
		System.out.println("Expected 15");
		
		p.print(10);
		System.out.println(p.getCurrentPaper());
		System.out.println("Expected 0");
		System.out.println(p.getTotalPaperUse());
		System.out.println("Expected 25");
		
		p.addPaper(150);
		System.out.println(p.getCurrentPaper());
		System.out.println("Expected 100");
		
	} 

}
