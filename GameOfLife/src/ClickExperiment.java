import java.util.Date;
import java.awt.event.*;
import javax.swing.*;
public class ClickExperiment
{
	private GameOfLife gOL;
	private SimpleGameViewer gv;
	private int MAXITS;
	private ML ml;
	private JFrame controlFrame;
	private JButton tickButton;
	public ClickExperiment(int r,int c)
	{
		MAXITS = 100;
		gOL = new GameOfLife(r,c);
		gv = new SimpleGameViewer(r,c,gOL.getWorld());
		ml = new ML(gv,gOL);
		gv.getPanel().addMouseListener(ml);
		
		controlFrame = new JFrame();
		controlFrame.setSize(100,200);
		controlFrame.setTitle("Game of Life");
		
		controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controlFrame.setVisible(true);	
		
		tickButton  = new JButton("Tick");
		controlFrame.add(tickButton);
		tickButton.addActionListener(new simpletickListener(gOL,gv));
		double p = 0.2;
		//gOL.randomise();
		gOL.zero();
		gv.setStatus();
		/*int changes = 1;
		int nIts = 0;
		while(changes>0 & nIts<MAXITS)
		{
			pause(p);
			changes = gOL.tick();
			//gv.setStatus(gOL.getWorld());	
			gv.setStatus();
			nIts++;
		}*/
			
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
		ClickExperiment gc = new ClickExperiment(20,20);
	}
}