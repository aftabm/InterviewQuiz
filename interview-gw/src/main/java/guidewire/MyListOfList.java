package guidewire;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MyListOfList<T> implements Iterator<T>, Iterable<T>
{

    private List<List<T>> myData;
    private Iterator<List<T>> internalIterator = null;
    private Iterator<T> externalIterator = null;

    public MyListOfList()
    {
        myData = new ArrayList<List<T>>();
    }

    public void addData(List<T> newData)
    {
        myData.add(newData);
    }

    @Override
    public boolean hasNext()
    {
        if (externalIterator != null && externalIterator.hasNext())
        {
            return true;
        }

        if (internalIterator.hasNext())
        {
            externalIterator = internalIterator.next().iterator();
            return hasNext();
        }

        return false;
    }

    @Override
    public Iterator<T> iterator()
    {
        internalIterator = myData.iterator();
        return this;
    }

    @Override
    public T next()
    {
        if (!hasNext())
        {
            throw new NoSuchElementException();
        }

        return externalIterator.next();
    }

    @Override
    public void remove()
    {
        // TODO Auto-generated method stub
    }

}
