package lab2;

public class Test1_CountWords 
{

	
	public static int countWords(String s)
	{
		/*
		 * Please implement this method to return the word count in a given String. 
		 * Assume that the parameter String can only contain spaces and alphanumeric characters.
		 */
		
		// assuming that each work is separated by single space
		
		String[] words = s.split("\\s");
		int wordCount=0;
		
		if (words!=null && words.length > 0)
		{
			for (int i=0; i < words.length; i++)
			{
				if (words[i].trim().length()>0)
					wordCount++;	
			}
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
		String data = "abc   def  ghi jkl mno pqr";
		int result = countWords(data);
		
		System.out.println("Words in ("+data+") are "+result);
	}

}
