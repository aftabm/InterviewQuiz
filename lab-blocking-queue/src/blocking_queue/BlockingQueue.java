package blocking_queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

public class BlockingQueue<T>
{
    private LinkedList<T> list = new LinkedList<T>();
    private int maxSize=0;
    
    public BlockingQueue(int maxSize)
    {
    	this.maxSize = maxSize;
    }


	public synchronized void add(T t)
    {
    	try
    	{
    		if (list.size() < maxSize)
    		{
    			list.add(t);
    		}
    		else
    		{
    			System.out.println("Queue is full. Wating for dequeue");
    			wait();
    			add(t);
    		}
    	}
    	catch (InterruptedException e)
        {
    		e.printStackTrace();
        }
    }
    
    
    public synchronized T remove()
    {
    	T t=null;
    	
    	try
    	{
    		t = list.remove();
    	}
    	catch(NoSuchElementException e)
    	{
    		//do nothing
    	}
    	finally
    	{  //in case of exception
    		
    		if(t!=null )// TBD need notify only when queue was full
    		{
        		notify();	
        		System.out.println("Notifying remove");
    		}
    	}
    	
    	return t;
    }
	
    
    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		WorkerThread w1 = new WorkerThread();
		
	    w1.queue =  new BlockingQueue<Integer>(5);
		
		Thread t1 = new Thread(w1);
		Thread t2 = new Thread(w1);
	    
		t1.start();
		t2.start();
	    

	}
	
	
	private static class WorkerThread implements Runnable
	{
		BlockingQueue<Integer> queue ;
		Random random = new Random();
		int number=0;
		@Override
        public void run()
        {
			for(int i=0; i<100;i++)
			{
				number = random.nextInt(100);
	        
				if(number%2==0)
				{
					System.out.println("Adding "+number);
					queue.add(new Integer(number));
				}
				else
				{
					System.out.println("Removed "+queue.remove());
				}
			}
        }
		
	}

}
