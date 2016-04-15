package lab2;

public class Test5_CapitalLetter {

	
	public static String capitalizeFirstLetters(String s)
	{
		/*
		 * Please implement this method to capitalize all first letters of the words in the given String. 
		 * All other symbols shall remain intact. If a word starts not with a letter, it shall remain intact too. 
		 * Assume that the parameter String can only contain spaces and alphanumeric characters. 
		 * NOTE: please keep in mind that the words can be divided by single or multiple spaces. 
		 * The spaced also can be found at the beginning or the end of the parameter string, 
		 * and you need to preserve them.
		 */
		
		char[] chars = s.toCharArray();
		boolean capNext=false;
		
		for (int i=0; i < chars.length; i++)
		{
		
			if(chars[i]==' ')
			{
				chars[i]=chars[i];
				capNext = true;
				continue;
			}
			
			if (capNext==true)
			{
				chars[i]=Character.toUpperCase(chars[i]);
				capNext = false;
			}
			else
				chars[i]=chars[i];
		}
		
		return new String(chars);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String result = capitalizeFirstLetters(" this  is a test");
		System.out.println(result);
		result = capitalizeFirstLetters(" 1this  i2s a test");
		System.out.println(result);
	}

}
