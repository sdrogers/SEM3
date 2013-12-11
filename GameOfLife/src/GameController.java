import java.util.Date;
import java.awt.event.*;
import javax.swing.*;
public class GameController
{
	private GameOfLife gOL;
	private GameViewer gv;
	
	public GameController()
	{
		gOL = new GameOfLife(20,20);
		gv = new GameViewer(20,20);
		
		JButton randButton = gv.getRandButton();
		randButton.addActionListener(new randomListener(gOL,gv));
		
		JButton tickButton = gv.getTickButton();
		tickButton.addActionListener(new tickListener(gOL,gv));	
	
	}
	
	public static void main(String[] args)
	{
		GameController gc = new GameController();	
	}
}