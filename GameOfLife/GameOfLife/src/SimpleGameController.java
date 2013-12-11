import java.util.Date;
import java.awt.event.*;
import javax.swing.*;
public class SimpleGameController
{
	private GameOfLife gOL;
	private SimpleGameViewer gv;
	private int MAXITS;
	public SimpleGameController(String name,int r,int c,int m,Double p)
	{
		MAXITS = m;
		gOL = new GameOfLife(r,c);
		gv = new SimpleGameViewer(r,c,gOL.getWorld());
		
		
		//gOL.randomise();
		if(name.equals("random"))
		{
			gOL.randomise();
		}else
		{
			gOL.loadGameCoords(name);
		}
		gv.setStatus();
		int changes = 1;
		int nIts = 0;
		while(changes>0 & nIts<MAXITS)
		{
			pause(p);
			changes = gOL.tick();
			//gv.setStatus(gOL.getWorld());	
			gv.setStatus();
			nIts++;
		}
			
	}
	private void pause(double seconds)
	{
    	Date start = new Date();
    	Date end = new Date();
    	while(end.getTime() - start.getTime() < seconds * 1000.0)
    	{
        	end = new Date();
    	}
	}
	public static void main(String[] args)
	{
		SimpleGameController gc = new SimpleGameController(args[0],Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]),Double.parseDouble(args[4]));	
	}
}