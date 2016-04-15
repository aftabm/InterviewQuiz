package guidewire.test;

import static org.junit.Assert.assertNotNull;
import guidewire.MyListOfList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

public class testMyListOfList
{

    private List<String> getTestData(String preFix)
    {
        List<String> testData = new ArrayList<String>();

        testData.add(preFix + "A");
        testData.add(preFix + "B");
        testData.add(preFix + "C");

        return testData;

    }

    @Test
    public void testHasNextHappyCase()
    {

        MyListOfList<String> myLl = new MyListOfList<String>();

        myLl.addData(getTestData("0"));
        myLl.addData(getTestData("1"));

        Iterator<String> myItr = myLl.iterator();

        while (myItr.hasNext())
            System.out.println(myItr.next());

    }

    @Test
    public void testHasNextNoData()
    {
        MyListOfList<String> myLl = new MyListOfList<String>();
        Iterator<String> myItr = myLl.iterator();

        while (myItr.hasNext())
            System.out.println(myItr.next());

    }
    
    @Test
    public void testNextWithAllEmptyList()
    {
        MyListOfList<String> myLl = new MyListOfList<String>();

        myLl.addData(new ArrayList<String>());
        myLl.addData(new ArrayList<String>());
        myLl.addData(new ArrayList<String>());

        Iterator<String> myItr = myLl.iterator();

        while (myItr.hasNext())
            System.out.println(myItr.next());
    }

    @Test
    public void testNextWithAnEmptyList()
    {
        MyListOfList<String> myLl = new MyListOfList<String>();

        myLl.addData(getTestData("0"));
        myLl.addData(new ArrayList<String>());
        myLl.addData(getTestData("1"));

        Iterator<String> myItr = myLl.iterator();

        while (myItr.hasNext())
            System.out.println(myItr.next());
    }

    @Test
    public void testNextWithAnLastEmpty()
    {
        MyListOfList<String> myLl = new MyListOfList<String>();

        myLl.addData(getTestData("0"));
        myLl.addData(getTestData("1"));
        myLl.addData(new ArrayList<String>());

        Iterator<String> myItr = myLl.iterator();

        while (myItr.hasNext())
            System.out.println(myItr.next());
    }

    @Test
    public void testNextWithException()
    {
        MyListOfList<String> myLl = new MyListOfList<String>();

        Iterator<String> myItr = myLl.iterator();

        try
        {
            myItr.next();
        }
        catch (NoSuchElementException e)
        {
            assertNotNull(e);
        }

    }

}
