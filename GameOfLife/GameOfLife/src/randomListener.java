import java.awt.event.*;


public class randomListener implements ActionListener
{
	private GameOfLife gOL;
	private GameViewer gv;
	public randomListener(GameOfLife g,GameViewer v)
	{
		gOL = g;
		gv = v;	
	}
	public void actionPerformed(ActionEvent event)
	{
		gOL.randomise();
		gv.setStatus(gOL.getWorld());
	}
}