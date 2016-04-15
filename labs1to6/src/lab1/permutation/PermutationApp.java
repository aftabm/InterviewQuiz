package lab1.permutation;

public class PermutationApp
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        char[] data = new char[]{'1', '2', '3', '4'};
        
        printPermutation(data);
    }
    
    
    private static void printPermutation(char[] data)
    {
        for (int fixedPivotIndex = 0; fixedPivotIndex < data.length; fixedPivotIndex++)
        {            
            for (int movingPivotIndex=0; movingPivotIndex < data.length; movingPivotIndex++)
            {
                if (!swap(movingPivotIndex, data))            
                  continue;
                
                System.out.println(data);
            }            
        }                
    }

    private static boolean swap(int i, char[] data)
    {        
        if (i >= data.length-1)
            return false;
        
        char temp;
        
        temp = data[i];
        data[i] = data[i+1];
        data[i+1] = temp;
                      
        return true;        
    }


}
