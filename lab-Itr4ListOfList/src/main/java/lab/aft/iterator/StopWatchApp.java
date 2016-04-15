package lab.aft.iterator;

/**
 * @author amahmood
 *
 */
public class StopWatchApp
{

    public static void main(String[] args)
    {
    }

    private long startTime = 0;

    private long stopTime = 0;

    private boolean isRunning = false;

    public long getElapsedTime()
    {
        if (startTime == 0)
            return 0;

        if (isRunning)
            return System.currentTimeMillis() - startTime;
        else
            return stopTime - startTime;
    }

    public long start()
    {
        startTime = System.currentTimeMillis();
        isRunning = true;
        return startTime;
    }

    public long stop()
    {
        if (startTime == 0)
            return 0;

        isRunning = false;
        stopTime = System.currentTimeMillis();

        return stopTime - startTime;
    }

}
