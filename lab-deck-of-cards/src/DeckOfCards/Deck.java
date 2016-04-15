package DeckOfCards;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Deck
{
	private List<Card> cards=new ArrayList<Card>();
	private Stack<Card> cardStack = new Stack<Card>();
	
	public void init()
	{
		cards.clear();
		cards.addAll(CardFactory.createSuit(Suit.Clubs));
		cards.addAll(CardFactory.createSuit(Suit.Diamonds));
		cards.addAll(CardFactory.createSuit(Suit.Hearts));
		cards.addAll(CardFactory.createSuit(Suit.Spades));		
	}
	
	public boolean isValidDeck()
	{
		
		if (cardStack.size()!=cards.size())
			return false;
		
		Card[] cardsToTest = new Card[cardStack.size()];
		cardStack.toArray(cardsToTest);
		
		Card[] clubs = new Card[13];		
		Card[] diamonds = new Card[13];
		Card[] hearts = new Card[13];
		Card[] spades = new Card[13];
		
		int clubsCount=0;		
		int diamondsCount=0;
		int heartsCount=0;
		int spadesCount=0;

		
		Card card;
		for (int i=0; i < cardStack.size(); i++)
		{
			card = cardsToTest[i];
			
			switch(card.suit)
			{
				case Clubs:
					if (clubs[card.rankNumber-1]!=null)
						return false;
					else
						clubs[card.rankNumber-1]=card;
						clubsCount++;
					break;
					
				case Diamonds:
					if (diamonds[card.rankNumber-1]!=null)
						return false;
					else
						diamonds[card.rankNumber-1]=card;
						diamondsCount++;
					break;
					
				case Hearts:
					if (hearts[card.rankNumber-1]!=null)
						return false;
					else
						hearts[card.rankNumber-1]=card;
						heartsCount++;
					break;
					
				case Spades:
					if (spades[card.rankNumber-1]!=null)
						return false;
					else
						spades[card.rankNumber-1]=card;
						spadesCount++;
					break;					
			}
		}//end for
		
		if ( (clubsCount+diamondsCount+heartsCount+spadesCount)/4 != 13 )
			return false;

		return true;
	}
	
	public void shuffle()
	{
		cardStack.clear();
		int randomIndex=0;
		int tempIndex=0;
		Card card=null;
		
		for (int i=0; i<cards.size(); i++)
		{
			randomIndex = RandomNumberGenerator.getNext(52);			
			card = cards.get(randomIndex);
			
			if (card==null)
			{
				//try again upto 52 times;
				do
				{
					randomIndex = RandomNumberGenerator.getNext(52);
					card = cards.get(randomIndex);
					tempIndex++;
				}
				while(card==null && tempIndex < cards.size());
				
				if (card==null)
				{
					//if it is still null pick from top
					for (tempIndex=0;(card==null)&&(tempIndex < cards.size()); tempIndex++)
					{
						card = cards.get(tempIndex);
					}
					
					if (card==null)
						throw new IllegalStateException("card is still null");
					else
						cards.set(tempIndex-1, null);
				}
				else
				{
					cards.set(randomIndex, null);
				}
			}
			else
			{
				cards.set(randomIndex, null);
			}
				
				
				
			cardStack.push(card);
		}
		
		if (cardStack.size()!=cards.size())
			throw new IllegalStateException("invalid stack. numbers does not match.");
	}
	
	
	public Card draw()
	{
		if (!cardStack.isEmpty())
			return cardStack.pop();
		else
			return null;		
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Deck deck = new Deck();
		deck.init();
		deck.shuffle();
		System.out.println("IsValidDeck "+deck.isValidDeck());
		
		for (int i=0; i < 52; i++)
			System.out.println(deck.draw());
	}
	
	
}
