package lab2;

public class Test4_ReverseWords {

	
	public static String reverseWords(String s)
	{
		/*
		 * Assume that the parameter String can only contain spaces and alphanumeric characters. 
		 * Please implement this method to reverse each word in the original String while maintaining the word order. 
		 * For example: parameter: "Hello world", result: "olleH dlrow"
		 */
		
		String[] words = s.split(" ");
		
		//Aftab >> there are more then one way of doing it based on time and space usage. 
		
		for (int i=0; i < words.length; i++)
		{
			String aWord = words[i];
			
			char[] chars = aWord.toCharArray();
			
			int k = chars.length-1;
			
			for (int j=0; j < k;j++, k--)
			{
				char temp = chars[j];
				chars[j] = chars[k];
				chars[k]=temp;				
			}
			
			words[i]=new String(chars);
		}

		
		
		String out="" ;
		
		for (String word:words)
			out = out + word+ " ";
		
		return out;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String result = reverseWords("Hello world");
		
		System.out.println(result);
	}

}
