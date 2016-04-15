package lab1.permutation;

public class Unique 
{

	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	
		System.out.println(isUnique("this"));
		System.out.println(isUnique("test"));
		System.out.println(isUnique("that"));
		System.out.println(isUnique("abcdefghijklmnopqurestuvwxyz"));
		System.out.println(isUnique("is"));
		System.out.println(isUnique("a"));
		
	}
	
	public static boolean isUnique(String str)
	{
		char[] temp = new char[26]; // assuming that is is lower case English word, with alphabets only
		
		for (char a :str.toCharArray())
		{
			if (temp[a-97]==1)
				return false;
			
			else
				temp[a-97]=1;
		}
		
		return true;
	}

}
