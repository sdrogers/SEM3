import javax.swing.*;
import java.awt.*;
public class SimpleGameViewer
{
	private int nRows;
	private int nCols;
	private JFrame mainFrame;	
	private JPanel mainPanel;
	private JTextArea textArea;
	private char deadchar = ' ';
	private char alivechar = '0';
	private DrawPanel grid;
	private int[][] world;
	public SimpleGameViewer(int r,int c,int[][] worldin)
	{
		nRows = r;
		nCols = c;
		world = worldin;
		mainFrame = new JFrame();
		mainFrame.setSize(1000,1000);
		mainFrame.setTitle("Game of Life");
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0,1));
				
				
		grid = new DrawPanel(r,c,world);
		/*textArea = new JTextArea(nRows,nCols);
		textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
		mainPanel.add(textArea);*/
		mainPanel.add(grid);
		
		mainFrame.add(mainPanel);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);		
	}
	public JFrame getFrame()
	{
		return mainFrame;	
	}
	public DrawPanel getPanel()
	{
		return grid;	
	}
	public int getClickRow(int y)
	{
		return grid.getClickRow(y);
	}
	public int getClickCol(int x)
	{
		return grid.getClickCol(x);
	}

	public void setStatus()
	{
		/*String temp = "";
		for(int i=0;i<nRows;i++)
		{
			for(int j=0;j<nCols;j++)
			{
				if(world[i][j]==0)
				{
					temp+= deadchar;	
				}
				else
				{
					temp+= alivechar;	
				}
			}	
			temp+="\n";
		}	
		textArea.setText(temp);*/
		grid.setStatus();
		grid.repaint();
	}
}