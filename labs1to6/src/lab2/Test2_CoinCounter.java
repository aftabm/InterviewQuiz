package lab2;

public class Test2_CoinCounter {

	public static Change getCorrectChange(int cents)
	{
		/*
		 * Please implement this method to take cents as a parameter and return an equal 
		 * amount in dollars and coins using the minimum number of coins possible. 
		 * For example: 164 cents = 1 dollar, 2 quarters, 1 dime and 4 cents.
		 */
		
		//doller = cents/100
		//quarter = cents/25
		//dime = 10/25
		
		int dollars=0, quarters=0, dimes=0,nickels=0;
		
		if (cents >= 100)
		{
			dollars = cents/100;
			cents = cents % 100;
		}
		
		if (cents >= 25)
		{
			quarters = cents/25;
			cents = cents % 25;			
		}
		
		if (cents >=10)
		{
			dimes = cents / 10;
			cents = cents % 10;
		}
		
		if (cents >=5)
		{
			nickels = cents / 5;
			cents = cents % 10;
		}
		
		
		Change myChange = new Change(dollars, quarters, dimes, nickels, cents);
		
		return myChange;
	}

	// Please do not change this class
	static class Change
	{
		private final int _dollars;
		private final int _quarters; // 25 cents
		private final int _dimes; // 10 cents
		private final int _nickels; // 5 cents
		private final int _cents; // 1 cent

		public Change(int dollars, int quarters, int dimes, int nickels, int cents)
		{
			_dollars = dollars;
			_quarters = quarters;
			_dimes = dimes;
			_nickels = nickels;
			_cents = cents;
		}

		public int getDollars()
		{
			return _dollars;
		}

		public int getQuarters()
		{
			return _quarters;
		}

		public int getDimes()
		{
			return _dimes;
		}

		public int getNickels()
		{
			return _nickels;
		}

		public int getCents()
		{
			return _cents;
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Change result = getCorrectChange(164);
		
		System.out.println("Dollers="+result.getDollars());
		System.out.println("Quarters="+result.getQuarters());
		System.out.println("Dimes="+result.getDimes());
		System.out.println("Nickels="+result.getNickels());
		System.out.println("Cents="+result.getCents());

	}

}
