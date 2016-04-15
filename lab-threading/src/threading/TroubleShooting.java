package threading;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class TroubleShooting 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{

		aMethod();
	}
	
	
	private static void aMethod()
	{
		int retry=-1;
		String line=null;
		
		do
		{
			try
			{
				BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream("input.txt"))
				);
				
				line = br.readLine();
				br.close();
			}
			catch(IOException e)
			{
				try {
					Thread.currentThread().sleep(250);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if (retry==-1)
					retry=3;
				else
					retry--;
				
			}
		}while(retry>0);
		
	}
}
