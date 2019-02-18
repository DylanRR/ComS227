package project8;

import java.util.ArrayList;
import java.io.*;

public class LineNumberer {

	public static void removeDuplicates(ArrayList <String> words){
		for (int i=0; i < words.size(); i++){
			String check = words.get(i);
			for (int a= i+1; a < words.size(); a++)
				if(words.get(i).equalsIgnoreCase(words.get(a)))
					words.remove(a);
		}
		System.out.println(words);
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		ArrayList <String> output = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/dylanrr/Desktop/story.txt"))) { //Try-Catch
            String line;
            while ((line = br.readLine()) != null) {
            	int count = 0;
                String []mainLine = line.split(" ");
                	for (String i : mainLine)
                		count++;
                	if (count == 1)
                		count = 0;
                    System.out.println(count);
                }

            } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       // */
		ArrayList <String> remove = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/dylanrr/Desktop/test.txt"))) { //Try-Catch
            String line;
            while ((line = br.readLine()) != null) {
                String []mainLine = line.split(" ");
                for (int i=0; i < mainLine.length; i++)
                	remove.add(mainLine[i]);
                removeDuplicates(remove);
                remove.clear();
                }
            } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
}
