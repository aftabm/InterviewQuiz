package lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Test3_PositiveOnly {

	
	
	public static int[] retainPositiveNumbers(int[] a)
	{
		/*
		 * Please implement this method to return a new array with only positive numbers from the given array. 
		 * The elements in the resulting array shall be sorted in the ascending order.
		 */
		
		
		//Aftab>>there are many ways to do it. We can discuss its time and space complexity 
		
		if (a==null || a.length ==0)
			return a;
		
		List<Integer> result = new ArrayList<Integer>();
		
		for (int i: a)
		{
			if (i < 0)
			{
				continue;
			}
			
			result.add(i);
		}
		
		Collections.sort(result); //a short cut

		int[] out=null; //usually we do not return null array. Should be array of zero length.
		int index=0;
		
		if (result!=null)
		{
		   out = new int[result.size()];
		   
		   for (Integer myInt: result)
		   {
			   out[index] = myInt;
			   index++;
		   }
		   
		   
		}
		
		return out;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int[] data = new int[]{8,23,100,1,0,-1,333};
		
		int[] result = retainPositiveNumbers(data);
		
		for (int i : result)
		{
			System.out.println(i);	
		}
		

	}

}
