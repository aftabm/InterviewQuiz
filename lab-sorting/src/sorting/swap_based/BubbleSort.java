package sorting.swap_based;

import sorting.Helper;
import sorting.StopWatch;

public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int[] data = Helper.generateData(0, 5, 100);//best case (100, 5, 100)
		Helper.print(data);
		StopWatch.getInstance().start();
		sort2(data);
		StopWatch.getInstance().stop();
		Helper.print(data);
		StopWatch.getInstance().printElapsedTime();

	}
	
	
	public static void sort1(int[] data)
	{
		boolean swaped=false;
		int temp;
		do
		{
			swaped=false;
			for (int i=1; i < data.length; i++)
			{
				if (data[i-1] > data[i])
				{
					temp = data[i];
					data[i] = data[i-1];
					data[i-1] = temp;
					swaped=true;
				}
			}
			Helper.print(data);
		}while(swaped);
	}
	
	/*
	 * for (i = 0; i < n; i++)
     for (j = n-1; j > i; j--)
        if A[j-1] > A[j] then
           swap(A[j-1], A[j])
        end if
     end for
  end for
  */
	public static void sort2(int[] data)
	{
		int temp;
		int counter=1;
		for (int i=0;i<data.length;i++)
		{
			for (int j=data.length-1; j>i; j--)
			{
				if (data[j] < data[j-1])
				{
					temp = data[j-1];
					data[j-1] = data[j];
					data[j] = temp;
				}
				Helper.print(counter++, data);
			}
			Helper.print(counter++, data);
		}
	}

	

}
