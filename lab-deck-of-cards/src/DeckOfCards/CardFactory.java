package DeckOfCards;

import java.util.ArrayList;
import java.util.List;

public class CardFactory
{
	public static List<Card> createSuit(Suit suit)
	{
		List<Card> out = new ArrayList<Card>(13);
		
		for (int i=1; i<14; i++)
		{
			out.add(new Card(suit, i));
		}
		
		return out;
	}
}
