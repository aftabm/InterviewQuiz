package lab3;

import java.awt.Point;

public class TestApp
{

    public TestApp()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        
        int[] numberList;
        
        Point p1 = new Point(1,1);
        Point p2 = new Point(2, 2);
        String s1 = "Hello";
        String s2 = "World";
     
        System.out.println( "p1.x=" + p1.x + " p1.y=" + p1.y + " p2="+p2.toString() );
        
        UpdateMe((Point)p1.clone(), p2);
        
        System.out.println( "p1.x=" + p1.x + " p1.y=" + p1.y + " p2="+p2.toString() );

        PrintArgs(new String[]{"A", "B", "C"});
    }
    
    static public void PrintArgs(String[] args)
    {
        StringBuilder result = new StringBuilder();
        
        for (int i =0; i < args.length; i++)
        {
            result.append(args[i]);
            
            if (i != args.length-1)
                result.append("; ");
            
        }
        
        System.out.println(result);
    }
    
    
    static public void UpdateMe(Point p1, Point p2)
    {
        p1.x = 100;
        p1.y = 101;
        
        Point temp;
        
        temp = p1;
        p1 = p2;
        p2 = temp;
        
    }

}
