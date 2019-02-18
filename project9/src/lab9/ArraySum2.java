package lab9;

import java.util.ArrayList;

public class ArraySum2{
/**
 * Try it out.
 */
public static void main(String[] args)
{
	int height = 4;
  int result = arraySum(pyramidHelper(height));
  System.out.println(result);
}
private static int[] pyramidHelper(int height){
		ArrayList <Integer> output = new ArrayList<Integer>();
		int level = 1;
		while (level <= height){
			output.add(level*level);
			level++;
		}
		int[] helper = new int[output.size()];
		for(int i=0; i<output.size();i++)
			helper[i]=output.get(i);
		System.out.println();
		return helper;
	}

public static int arraySum(int[] arr)
{
  return arraySumRec(arr, 0, arr.length - 1);
}

private static int arraySumRec(int[] arr, int start, int end)
{
  if (start == end)
  {
    return arr[start];
  }
  else
  {
    int mid = (start + end) / 2;
    int leftSum = arraySumRec(arr, start, mid);
    int rightSum = arraySumRec(arr, mid + 1, end);
    return leftSum + rightSum;
  }
}
}