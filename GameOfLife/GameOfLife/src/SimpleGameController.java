import java.util.Date;
import javax.swing.*;
public class SimpleGameController
{
	private GameOfLife gOL;
	private SimpleGameViewer gv;
	private int MAXITS;
	private Double pauseTime;
	public SimpleGameController(String name,int r,int c,int m,Double p)
	{
		MAXITS = m;
		gOL = new GameOfLife(r,c);
		pauseTime = p;
		gv = new SimpleGameViewer(r,c,gOL.getWorld());
		gOL.loadGameCoords(name);
		gv.setStatus();
		run();
			
	}
	public SimpleGameController(int r,int c,int m, Double p,Double prob) {
		MAXITS = m;
		gOL = new GameOfLife(r,c);
		pauseTime = p;
		gv = new SimpleGameViewer(r,c,gOL.getWorld());
		gOL.randomise(prob);
		gv.setStatus();
		run();
	}
	private void run() {
		int changes = 1;
		int nIts = 0;
		while(changes>0 & nIts<MAXITS)
		{
			pause(pauseTime);
			changes = gOL.tick();
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
		try { 
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch(Exception e) { 
			System.out.println("Error setting Java LAF: " + e);
		}
		Object[] options = {"Random","Game File"};
		int n = JOptionPane.showOptionDialog(null,"Random initialisation, or load file?","",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		int nR = Integer.parseInt((String)JOptionPane.showInputDialog(
                null,
                "Enter number of rows:",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE));
		int nC = Integer.parseInt((String)JOptionPane.showInputDialog(
                null,
                "Enter number of columns:",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE));
		int m = Integer.parseInt((String)JOptionPane.showInputDialog(
                null,
                "Enter maximum number of iterations:",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE));
		Double p = Double.parseDouble((String)JOptionPane.showInputDialog(
                null,
                "Enter pause time (seconds):",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE));
		if(n==0) {
			Double prob = Double.parseDouble((String)JOptionPane.showInputDialog(
	                null,
	                "Enter probability of initial occupancy:",
	                "Customized Dialog",
	                JOptionPane.PLAIN_MESSAGE));
			SimpleGameController gc = new SimpleGameController(nR,nC,m,p,prob);
		} else {
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				SimpleGameController gc = new SimpleGameController(chooser.getSelectedFile().getAbsolutePath(),nR,nC,m,p);				
			} else {
				System.exit(0);
			}
			
		}
	}
}