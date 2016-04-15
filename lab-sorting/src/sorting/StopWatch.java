package sorting;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StopWatch 
{
    private long startTime = 0;
    private long stopTime = 0;
    private boolean isRunning = false;
    private static StopWatch instance=  new StopWatch();
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSSS");
    
    private long getElapsedTime()
    {
        if (startTime == 0)
            return 0;

        if (isRunning)
            return System.currentTimeMillis() - startTime;
        else
            return stopTime - startTime;
    }

    private long startTime()
    {
        startTime = System.currentTimeMillis();
        isRunning = true;
        return startTime;
    }

    private long stopTime()
    {
        if (startTime == 0)
            return 0;

        isRunning = false;
        stopTime = System.currentTimeMillis();

        return stopTime;
    }


	public static StopWatch getInstance()
	{
		return instance;
	}
	
	public void start()
	{
		System.out.println( simpleDateFormat.format(new Date(this.startTime())));
	}
	
	public void stop()
	{
		System.out.println( simpleDateFormat.format(new Date(this.stopTime())));
	}
	
	
	public String getStartTime()
	{
		return simpleDateFormat.format(new Date(this.startTime));
	}
	
	public String getStopTime()
	{
		return simpleDateFormat.format(new Date(this.stopTime));
	}
	
	public String getElapsed()
	{
		return simpleDateFormat.format(new Date(getElapsedTime()));
	}
	
	public void reset()
	{
		this.stopTime=0;
		this.startTime=0;	
		this.isRunning=false;
		this.startTime();
	}
	
	public void printElapsedTime()
	{
		System.out.println(simpleDateFormat.format(new Date(getElapsedTime())));
	}
}
