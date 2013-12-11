import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.JLabel;

public class ProgramEditor
{
	private RegisterMachine rm;
	private JFrame editorFrame;
	private JTable programTable;
	private JPanel mainPanel;
	private JScrollPane scrollpane;
	private JButton saveButton;
	private DefaultTableModel tableModel;
	private JButton addRow;
	private JButton removeRow;
	private JButton insertRow;
	private JButton newFile;
	private JButton loadFile;
	public ProgramEditor()
	{
		class saveButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				//Open a dialog
				JFileChooser fc = new JFileChooser();
				int returnval = fc.showSaveDialog(null);
				if(returnval == JFileChooser.APPROVE_OPTION)
				{
					String fullname = ""+fc.getSelectedFile();
					rm.saveProgram(fullname);
				}
			}	
		}
		class addButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				int nRow = tableModel.getRowCount();
				tableModel.addRow(new Object[]{""+nRow,"0","0","0","0","No Comment"});	
			}	
		}
		class removeButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				int row = programTable.getSelectedRow();
				if(row>-1)
				{
					tableModel.removeRow(row);
					// Change everything that jumped or branched beyond this row to jump/branch to one row less!
					int nRow = tableModel.getRowCount();
					for(int i=0;i<nRow;i++)
					{
						int next = Integer.parseInt((String)tableModel.getValueAt(i,3));
						int branch = Integer.parseInt((String)tableModel.getValueAt(i,4));
						if(next>row)
						{
							next--;
							tableModel.setValueAt(""+next,i,3);	
						}	
						if(branch>row)
						{
							branch--;
							tableModel.setValueAt(""+branch,i,4);
						}
						
					}
				}	
			}	
		}
		class insertButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				int row = programTable.getSelectedRow();
				if(row>-1)
				{
					tableModel.insertRow(row,new Object[]{""+row,"0","0","0","0","No Comment"});	
				}	
				updaterownumbers();
				//Find anything reference row or higher and increment
				int nLines = tableModel.getRowCount();
				for(int i=0;i<nLines;i++)
				{
					int next = Integer.parseInt((String)tableModel.getValueAt(i,3));
					int branch = Integer.parseInt((String)tableModel.getValueAt(i,4));
					if(next>=row)
					{
						next++;
						tableModel.setValueAt(""+next,i,3);							
					}	
					if(branch>=row)
					{
						branch++;
						tableModel.setValueAt(""+branch,i,4);
					}
				}
			}	
		}
		class newButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				tableModel.setRowCount(0);
				tableModel.addRow(new Object[]{"0","0","0","0","0","No Comment"});
			}	
		}
		class loadButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				JFileChooser fc = new JFileChooser();
				int returnval = fc.showOpenDialog(null);
				
				if(returnval == JFileChooser.APPROVE_OPTION)
				{
					String fullname = ""+fc.getSelectedFile();
					rm.loadProgram(fullname);
					loadProgram();
				}
				
			}
		}
		class TableListener implements TableModelListener
		{
			public void tableChanged(TableModelEvent event)
			{
				// Copy the new table contents back into the RegisterMachine
				int nRow = tableModel.getRowCount();
				for(int i=0;i<nRow;i++)
				{
					int instruction = Integer.parseInt((String)tableModel.getValueAt(i,1));
					rm.setInstruction(i,instruction);
					if(instruction<2)
					{
						int reg = Integer.parseInt((String)tableModel.getValueAt(i,2));
						rm.setProgramRegister(i,reg);
						int next = Integer.parseInt((String)tableModel.getValueAt(i,3));
						rm.setNext(i,next);
						if(instruction==1)
						{
							int branch = Integer.parseInt((String)tableModel.getValueAt(i,4));
							rm.setBranch(i,branch);	
						}	
					}
					String comment = (String)tableModel.getValueAt(i,5);
					rm.setComment(i,comment);
				}
				rm.setnLines(nRow);
			}
		}
		
		
		rm = new RegisterMachine(10);
		
		
		editorFrame = new JFrame();
		editorFrame.setSize(1000,1000);
		editorFrame.setTitle("Program Editor");
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0,1));
		

		ActionListener saveListener = new saveButtonListener();
		ActionListener addListener = new addButtonListener();
		saveButton = new JButton("Save");
		saveButton.addActionListener(saveListener);
		addRow = new JButton("Add Row");
		addRow.addActionListener(addListener);
		removeRow = new JButton("Remove Selected Row");
		removeRow.addActionListener(new removeButtonListener());
		insertRow = new JButton("Insert Row Above Selected Row");
		insertRow.addActionListener(new insertButtonListener());
		newFile = new JButton("New File");
		newFile.addActionListener(new newButtonListener());
		loadFile = new JButton("Load File");
		loadFile.addActionListener(new loadButtonListener());
		
		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(new GridLayout(1,0));

		buttonpanel.add(loadFile);
		buttonpanel.add(saveButton);
		buttonpanel.add(newFile);
		mainPanel.add(buttonpanel);
		
		
		editorFrame.add(mainPanel);
		
		String[] colnames = {"LineNo","Instruction","Register","Next","Branch","Comment"};
		tableModel = new DefaultTableModel(null,colnames){
			@Override
			public boolean isCellEditable(int row,int column)
			{
				return column>0;	
			}	
		};
		
		programTable = new JTable(tableModel);
		TableModelListener tl = new TableListener();
		tableModel.addTableModelListener(tl);
		scrollpane = new JScrollPane(programTable);
		mainPanel.add(scrollpane);
		
		
		JPanel buttonpanel2 = new JPanel();
		buttonpanel2.setLayout(new GridLayout(1,0));
		buttonpanel2.add(addRow);
		buttonpanel2.add(removeRow);
		buttonpanel2.add(insertRow);

		mainPanel.add(buttonpanel2);
		
		editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editorFrame.setVisible(true);	
	}
	private void updaterownumbers()
	{
		int nRow = tableModel.getRowCount();
		for(int i=0;i<nRow;i++)
		{
			tableModel.setValueAt(""+i,i,0);
		}	
	}
	private void loadProgram()
	{
		//Clears the table and loads a new program
		tableModel.setRowCount(0);
		int nLines = rm.getnLines();
		for(int i=0;i<nLines;i++)
		{
			Object[] temp = new Object[6];
			temp[0] = ""+i;
			temp[1] = Integer.toString(rm.getInstruction(i));
			if(rm.getInstruction(i)==2)
			{
				temp[2] = "0";
				temp[3] = "0";
				temp[4] = "0";	
			}else
			{
				temp[2] = Integer.toString(rm.getReg(i));
				temp[3] = Integer.toString(rm.getNextIns(i));
				if(rm.getInstruction(i)==1)
				{
					temp[4] = Integer.toString(rm.getBranch(i));	
				}else
				{
					temp[4] = "0";	
				}
			}
			temp[5] = rm.getComment(i).trim();
			tableModel.addRow(temp);
		}	
	}
	
	public static void main(String[] args)
	{
		ProgramEditor pe = new ProgramEditor();
	}
}