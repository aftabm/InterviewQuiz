package lab6;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * @author amahmood
 */
public class LottoGenerator
{

    /**
     * 
     */
    public LottoGenerator()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

        ArrayList lottoNumber = getRandomNumbers(6,53, new Random());
        Collections.sort(lottoNumber);
        printLottoNumber(lottoNumber);
        
        //Other tests
        //printLottoNumber(getRandomNumbers(Integer.MAX_VALUE,Integer.MAX_VALUE, new Random()));
        //printLottoNumber(getRandomNumbers(Short.MAX_VALUE,Short.MAX_VALUE, new Random()));
        //printLottoNumber(getRandomNumbers(Byte.MAX_VALUE*10,Integer.MAX_VALUE, new Random()));
        
        //printLottoNumber(getRandomNumbers(-6,53, new Random()));     
        //printLottoNumber(getRandomNumbers(6,5, new Random()));
    }

    private static ArrayList getRandomNumbers(int length,  int upperLimit, Random generator)
    {
        if (length > Byte.MAX_VALUE)
            throw new IllegalArgumentException("Length cannot be more than "+ Byte.MAX_VALUE);
        
        if (length <0 ||  upperLimit < 0)
            throw new IllegalArgumentException("one or more arguments are less then zero ");
        
        if (length > upperLimit)
            throw new IllegalArgumentException("Length should be less than upperLimit");
  
        ArrayList numberList = new ArrayList(length);
        
        int number;
        
        for (int count=0; count < length; count++)
        {
           number = generator.nextInt(upperLimit);
           
           while (numberList.contains(Integer.valueOf(number) ))
               number = generator.nextInt(upperLimit);
            
           numberList.add(Integer.valueOf(number));
        }
        
       return numberList; 
    }
    
    private static void printLottoNumber(ArrayList numbers)
    {
        if (numbers == null || numbers.isEmpty())
            return;
        
        System.out.println(numbers.toString());    
    }
}
