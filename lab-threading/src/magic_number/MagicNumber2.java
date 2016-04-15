package magic_number;

public class MagicNumber2
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		showMagicNumber(100000, 999999);

	}

	private static void showMagicNumber(int start, int end)
	{

		int number = start;
		int itrCounter = 0;

		int[] numbers;
		int deviation;

		while (number < end)
		{
			numbers = tokenize(number);
			deviation = deviationFromSqrOfSum(numbers[0], numbers[1]);

			if (deviation > 0)
			{
				//TBD limit check numbers[1]
				while (deviation > 0)
				{
					numbers[1] = numbers[1] + 20;
					
					deviation = deviationFromSqrOfSum(numbers[0], numbers[1]);
					itrCounter++;
				}

				while (deviation < 0)
				{
					numbers[1] = numbers[1] - 1;
					deviation = deviationFromSqrOfSum(numbers[0], numbers[1]);
					itrCounter++;
				}
			}

			if (deviation == 0)
			{
				number = combine(numbers[0], numbers[1]);
				System.out.println("found " + number);
			}

			number = combine(numbers[0] + 1, 0);

			itrCounter++;

		}

		System.out.println("Total iterations = " + itrCounter);
	}

	private static int deviationFromSqrOfSum(int i, int j)
	{
		int number = combine(i, j);
		int sqr = squareOfSum(i, j);
		return number - sqr;
	}

	private static int squareOfSum(int higher, int lower)
	{
		int sum = higher + lower;
		int sqr = sum * sum;
		return sqr;
	}

	private static int combine(int higher, int lower)
	{
		String highStr = Integer.toString(higher), lowStr = Integer.toString(lower);

		if (higher < 100)
		{
			highStr = highStr + "000";
			highStr = highStr.substring(0, 3);
		}
		if (lower < 100)
		{
			lowStr = "000" + lowStr;
			lowStr = lowStr.substring(lowStr.length() - 3, lowStr.length());
		}

		int out = Integer.parseInt(highStr + lowStr);

		return out;
	}

	private static int[] tokenize(int number)
	{
		String strNumber = Integer.toString(number);

		int[] out = new int[2];

		out[0] = Integer.parseInt(strNumber.substring(0, 3));
		out[1] = Integer.parseInt(strNumber.substring(3, 6));

		return out;
	}

}
