package sorting.swap_based;

import sorting.Helper;
import sorting.StopWatch;

public class SelectionSort 
{

	public static void main(String[] args) 
	{
		int[] data = Helper.generateData(0, 10, 100);//5, 100 always sorted data
		Helper.print(data);
		StopWatch.getInstance().start();
		sort1(data);
		StopWatch.getInstance().stop();
		Helper.print(data);
		StopWatch.getInstance().printElapsedTime();

	}
	
//10,5,11,1,9,0,15
	public static void sort1(int[] data)
	{
		int temp=0;
		int step=1;
		for (int i=0; i < data.length-1; i++)
		{
			for (int j=i+1; j < data.length; j++)
			{
				if (data[j] < data[i])
				{
					temp = data[i];
					data[i]=data[j];
					data[j]=temp;
				}
				Helper.print(step++, data);
			}
			Helper.print(step++, data);
		}
		
	}

}
