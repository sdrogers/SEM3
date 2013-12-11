//Simple program to display the contents of some registers
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.BorderFactory;
import java.awt.Color;

public class RegisterViewer
{
	private JButton copyButton;
	private JButton stepButton;
	private JButton runButton;
	private JButton loadButton;
	private int NRegisters;
	private JFrame frame;
	private JPanel panel;
	private JPanel inputpanel;
	private JPanel labelpanel;
	private JPanel centerpanel;
	private JPanel buttonpanel;
	private JPanel copypanel;
	private JPanel wrappanel;
	private JTextField[] inputs;
	private JLabel[] registers;
	private JLabel bottomtext;
	private JLabel nextInstruction;
	private JLabel message;
	private JList programlist;
	private DefaultListModel lm;
	
	public RegisterViewer(int Nreg)
	{
		NRegisters = Nreg;
		
		JFrame frame = new JFrame();
		frame.setSize(500,600);
		frame.setTitle("Tom's register machine");
		//Create the buttons
		copyButton = new JButton("Copy values into registers.");
		stepButton = new JButton("Step");
		runButton = new JButton("Run");
		loadButton = new JButton("Load");
		
		//Create the input text fields and register labels
		inputs = new JTextField[NRegisters];
		registers = new JLabel[NRegisters];
		for(int i=0;i<Nreg;i++)
		{
			inputs[i] = new JTextField("0");
			inputs[i].setBorder(BorderFactory.createTitledBorder(""+i));
			inputs[i].setHorizontalAlignment(JLabel.CENTER);				
			registers[i] = new JLabel("0");
			registers[i].setBorder(BorderFactory.createTitledBorder(""+i));
			registers[i].setHorizontalAlignment(JLabel.CENTER);
		}
		
		//Create the outer panel
		wrappanel = new JPanel();
		wrappanel.setLayout(new GridLayout(0,1));
		
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		
		panel.add(new JLabel("Enter initial register contents below."));
		
		//Create the input panel
		inputpanel = new JPanel();
		inputpanel.setLayout(new GridLayout(1,NRegisters));
		
		//Add things to the input panel		
		for(int i=0;i<NRegisters;i++)
		{
			inputpanel.add(inputs[i]);	
		}

		
		panel.add(inputpanel);
		copypanel = new JPanel();
		copypanel.setLayout(new GridLayout(1,3));
		copypanel.add(new JLabel());
		copypanel.add(copyButton);		
		copypanel.add(new JLabel());
		panel.add(copypanel);
		//Create the label panel: this is for the register contents.
		labelpanel = new JPanel();
		labelpanel.setLayout(new GridLayout(1,NRegisters));
		
		panel.add(new JLabel("Registers:"));
		for(int i=0;i<NRegisters;i++)
		{
			labelpanel.add(registers[i]);	
		}
		
		panel.add(labelpanel);
		
		centerpanel = new JPanel();
		centerpanel.setLayout(new GridLayout(0,1));
		JLabel temp = new JLabel("<html><font color='red'>Next instruction:</font></html>");
		temp.setHorizontalAlignment(JLabel.CENTER);
		centerpanel.add(temp);
		nextInstruction = new JLabel("");
		nextInstruction.setHorizontalAlignment(JLabel.CENTER);
		centerpanel.add(nextInstruction);
		panel.add(centerpanel);
		JPanel centerpanel2 = new JPanel();
		centerpanel2.setLayout(new GridLayout(0,1));
		JLabel temp2 = new JLabel("<html><font color='red'>Message:</font></html>");
		temp2.setHorizontalAlignment(JLabel.CENTER);
		centerpanel2.add(temp2);
		message = new JLabel("");
		message.setHorizontalAlignment(JLabel.CENTER);
		centerpanel2.add(message);
		panel.add(centerpanel2);

		buttonpanel = new JPanel();
		buttonpanel.setLayout(new GridLayout(1,5));
		
		buttonpanel.add(stepButton);
		buttonpanel.add(new JLabel());
		buttonpanel.add(runButton);
		buttonpanel.add(new JLabel());
		buttonpanel.add(loadButton);
		panel.add(buttonpanel);
		
		
		//Add the complete program listing
		lm = new DefaultListModel();
		programlist = new JList(lm);
		programlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		programlist.setLayoutOrientation(JList.VERTICAL);
		programlist.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(programlist);
		wrappanel.add(panel);
		wrappanel.add(listScroller);
		
		frame.add(wrappanel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
	}
	private void setnextInstruction(String in)
	{
		nextInstruction.setText(in);	
	}
	private void setRegister(int reg,int val)
	{
		registers[reg].setText(""+val);	
	}
	public int getFieldValue(int fieldno)
	{
		return Integer.parseInt(inputs[fieldno].getText());
	}
	public JButton getcopyButton()
	{
		return copyButton;	
	}
	public JButton getstepButton()
	{
		return stepButton;	
	}
	public JButton getrunButton()
	{
		return runButton;
	}
	public JButton getloadButton()
	{
		return loadButton;
	}
	private void setMessage(String newMessage)
	{
		message.setText(newMessage);	
	}
	public void updateListing(String[] listing,int selected)
	{
		clearList();
		for(int i=0;i<listing.length;i++)
		{
			lm.addElement(listing[i]);	
		}	
		programlist.setSelectedIndex(selected);
	}
	public void setSelectedProgramLine(int selected)
	{
		programlist.setSelectedIndex(selected);
	}
	public void updateviewdata(int[] newregisters,String newtext,String newMessage)
	{
		for(int i=0;i<NRegisters;i++)
		{
			setRegister(i,newregisters[i]);	
		}	
		setnextInstruction(newtext);
		setMessage(newMessage);
	}
	public void clearList()
	{ 
		lm.removeAllElements();
	}
	/*
	public static void main(String[] args)
	{
		
		
		final int NRegisters = 10;		
		
		
		final RegMachine rm = new RegMachine(NRegisters);
		
		for(int i=0;i<NRegisters;i++)
		{
			rm.setRegister(i,0);	
		}
		
		JButton copyButton = new JButton("Copy");
		JButton stepButton = new JButton("Step");
		JButton runButton = new JButton("Run");
		JFrame frame = new JFrame();
		frame.setSize(500,100);
		
		final JTextField[] inputs = new JTextField[NRegisters];
		
		JLabel title = new JLabel("Input:");
		JLabel moretext = new JLabel("Register Contents:");
		final JLabel currentInstruction = new JLabel();
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		JPanel inputPanel = new JPanel();
		JPanel registerPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(1,NRegisters+2));
		registerPanel.setLayout(new GridLayout(1,NRegisters+1));
//		inpitPanel.add(title);
		inputPanel.add(title);
		for(int i=0;i<NRegisters;i++)
		{
			inputs[i] = new JTextField("      ");
			inputPanel.add(inputs[i],BorderLayout.NORTH);	
		}
		panel.add(inputPanel,BorderLayout.NORTH);
		panel.add(registerPanel,BorderLayout.SOUTH);
		final JLabel[] regcontents = new JLabel[NRegisters];

		
		
		class copyButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				for(int i=0;i<NRegisters;i++)
				{
					rm.setRegister(i,Integer.parseInt(inputs[i].getText()));	
				}
				rm.reset();
				rm.display(regcontents,currentInstruction);
				
			}	
		}
		
		ActionListener listener = new copyButtonListener();
		copyButton.addActionListener(listener);

		inputPanel.add(copyButton);
		registerPanel.add(moretext);
				for(int i=0;i<NRegisters;i++)
		{
			regcontents[i] = new JLabel("     0");
			registerPanel.add(regcontents[i]);	
		}
		
		class stepListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				rm.step(regcontents,currentInstruction);	
			}	
		}


		class runListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				rm.run(regcontents,currentInstruction);	
			}	
		}

		
		ActionListener steplistener = new stepListener();
		stepButton.addActionListener(steplistener);
		
		ActionListener runlistener = new runListener();
		runButton.addActionListener(runlistener);

		
		panel.add(stepButton,BorderLayout.WEST);
		panel.add(runButton,BorderLayout.EAST);
		panel.add(currentInstruction,BorderLayout.CENTER);
		frame.add(panel);
		
			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
		
		
		for(int i=0;i<NRegisters;i++)
		{
			inputs[i].setText(""+rm.getRegister(i));	
		}

	}*/	
}