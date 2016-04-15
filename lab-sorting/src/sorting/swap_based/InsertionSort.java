package sorting.swap_based;

import sorting.Helper;
import sorting.StopWatch;

public class InsertionSort 
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] data = Helper.getData(6,5,3,1,8,7,2,4);//5, 100 always sorted data
		Helper.print(data);
		StopWatch.getInstance().start();
		sort2(data);
		StopWatch.getInstance().stop();
		Helper.print(data);
		StopWatch.getInstance().printElapsedTime();

	}
	
	public static void sort1(int[] data)
	{
		int min;
		for (int i=1;i < data.length-1; i++)
		{
			min=data[i];
			for (int j=i; j < data.length; j++)
			{
				
			}
		}
	}
	
	//6, 5, 3, 1, 8, 7, 2, 4
	public static void sort2(int[] data)
	{
		int step=1;
		for (int i = 1; i < data.length; i++)
		{
			  int j = i;
			  int pivot = data[i];
		
			  while ((j>0) && (data[j-1] > pivot))
			  {
				  data[j] = data[j-1];
				  j--;
				  Helper.print(step++, data);  
			  }
			  
			  data[j] = pivot;
			  Helper.print(step++, data);
		}
	}
	

}
