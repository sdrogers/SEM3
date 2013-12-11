import javax.swing.*;
import java.awt.*;
public class DrawPanel extends JPanel
{
	private int nRows;
	private int nCols;
	private int[][] myWorld;
	public DrawPanel(int r,int c,int[][] worldin)
	{
		nRows = r;
		nCols = c;	
		myWorld = worldin;
	}
	public void setStatus()
	{
	/*	for(int i=0;i<nRows;i++)
		{
			for(int j=0;j<nCols;j++)
			{
				myWorld[i][j] = world[i][j];		
			}	
		}*/
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		//Draw the grid
		
		int panelWidth = this.getWidth();
		int panelHeight = this.getHeight();
		
		/*if(panelWidth<panelHeight)
		{
			panelHeight = panelWidth;	
		}else
		{
			panelWidth = panelHeight;	
		}*/
		
		//Subtract a border
		int padding = 20;
		int boxWidth = ((panelWidth+1-padding)/nCols);
		int boxHeight = ((panelHeight+1-padding)/nRows);
		
		//Draw the rectangles
		int top = padding/2;

		for(int i=0;i<nRows;i++)
		{
			for(int j=0;j<nCols;j++)
			{
				if(myWorld[i][j]==1)
				{
					g.setColor(Color.black);				
				}else
				{
					g.setColor(Color.white);	
				}
				g.fillRect(top+j*boxWidth,top+i*boxHeight,boxWidth,boxHeight);
				g.setColor(Color.black);
				g.drawRect(top+j*boxWidth,top+i*boxHeight,boxWidth,boxHeight);
			}
		}
		

		//g.drawRect(20, 20, 100, 60);
	}
	public int getClickRow(int y)
	{
		int panelWidth = this.getWidth();
		int panelHeight = this.getHeight();
		int padding = 20;
		int boxWidth = ((panelWidth+1-padding)/nCols);
		int boxHeight = ((panelHeight+1-padding)/nRows);
		return (y - padding/2)/boxHeight;		
	}
	public int getClickCol(int x)
	{
		int panelWidth = this.getWidth();
		int panelHeight = this.getHeight();
		int padding = 20;
		int boxWidth = ((panelWidth+1-padding)/nCols);
		int boxHeight = ((panelHeight+1-padding)/nRows);
		return (x - padding/2)/boxWidth;	
	}
}