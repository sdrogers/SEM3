import java.awt.event.*;
import java.util.Date;

public class tickListener implements ActionListener
{
	private GameOfLife gOL;
	private GameViewer gv;
	public tickListener(GameOfLife g,GameViewer v)
	{
		gOL = g;
		gv = v;	
	}
	public void actionPerformed(ActionEvent event)
	{
		gOL.tick();
		gv.setStatus(gOL.getWorld());	
	}
	private void pause(int seconds)
	{
    	Date start = new Date();
    	Date end = new Date();
    	while(end.getTime() - start.getTime() < seconds * 1000.0)
    	{
        	end = new Date();
    	}
	}
}