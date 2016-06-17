import java.util.Arrays;

public class CircularArrayQueue 
{

	private String[] array=null;
	private int head=0;
	private int tail=0;
	private int capacity=0;
	private int size=0;
	
	public CircularArrayQueue(int capacity)
	{
		this.array = new String[capacity]; 
		this.capacity = capacity;
	}
	
	  
    //head = 0;
    //tail = 10;
    
    //head = 9;
    //tail = 0;        
    
    //head = 5;
    //tail = 10;
    
    
    //head = 7;
    //tail = 7;
	
	
	public int getCapacity()
	{
		return this.capacity;
	}
	public boolean isEmpty()
	{		
		if (size == 0)
			return true;
		
		return false;
	}
	
	
	public boolean isFull()
	{
		if (size == capacity)
			return true;
			
		return false;
	}
	
	
	
	public void enqueue(String item)
	{		
		if( !isFull() )
		{
			array[tail] = item;			
			tail = (tail + 1) % capacity;
			size++;
		}
		else
			throw new java.lang.IllegalStateException("Queue is full");
		
	}
	
	public void reset()
	{
		array = new String[capacity];
		head = 0; 
		tail = 0;
		size = 0;
	}
	
	public String dequeue()
	{
		if (!isEmpty())
		{
			String out = array[head];
			array[head]=null;
			
			head = (head + 1) % capacity;
			size--;
			return out;
		}
		else
			throw new java.lang.IllegalStateException("Queue is empty");
				
	}
	
	
	public static void main(String[] args) 
	{
		
		test1();
		test2();
		test3();
		test4();
	}
	
	public int getSize()
	{
		return size;
	}
	
	public String toString()
	{
		return Arrays.toString(array);
	}
	
	//queue spill over
	public static void test1()
	{
		System.out.println("----------------------------------------------");
		System.out.println("TEST-CASE-1: Inserting into already full queue");
		CircularArrayQueue queue = new CircularArrayQueue(5);
		try
		{

			queue.reset();
			queue.enqueue("A");
			queue.enqueue("B");
			queue.enqueue("C");
			queue.enqueue("D");
			queue.enqueue("E");
			queue.enqueue("F");
			
			System.err.println("FAILED: ");
		}
		catch(Exception e)
		{
			System.out.println("PASSED: ");
		}
		System.out.println("Queue = "+queue);
		System.out.println("----------------------------------------------");		
	}
	
	
	
	public static void test2()
	{
		System.out.println("----------------------------------------------");
		System.out.println("TEST-CASE-2: Dequeue from empty queue");
		CircularArrayQueue queue = new CircularArrayQueue(5);
		try
		{
			queue.reset();
			queue.dequeue();
			
			System.err.println("FAILED: ");
		}
		catch(Exception e)
		{
			System.out.println("PASSED: ");
		}
		System.out.println("Queue = "+queue);
		System.out.println("----------------------------------------------");		
	}
	


	public static void test3()
	{
		System.out.println("----------------------------------------------");
		System.out.println("TEST-CASE-3: Validate queue size");
		CircularArrayQueue queue = new CircularArrayQueue(5);
		
		try
		{
			queue.reset();
			queue.enqueue("A");
			queue.enqueue("B");
			queue.enqueue("C");
			queue.enqueue("D");
			queue.enqueue("E");
			queue.enqueue("F");
		
			System.err.println("FAILED: ");
		}
		catch(Exception e)
		{
			if (queue.getSize()==5)
				System.out.println("PASSED: ");
			else
				System.err.println("FAILED: ");
		}
		System.out.println("Queue = "+queue);
		System.out.println("Size = "+queue.getSize());
		System.out.println("----------------------------------------------");		
	}
	
	
	
	public static void test4()
	{
		System.out.println("----------------------------------------------");
		System.out.println("TEST-CASE-4:  enqueue and dequeue");
				
		CircularArrayQueue queue = new CircularArrayQueue(5);
		
		
		try
		{
			queue.reset();
			queue.enqueue("A");
			
			
			String result = queue.dequeue();

			
			System.out.print(" Step 1: enqueue A, dequeue A > ");
			if ("A".equals(result))
			{
				System.out.println("PASSED");
			}
			else
			{
				System.out.println("FAILD");
			}
			
			System.out.println("Queue = "+queue);
						
			queue.enqueue("B");
			queue.enqueue("C");
			result = queue.dequeue();

			
			System.out.print("Step 2: enqueue B, C, dequeue B > ");
			if ("B".equals(result))
			{
				System.out.println("PASSED" );
			}
			else
			{
				System.out.println("FAILD");
			}
			
			System.out.println("Queue = "+queue);
			
			
			queue.enqueue("D");
			queue.enqueue("E");
			queue.enqueue("F");
			result = queue.dequeue();
			
			System.out.print("Step 3: enqueue D, E, F, dequeue C >");
			if ("C".equals(result))
			{
				System.out.println("PASSED");
			}
			else
			{
				System.out.println("FAILD");
			}
			
			System.out.println("Queue = "+queue);

			queue.enqueue("G");
			result = queue.dequeue();
			result = queue.dequeue();
			
			System.out.print("Step 4: enqueue G, dequeue E, F > ");
			if ("E".equals(result))
			{
				System.out.println("PASSED");
			}
			else
			{
				System.out.println("FAILD");
			}
			
			System.out.println("Queue = "+queue);

			
			System.out.print("Step 5: queue size should be 2 > ");
			if (queue.getSize()==2)
			{
				System.out.println("PASSED");
			}
			else
			{
				System.out.println("FAILD");
			}
			
			
		}
		catch(Exception e)
		{
			System.err.println("FAILED: ");
		}
		System.out.println("Queue = "+queue);
		System.out.println("----------------------------------------------");		
	}	

}
