package lab.aft.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author amahmood
 *
 * @param <T>
 */
public class MyListOfList<T> implements Iterable<T>
{

    private List<List<T>> myData;

    public MyListOfList()
    {
        myData = new ArrayList<List<T>>();
    }

    public void addData(List<T> newData)
    {
        myData.add(newData);
    }

    @Override
    public Iterator<T> iterator()
    {        
       return new MyIterator<T>(myData);
    }
    
    
    private class MyIterator<E>  implements Iterator<E>
    {
        private List<List<E>> data;
        private Iterator<List<E>> internalIterator = null;
        private Iterator<E> externalIterator = null;
        
        public MyIterator(List<List<E>> data)
        {
            this.data=data;
            internalIterator = this.data.iterator();
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
        public E next()
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
        

}
