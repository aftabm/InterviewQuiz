package guidewire.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import guidewire.StopWatchApp;

import org.junit.Test;

public class testStopWatch
{
    private long SLEEP_TIME = 1000;

    @Test
    public void testGetElapsedTime()
    {
        StopWatchApp stopWatch = new StopWatchApp();

        long startTime = stopWatch.start();

        try
        {
            Thread.sleep(SLEEP_TIME);
        }
        catch (InterruptedException e)
        {
            fail(e.getMessage());
        }

        long stopTime = stopWatch.stop();
        long elapsedTime = stopWatch.getElapsedTime();

        assertTrue(stopTime == elapsedTime);
    }

    @Test
    public void testGetElapsedTimeNoStart()
    {
        StopWatchApp stopWatch = new StopWatchApp();

        long elapsedTime = stopWatch.getElapsedTime();

        assertTrue(elapsedTime == 0);
    }

    @Test
    public void testGetElapsedTimeNoStop()
    {
        StopWatchApp stopWatch = new StopWatchApp();

        long startTime = stopWatch.start();

        try
        {
            Thread.sleep(SLEEP_TIME);
        }
        catch (InterruptedException e)
        {
            fail(e.getMessage());
        }

        long elapsedTime = stopWatch.getElapsedTime();

        assertTrue(elapsedTime >= SLEEP_TIME);
    }

    @Test
    public void testGetElapsedWhileRunning()
    {
        StopWatchApp stopWatch = new StopWatchApp();

        long startTime = stopWatch.start();

        try
        {
            Thread.sleep(SLEEP_TIME);
        }
        catch (InterruptedException e)
        {
            fail(e.getMessage());
        }

        long elapsedTime = stopWatch.getElapsedTime();

        assertTrue(elapsedTime >= SLEEP_TIME);

        try
        {
            Thread.sleep(SLEEP_TIME);
        }
        catch (InterruptedException e)
        {
            fail(e.getMessage());
        }

        elapsedTime = stopWatch.getElapsedTime();

        assertTrue(elapsedTime >= (SLEEP_TIME + SLEEP_TIME));

    }

    @Test
    public void testStart()
    {

        StopWatchApp stopWatch = new StopWatchApp();

        long startTime = stopWatch.start();

        assertTrue(System.currentTimeMillis() >= startTime);

    }

    @Test
    public void testStopWithoutStart()
    {

        StopWatchApp stopWatch = new StopWatchApp();

        long stopTime = stopWatch.stop();

        assertTrue(stopTime == 0);

    }

    @Test
    public void testStopWithStart()
    {
        StopWatchApp stopWatch = new StopWatchApp();

        long startTime = stopWatch.start();

        try
        {
            Thread.sleep(SLEEP_TIME);
        }
        catch (InterruptedException e)
        {
            fail(e.getMessage());
        }

        long stopTime = stopWatch.stop();

        assertTrue(stopTime >= SLEEP_TIME);
    }

}
