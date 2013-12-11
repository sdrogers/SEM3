import javax.swing.*;
import java.awt.*;
public class GameViewer
{
	private int nRows;
	private int nCols;
	private JFrame mainFrame;	
	private JPanel mainPanel;
	private JButton randButton;
	private JButton tickButton;
	private JTextArea textArea;
	private char deadchar = ' ';
	private char alivechar = '0';
	private DrawPanel grid;
	public GameViewer(int r,int c)
	{
		nRows = r;
		nCols = c;
		mainFrame = new JFrame();
		mainFrame.setSize(1000,1000);
		mainFrame.setTitle("Game of Life");
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0,1));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,0));
		
		randButton = new JButton("Randomise");
		tickButton = new JButton("Tick");
		buttonPanel.add(randButton);
		buttonPanel.add(tickButton);
		
		mainPanel.add(buttonPanel);
		
		textArea = new JTextArea(nRows,nCols);
		textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
		mainPanel.add(textArea);

		grid = new DrawPanel(r,c);
		
		mainPanel.add(grid);
		mainFrame.add(mainPanel);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);		
	}
	public JButton getRandButton()
	{
		return randButton;	
	}
	public JButton getTickButton()
	{
		return tickButton;	
	}
	public void setStatus(int[][] world)
	{
		String temp = "";
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
		textArea.setText(temp);
		grid.setStatus(world);
		grid.repaint();
	}
}