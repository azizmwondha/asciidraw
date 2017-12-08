package asciidraw.loops;

import java.text.DecimalFormat;

public abstract class ThreadLoop extends Thread
{

	// desired fps
	private int maxFps = 50;
	// maximum number of frames to be skipped
	private final static int MAX_FRAME_SKIPS = 5;
	// the frame period
	private int framePeriod;

	// Stuff for stats */
	private String threadName;
	private final static DecimalFormat df = new DecimalFormat("0.##");  // 2 dp
	// we'll be reading the stats every second
	private final int STAT_INTERVAL = 1000; //ms
	// the average will be calculated by storing
	// the last n FPSs
	private final int FPS_HISTORY_NR = 10;
	// last time the status was stored
	private long lastStatusStore = 0;
	// the status time counter
	private long statusIntervalTimer = 0l;
	// number of frames skipped since the game started
	private long totalFramesSkipped = 0l;
	// number of frames skipped in a store cycle (1 sec)
	private long framesSkippedPerStatCycle = 0l;

	// number of rendered frames in an interval
	private int frameCountPerStatCycle = 0;
	private long totalFrameCount = 0l;
	private int frameCountPerDecorCycle = 0;
	// the last FPS values
	private double fpsStore[];
	// the number of times the stat has been read
	private long statsCount = 0;
	// the average FPS since the game started
	private double averageFps = 0.0;

	public ThreadLoop(String name, int fps)
	{
		threadName = name;
		maxFps = fps;
	}

	@Override
	public void run()
	{
		// Moves the current Thread into the background
//		android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

		// initialise timing elements for stat gathering
		initTimingElements();

		long beginTime;        // the time when the cycle begun
		long timeDiff;        // the time it took for the cycle to execute
		int sleepTime = 0;        // ms to sleep (<0 if we're behind)
		int framesSkipped;    // number of frames being skipped


		// CLog.debug(threadName, "ThreadLoop start");

		try
		{
			while (!interrupted())
			{
				beginTime = System.currentTimeMillis();
				framesSkipped = 0;    // resetting the frames skipped

				// update game state
				update();

				// calculate how long did the cycle take
				timeDiff = System.currentTimeMillis() - beginTime;
				// calculate sleep time
				sleepTime = (int) (framePeriod - timeDiff);

				if (sleepTime > 0)
				{
					// if sleepTime > 0 we're READY
					// send the thread to sleep for a short period
					// very useful for battery saving
					Thread.sleep(sleepTime);
				}

				// for statistics
				framesSkippedPerStatCycle += framesSkipped;
				frameCountPerDecorCycle++;
				// calling the routine to store the gathered statistics
				storeStats();
			}
		}
		catch (InterruptedException e)
		{
			// CLog.debug(threadName, "ThreadLoop interrupt");
		}
		catch (Throwable t)
		{
			// CLog.debug(threadName, "update() interrupt " + t.getMessage());
		}
		finally
		{
			// Clears the Thread's interrupt flag
			// CLog.debug(threadName, "ThreadLoop end");
		}
	}

	protected abstract void update();

	/**
	 * The statistics - it is called every cycle, it checks if time since last
	 * store is greater than the statistics gathering period (1 sec) and if so
	 * it calculates the FPS for the last period and stores it.
	 * <p/>
	 * It tracks the number of frames per period. The number of frames since the
	 * start of the period are summed up and the calculation takes part only if
	 * the next period and the frame count is initSim to 0.
	 */
	private void storeStats()
	{
		frameCountPerStatCycle++;
		totalFrameCount++;

		// check the actual time
		statusIntervalTimer += (System.currentTimeMillis() - statusIntervalTimer);

		if (statusIntervalTimer >= lastStatusStore + STAT_INTERVAL)
		{
			// calculate the actual frames pers status check interval
			double actualFps = (double) (frameCountPerStatCycle / (STAT_INTERVAL / 1000));

			//stores the latest fps in the array
			fpsStore[(int) statsCount % FPS_HISTORY_NR] = actualFps;

			// increase the number of times statistics was calculated
			statsCount++;

			double totalFps = 0.0;
			// sum up the stored fps values
			for (int i = 0; i < FPS_HISTORY_NR; i++)
			{
				totalFps += fpsStore[i];
			}

			// obtain the average
			if (statsCount < FPS_HISTORY_NR)
			{
				// in case of the first 10 triggers
				averageFps = totalFps / statsCount;
			}
			else
			{
				averageFps = totalFps / FPS_HISTORY_NR;
			}
			// saving the number of total frames skipped
			totalFramesSkipped += framesSkippedPerStatCycle;

			// resetting the counters after a status record (1 sec)
			framesSkippedPerStatCycle = 0;
			statusIntervalTimer = 0;
			frameCountPerStatCycle = 0;

			statusIntervalTimer = System.currentTimeMillis();
			lastStatusStore = statusIntervalTimer;
			// CLog.debug(threadName, "ThreadLoop stats [AverageFPS=" + df.format(averageFps) + "][FramePeriod=" + framePeriod + "]");
		}
	}

	private void initTimingElements()
	{
		framePeriod = (1000 / maxFps);

		// initialise timing elements
		fpsStore = new double[FPS_HISTORY_NR];
		for (int i = 0; i < FPS_HISTORY_NR; i++)
		{
			fpsStore[i] = 0.0;
		}
	}
}
