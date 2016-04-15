import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MiscQuestions
{
	
	 public static void parseList(List<? extends Object> list) {
	    }

	public static void main(String[] args)
	{
		/*Scanner scanner = new Scanner (System.in);
		int number = scanner.nextInt();*/
		
		
/*		if (args==null || args.length==0)
			new java.lang.IllegalArgumentException("Invalid input");
		
		String strNumber = args[0];
		int number = Integer.parseInt(strNumber);*/
		
		//printFizzBizz(15);
		
		int diff = maxDifference(new int[]{2,3,10,2,4,8,1});
		
		System.out.println("diff "+diff);
		
	}
	
	static int maxDifference(int[] a) {

		int maxPoisition = findMaxPosition(a);
		int minPosition = findMinPosition(a, maxPoisition);
		
		
		System.out.println("maxPoisition "+maxPoisition);		
		System.out.println("minPosition "+minPosition);
		
		int diff = a[maxPoisition]-a[minPosition] ;
		
		if (diff < 1)
			return -1;
		else
			return diff;
					
		

    }
	
	static int findMaxPosition(int[] in)
	{
		int max=in[0];
		int maxPoisition=0;
		
		for (int i=1; i<in.length;i++)
		{
			if(in[i]>max)
			{
				max=in[i];
				maxPoisition=i;
			}
		}
		
		return maxPoisition;
	}
	
	static int findMinPosition(int[] in, int endIndex)
	{
		int min=in[0];
		int minPoisition=0;
		
		for (int i=0; i<endIndex;i++)
		{
			if(in[i]< min)
			{
				min=in[i];
				minPoisition=i;
			}
		}
		
		return minPoisition;
	}	
	
	 	static int KDifference(int[] a, int k) 
	 	{
	 		int count=0;
	 		for (int i=0; i < a.length;i++)
	 		{
	 			int n1 = a[i];
	 			
	 			
	 			for (int j=i+1; j<a.length;j++)
	 			{
	 				int n2 = a[j];
	 				
	 				int diff = Math.abs(n1-n2);
	 				
	 				if (diff==k)
	 					count++;
	 				
	 			}
	 		}


	 		return count;
	    }
	
	
	
	
	
	
	
	private static void printFizzBizz(int number)
	{
		
		for (int i = 1; i < number+1; i++)
		{
			
			boolean isFizz = i%3 ==0;
			boolean isBizz = i%5 ==0;
			
			if (isFizz && isBizz)
				System.out.println("fizzBizz");
			else if (isFizz)
				System.out.println("fizz");
			else if (isBizz)
				System.out.println("Bizz");
			else
				System.out.println(i);
		}
		
	}

	//[0] = 2
	//[1] = 5
	//[2] = 5
	//length = 3
	static long sumOfIntegers(int[] arr)
	{
		
		if (arr == null || arr[0] < 1)
		{
			throw new java.lang.IllegalArgumentException("Input is null");
		}

		int n = arr[0];
				
		if( n < 1)
		{
			throw new java.lang.IllegalArgumentException("first element should be greater than zero");
		}			
				
		int length = n+1;
				
		if (arr.length != length)
			throw new java.lang.IllegalArgumentException("input length expected " + length +"  but got " + arr.length);
		
		long sum =0;
		
		for (int i=1; i < length; i++)
		{
			if (sum + arr[i] > Long.MAX_VALUE)
				throw new java.lang.IllegalStateException("sum exceeded the max value for long data type");
			
			sum = sum + arr[i];
			
		}
		
		
		return sum;
	}

}
