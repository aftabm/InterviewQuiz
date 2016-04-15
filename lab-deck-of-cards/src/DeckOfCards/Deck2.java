package DeckOfCards;

import java.util.ArrayList;
import java.util.List;

public class Deck2
{
	private List<Card> cards=new ArrayList<Card>();
	
	
	public void init()
	{
		cards.clear();
		cards.addAll(CardFactory.createSuit(Suit.Clubs));
		cards.addAll(CardFactory.createSuit(Suit.Diamonds));
		cards.addAll(CardFactory.createSuit(Suit.Hearts));
		cards.addAll(CardFactory.createSuit(Suit.Spades));		
		
	}
	
	
	public void shuffle()
	{
		int randomIndex;
		Card temp;
		
		for (int i=0; i<cards.size(); i++)
		{
			randomIndex = RandomNumberGenerator.getNext(cards.size());
			
			temp = cards.get(randomIndex);
			cards.set(randomIndex, cards.get(i));
			cards.set(i, temp);
		}
	}
	
	public Card draw()
	{
		if (!cards.isEmpty())
			return cards.remove(0);
		else
			return null;		
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Deck2 deck = new Deck2();
		deck.init();
		deck.shuffle();
		deck.shuffle();
		deck.shuffle();
		deck.shuffle();
		
		for (int i=0; i < 52; i++)
			System.out.println(deck.draw());
			
	}
	
	
}
