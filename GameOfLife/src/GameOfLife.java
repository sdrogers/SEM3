import java.util.Random;
import java.io.*;

public class GameOfLife
{
	private int[][] world;
	private int nRows;
	private int nCols;
	
	public GameOfLife(int r,int c)
	{
		nRows = r;
		nCols = c;
		world = new int[nRows][nCols];
	}
	public void randomise()
	{
		randomise(0.2);	
	}
	public void zero()
	{
		for(int i=0;i<nRows;i++)
		{
			for(int j=0;j<nCols;j++)
			{
				world[i][j] = 0;	
			}	
		}	
	}
	public void randomise(double p)
	{
		Random r = new Random();
		for(int i = 0;i<nRows;i++)
		{
			for(int j = 0;j<nCols;j++)
			{
				double ra = r.nextDouble();
				if(ra<p)
				{
					world[i][j] = 1;	
				}else
				{
					world[i][j] = 0;	
				}
			}	
		}
	}
	public void writeToConsole()
	{
		for(int i=0;i<nRows;i++)
		{
			String temp = "";
			for(int j=0;j<nCols;j++)
			{
				temp += " " + world[i][j];
			}	
			System.out.println(temp);
		}	
	}
	public void tick(int n)
	{
		for(int i=0;i<n;i++)
		{
			int a = this.tick();	
		}	
	}
	public int tick()
	{
		int[][] temp = new int[nRows][nCols];
		// Loop through the cells
		for(int i=0;i<nRows;i++)
		{
			for(int j=0;j<nCols;j++)
			{
				int n = countNeighbours(i,j);	
				int alive = world[i][j];
				if(alive==1)
				{
					if(n==2 | n==3)
					{
						temp[i][j] = 1;	
					}else
					{
						temp[i][j] = 0;	
					}
				}else
				{
					temp[i][j] = 0;
					if(n==3)
					{
						temp[i][j] = 1;
					}
				}
			}	
		}
		//Copy it back
		int changes = 0;
		for(int i=0;i<nRows;i++)
		{
			for(int j=0;j<nCols;j++)
			{
				if(world[i][j]!=temp[i][j])
				{
					changes++;
				}
				world[i][j] = temp[i][j];	
			}	
		}	
		return changes;
	}
	public int[][] getWorld()
	{
		return world;	
	}
	private int countNeighbours(int row,int col)
	{
		int count = 0;
		int x;
		int y;
		for(int i=row-1;i<=row+1;i++)
		{
			x = i;
			if(x<0)
			{
				x += nRows;
			}
			if(x>=nRows)
			{
				x -= nRows;	
			}
			for(int j=col-1;j<=col+1;j++)
			{
				y = j;
				if(y<0)
				{
					y += nCols;	
				}
				if(y>=nCols)
				{
					y -= nCols;	
				}
				if(x!=row | y!=col)
				{
					count += world[x][y];	
				}
			}
		}
		return count;
	}
	public void loadGame(String fName)
	{
		nRows = 0;
		nCols = 0;
		File file = new File(fName);
		BufferedReader reader = null;
		int rowNum = 0;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine()) != null)
			{
				nCols = line.length();
				for(int j=0;j<nCols;j++)
				{
					if(line.charAt(j)=='0')
					{
						world[rowNum][j] = 0;	
					}else
					{
						world[rowNum][j] = 1;	
					}
				}	
				rowNum++;
			}	
			nRows = rowNum;
		}catch(IOException e)
		{
			e.printStackTrace();	
		}
		finally
		{
			try 
			{
				if(reader!=null)
				{
					reader.close();
				}
			}catch (IOException e)
			{
				e.printStackTrace();	
			}	
		}

	}
	public void flipWorld(int row,int col)
	{
		world[row][col] = 1 - world[row][col];	
	}
	public void loadGameCoords(String fName)
	{
		File file = new File(fName);
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			String[] temp = null;
			for(int i=0;i<nRows;i++)
			{
				for(int j=0;j<nCols;j++)
				{
					world[i][j] = 0;	
				}	
			}
			while((line = reader.readLine()) != null)
			{
				temp = line.split(",");
				int x = Integer.parseInt(temp[0]);
				int y = Integer.parseInt(temp[1]);
				world[x][y] = 1;
			}	
		}catch(IOException e)
		{
			e.printStackTrace();	
		}
		finally
		{
			try 
			{
				if(reader!=null)
				{
					reader.close();
				}
			}catch (IOException e)
			{
				e.printStackTrace();	
			}	
		}

	}

}