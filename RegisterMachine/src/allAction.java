import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class allAction implements ActionListener
{
	private RegisterMachine rm;
	private RegisterViewer rv;
	private int NRegisters;
	public allAction(RegisterMachine rmin,RegisterViewer rvin)
	{
		rm = rmin;	
		rv = rvin;
		NRegisters = rm.getNRegisters();
	}
	public void actionPerformed(ActionEvent event)
	{
		System.out.println(event.getSource());
		/*if(event.getSource() == copyButton)
		{
			for(int i=0;i<NRegisters;i++)
			{
				rm.setRegister(i,rv.getFieldValue(i));	
			}
			rm.reset();
		}else if(event.getSource() == stepButton)
		{
			rm.step();	
		}else if(event.getSource() == runButton)
		{
			rm.run();	
		}*/
		updateview();
	}	
	private void updateview()
	{
		rv.updateviewdata(rm.getAllRegisters(),rm.getCurrentInstructionString(),rm.getMessage());
		rv.setSelectedProgramLine(rm.getCurrent());
	}
}