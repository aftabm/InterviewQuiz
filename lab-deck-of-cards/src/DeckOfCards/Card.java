package DeckOfCards;

public class Card
{

	public int rankNumber; // 1 to 13
	public Suit suit; //Spades, Hearts, Diamonds, Clubs
	private String rankLabel; //2 to 10, A, J, Q, K
	
	public Card(Suit suit, int rankNumber)
    {
		this.suit = suit;
		this.rankNumber = rankNumber;		
    }

	@Override
    public String toString()
    {
	    return "Card [suit=" + suit + ", rankNumber=" + rankNumber +  "]";
    }

	@Override
    public int hashCode()
    {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + rankNumber;
	    result = prime * result + ((suit == null) ? 0 : suit.hashCode());
	    return result;
    }

	@Override
    public boolean equals(Object obj)
    {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (getClass() != obj.getClass())
		    return false;
	    Card other = (Card) obj;
	    if (rankNumber != other.rankNumber)
		    return false;
	    if (suit != other.suit)
		    return false;
	    return true;
    }	
}
