package blocking_queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BlockingQueue2<T>
{
    private LinkedList<T> list = new LinkedList<T>();
    private  int maxSize=0;
    private volatile int size=0; 
    private ReentrantReadWriteLock lock= new ReentrantReadWriteLock();
    private volatile boolean waiting=false;
    
    public BlockingQueue2(int maxSize)
    {
    	this.maxSize = maxSize;
    }


	public void add(T t)
    {
		if (waiting)
		{
			System.out.println(Thread.currentThread().getName()+" Already blocked, rejecting request. Queue size "+size);
			//return;// should throw exception
			throw new RuntimeException() ;
		}
    	try
    	{
    		if (size < maxSize)
    		{
    			System.out.println(Thread.currentThread().getName()+" Adding "+t+" Queue size "+size);
    			lock.writeLock().lock();
    			list.add(t);
    			size++;
    			lock.writeLock().unlock();
    		}
    		else
    		{
    			synchronized(lock)
    			{
    				waiting=true;
    				System.out.println(Thread.currentThread().getName()+" Queue is full. Wating for dequeue. Queue size "+size);
    				lock.wait();// TBD  timed wait;???? like lock.wait(1000); or  a monitor thread monitoring indefinite wait
    				waiting=false;
    			}
    			add(t);//call again
    		}
    	}
    	catch (InterruptedException e)
        {
    		e.printStackTrace();
        }
    	finally
    	{
    		if (lock.writeLock().isHeldByCurrentThread())
    			lock.writeLock().unlock();
    	}
    	
    }
    
    
    public  T remove()
    {
    	T t=null;
    	
    	try
    	{
    		lock.writeLock().lock();
    		t = list.remove();
    		size--;
    		lock.writeLock().unlock();
    		System.out.println(Thread.currentThread().getName()+" Removed "+t+" Queue size "+size);
    		
    		if (waiting)
    		{
    			synchronized (lock)
    			{
    				lock.notify();	
    				System.out.println(Thread.currentThread().getName()+" Notifying removeQueue size "+size);
    			}
    		}
    	}
    	catch(NoSuchElementException e)
    	{
    		//do nothing
    	}
    	finally
    	{  //in case of exception
    		if (lock.writeLock().isHeldByCurrentThread())
    			lock.writeLock().unlock();
    	}
    	
    	return t;
    }
	
    
    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		WorkerThread w1 = new WorkerThread();
		
	    w1.queue =  new BlockingQueue2<Integer>(5);
		
		Thread t1 = new Thread(w1);
		Thread t2 = new Thread(w1);
	    
		t1.start();
		t2.start();
	    

	}
	
	
	private static class WorkerThread implements Runnable
	{
		BlockingQueue2<Integer> queue ;
		Random random = new Random();
		int number=0;
		@Override
        public void run()
        {
			System.out.println(Thread.currentThread().getName()+" Starts -----------");
			
			for(int i=0; i<100;i++)
			{
				System.out.println(Thread.currentThread().getName()+" iteration "+i);
				number = random.nextInt(1000);
				System.out.println(Thread.currentThread().getName()+" Number "+number);
				
				if(number%2==0)
				{
					try
					{
						queue.add(new Integer(number));
					}
					catch(Throwable h)
					{
						
					}
				}
				else
				{
					queue.remove();
				}
			}
			
			System.out.println("-----------"+Thread.currentThread().getName()+" Done -----------");
        }
		
	}

}
