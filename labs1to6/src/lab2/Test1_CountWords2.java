package lab2;

public class Test1_CountWords2 
{

	
	public static int countWords(String s)
	{
		/*
		 * Please implement this method to return the word count in a given String. 
		 * Assume that the parameter String can only contain spaces and alphanumeric characters.
		 */
		
		// assuming that each work is separated by single space
		
		String[] words = s.trim().split(" ");
		int wordCount=0;
		
		if (words!=null && words.length > 0)
		{
			wordCount = words.length;
		}
		
		
		return wordCount;
	}
	

	/**
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		String data = "abc def ghi jkl mno pqr";
		int result = countWords(data);
		
		System.out.println("Words in ("+data+") are "+result);
		
	}

}
