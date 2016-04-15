package DeckOfCards;

import java.util.Random;

public class RandomNumberGenerator
{
	private static Random random = new Random();
	
	public static int getNext(int max)
	{
		return random.nextInt(max);
	}
}
