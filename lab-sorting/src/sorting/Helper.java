package sorting;

import java.util.Arrays;
import java.util.Random;

public class Helper 
{
	
	public static int[] getData(int...data)
	{
		return data;
	}

	public static int[] generateData(int seed, int size,int min, int max)
	{
		int[] out = new int[size];
		Random random;
		
		if (seed>0)
			random= new Random(seed);
		else
			random = new Random();

		int randomNumber=0;
		
		for (int i=0; i<size; i++)
		{
			do
			{
				randomNumber = random.nextInt();
			}		
			while(randomNumber < min || randomNumber > max);
			
			out[i]=randomNumber;
		}
		
		return out;
	}
	
	public static int[] generateData(int ...data)
	{
		return data;
	}
	
	
	public static int[] generateData(int seed, int size, int max)
	{
		int[] out = new int[size];
		Random random;
		
		if (seed>0)
			random= new Random(seed);
		else
			random = new Random();

		int randomNumber=0;
		
		for (int i=0; i<size; i++)
		{
			randomNumber = random.nextInt(max);
			out[i]=randomNumber;
		}
		
		return out;
	}
	
	
	public static void print(int[] data)
	{
		for (int i=0; i < data.length; i++)
		{
			System.out.print(data[i]);
			System.out.print(", ");
		}
		System.out.println("");
	}
	
	public static void print(int stepNumber, int[] data)
	{
		System.out.print(stepNumber +"- ");
		
		for (int i=0; i < data.length; i++)
		{
			System.out.print(data[i]);
			System.out.print(", ");
		}
		System.out.println("");
	}	
	
	public static int[] clone(int[] data)
	{
		return Arrays.copyOf(data, data.length);
	}
	
	
}
