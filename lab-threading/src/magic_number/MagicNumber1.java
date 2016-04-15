package magic_number;

public class MagicNumber1
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		showMagicNumber(100000, 999999);

	}
	
	
	
	private static void showMagicNumber(int start, int end)
	{
		
		int number = start;
		int counter=0;
		while (number <=end)
		{
			String[] tokens = split(number);
			int sum =  sum(tokens);
			int sqr= square(sum);
			
			if (number==sqr)
			{
				System.out.println(number);
				number = ((number/1000)+1)*1000;
				System.out.println("found. starting from "+number);	
			}
			else if ((number-sqr)<0)
			{
				number = ((number/1000)+1)*1000;
				System.out.println("skiping to "+number);							
			}
			else				
				number++;
			
			
			counter++;
						
		}
		
		System.out.println("Total iterations = "+counter);
		
	}



	private static int nextSeries(String[] tokens)
    {
		String lower = "000";
		String higher = tokens[0];		
		//int higherN = Integer.parseInt(s)
		
	    return 0;
    }



	private static int square(int number)
    {
	    
	    return number * number;
    }



	private static int sum(String[] tokens)
    {
	    int high = Integer.parseInt(tokens[0]);
	    int low = Integer.parseInt(tokens[1]);
	    return high+low;
    }



	private static String[] split(int index)
    {
		String strNumber = Integer.toString(index);
		String[] out = new String[2];
		
		out[0] = strNumber.substring(0, 3);
		out[1] = strNumber.substring(3, 6);
		
	    return out;
    }

}
