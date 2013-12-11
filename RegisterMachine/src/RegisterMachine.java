import javax.swing.JLabel;
import java.io.*;
import java.util.regex.*;

	public class RegisterMachine
		{
			private int NRegisters;
			private int[] Registers;
			private int[] instructions;
			private int[] register;
			private int[] next;
			private int[] branch;
			private String[] comments;
			private int current;
			private int nsteps;
			private int MaxInstructions = 100;
			
			private int nLines = 0;
			private Double maxSteps = 1e100;
			private String message = "";
			
			public RegisterMachine(int NR)
			{
				NRegisters = NR;
				Registers = new int[NRegisters];
				
				
				instructions = new int[MaxInstructions];
				register = new int[MaxInstructions];
				next = new int[MaxInstructions];
				branch = new int[MaxInstructions];
				comments = new String[MaxInstructions];
				instructions[0] = 1;
				instructions[1] = 1;
				instructions[2] = 0;
				instructions[3] = 2;
				register[0] = 1;
				register[1] = 0;
				register[2] = 1;
				next[0] = 0;
				next[1] = 2;
				next[2] = 1;
				branch[0] = 1;
				branch[1] = 3;
				
				nsteps = 0;
				current = 0;
				nLines = 3;
				
			}	
			public int getReg()
			{
				return register[current];	
			}
			public int getReg(int no)
			{
				return register[no];	
			}
			public int getNextIns()
			{
				return next[current];	
			}
			public int getNextIns(int no)
			{
				return next[no];	
			}
			public int getBranch()
			{
				return branch[current];	
			}
			public int getBranch(int no)
			{
				return branch[no];	
			}
			public int getInstruction(int no)
			{
				return instructions[no];
			}
			public void setInstruction(int no,int val)
			{
				instructions[no] = val;	
			}			
			public void setProgramRegister(int no,int val)
			{
				register[no] = val;	
			}
			public void setComment(int no,String val)
			{
				comments[no] = val;	
			}			
			public void setnLines(int val)
			{
				nLines = val;	
			}
			public void setNext(int no,int val)
			{
				next[no] = val;	
			}
			public void setBranch(int no,int val)
			{
				branch[no] = val;
			}
			public int getNRegisters()
			{
				return NRegisters;	
			}
			public void setRegister(int n,int val)
			{
				Registers[n] = val;	
			}
			public int getRegister(int n)
			{
				return Registers[n];
			}	
			public String getMessage()
			{
				return message;	
			}		
			public int getCurrent()
			{
				return current;
			}
			public void step()
			{
				if(instructions[current]<2)
				{
					if(instructions[current]==0)
					{
						Registers[register[current]]++;
						current = next[current];	
					}else if(instructions[current]==1)
					{
						if(Registers[register[current]]>0)
						{
							Registers[register[current]]--;
							current = next[current];	
						}else
						{
							current = branch[current];	
						}
					}
					nsteps ++;
					message = "Done step " + nsteps + ".";
				}
				
				
			}
			public void reset()
			{
				current = 0;
				nsteps = 0;	
				message = "Program reset.";
			}
			public void run()
			{
				while(instructions[current]<2 & nsteps<maxSteps)
				{
					step();	
				}	
				if(nsteps==maxSteps)
				{
					message = "Reached maximum number of steps (" + maxSteps + ")";	
				}else
				{
					message = "Finished running after " + nsteps + " steps.";
				}
			}
			public int[] getAllRegisters()
			{
				return Registers;	
			}
			public int getnLines()
			{
				return nLines;
			}
			public String getInstructionString(int insno)
			{
				String temp = "";
				if(instructions[insno]==0)
				{
					temp = "Increment " + register[insno] + ", next " + next[insno];	
				}else if (instructions[insno]==1)
				{
					temp = "Decrement " + register[insno] + ", next " + next[insno] + ", branch " + branch[insno];
				}else
				{
					temp = "Stop";	
				}
				return temp;
					
			}
			public String getCurrentInstructionString()
			{
				return getInstructionString(current);
			}
			public String getComment(int pos)
			{
				return comments[pos];	
			}
			public void loadProgram(String fileName)
			{
				nLines = 0;
				File file = new File(fileName);
				BufferedReader reader = null;
				int lineNo = 0;
				try
				{
					reader = new BufferedReader(new FileReader(file));
					String text = null;
					while ((text = reader.readLine()) != null)
					{	
						// Read in the other stuff….
						// Start by extracting comments
						String[] subs = text.split(":");
						if(subs.length>1)
						{
							comments[lineNo] = subs[1];
						}else
						{
							comments[lineNo] = "";	
						}
						Pattern p = Pattern.compile("[0-9]+");
						Matcher m = p.matcher(subs[0]);
						int pos = 0;
						while(m.find())
						{
							switch(pos) {
								case 0:	
									instructions[lineNo] = Integer.parseInt(m.group());
									break;
								case 1:
									register[lineNo] = Integer.parseInt(m.group());
									break;
								case 2:
									next[lineNo] = Integer.parseInt(m.group());
									break;
								case 3:
									branch[lineNo] = Integer.parseInt(m.group());
									break;
							}
							pos++;
						}		
						lineNo++;
					}
					nLines = lineNo;		
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				finally {
		            try {
		                if (reader != null) {
		                    reader.close();
		                }
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
			message = "Loaded "+ fileName;
			}
		public void saveProgram(String fileName)
		{
			File file = new File(fileName);
			FileWriter writer = null;
			
			try{
				writer = new FileWriter(file);
				for(int i=0;i<nLines;i++)
				{
					String line = "" + instructions[i];
					if(instructions[i]<2)
					{
						line +=  "\t" + register[i] + "\t" + next[i];
						if(instructions[i]==1)
						{
							line += "\t" + branch[i];
						}
					}
					if(comments[i].length()>0)
					{
						line += " : " + comments[i];
					}
					line += "\n";
					writer.write(line);
				}	
				
			}catch (IOException e)
				{
					e.printStackTrace();
				}
				finally {
		            try {
		                if (writer != null) {
		                    writer.close();
		                }
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
	
			
		}
		}
