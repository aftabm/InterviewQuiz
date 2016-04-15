package threading;


public class ThreadTest 
{
	private static Object obj = new Object();
	
	private static class Thread01 extends Thread
	{
		private Thread02 _thread02;
		public int foo=0;
		
		public void setThread02(Thread02 thread02)
		{
			_thread02 = thread02;
		}
		
		public void run()
		{
			try
			{

				for(int i=0; i < 10; i++)
					foo +=i;
				
				System.out.println("1-foo = "+foo);
				
				synchronized (obj) 
				{
					System.out.println("1-notify");
					obj.notify();
					System.out.println("1-wait-in");
					obj.wait();
					System.out.println("1-wait-out");
				}
				
				System.out.println("1- Foo: "+_thread02.foo);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	private static class Thread02 extends Thread
	{
		private final Thread01 _thread01;
		public int foo =0;
		
		public Thread02(Thread01 thread01)
		{
			_thread01 = thread01;
		}
		
		
		public void run()
		{
			try
			{				
				synchronized (obj) 
				{
					System.out.println("2- wait - in");
					obj.wait();
					System.out.println("2- wait - out");
				}
				
				foo = _thread01.foo;
				
				System.out.println("2-foo = "+foo);
				
				for (int i=0; i<10; i++)
					foo += i;
				
				System.out.println("2-foo = "+foo);
				
				synchronized (obj) 
				{
					obj.notify();
					System.out.println("2-notify");
				}
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{

		Thread01 thread01 = new Thread01();
		Thread02 thread02 = new Thread02(thread01);
		thread01.setThread02(thread02);
		
		thread01.start();
		thread02.start();
		
		try 
		{
			thread01.join();
			thread02.join();	
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
