package asciidraw.loops;

public class SimLoop extends ThreadLoop
{
	public SimLoop(){
		super("SimLoop", 10000);
	}

	protected void update()
	{
//		if (null != TheOC.workspace())
		{
			// TheOC.workspace().analyse();
		}
	}
}
