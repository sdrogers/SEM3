import java.awt.event.*;
import java.util.Date;

public class simpletickListener implements ActionListener 
{
	private GameOfLife gOL;
	private SimpleGameViewer gv;
	private DrawPanel dp;
	public simpletickListener(GameOfLife g,SimpleGameViewer v)
	{
		gOL = g;
		gv = v;	
		dp = gv.getPanel();
	}
	public void actionPerformed(ActionEvent event)
	{
		doticks();
	}
	public void doticks()
	{
		gOL.tick();
		gv.setStatus();
	}
	private void pause(Double seconds)
	{
    	Date start = new Date();
    	Date end = new Date();
    	while(end.getTime() - start.getTime() < seconds * 1000.0)
    	{
        	end = new Date();
    	}
	}
}