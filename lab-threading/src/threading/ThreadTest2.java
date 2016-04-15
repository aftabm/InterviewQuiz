package threading;

public class ThreadTest2 
{

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
				
				synchronized (_thread02) 
				{
					System.out.println("1- notify");
					_thread02.notify();
				}
				synchronized (this) 
				{
					System.out.println("1- wait in");
					this.wait();
					System.out.println("1- wait out");
				}
				
				System.out.println("Foo: "+_thread02.foo);
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
				synchronized (this) 
				{
					this.wait();
				}

				foo = _thread01.foo;
				
				for (int i=0; i<10; i++)
					foo += i;
				
				System.out.println("2- foo ="+foo);
				
				synchronized (_thread01) 
				{
					_thread01.notify();
					
					System.out.println("2- Notify");
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
