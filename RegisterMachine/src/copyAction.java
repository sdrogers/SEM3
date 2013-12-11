import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class copyAction implements ActionListener
{
	private RegisterMachine rm;
	private RegisterViewer rv;
	private viewupdater vu;
	private int NRegisters;
	public copyAction(RegisterMachine rmin,RegisterViewer rvin,viewupdater vuin)
	{
		vu = vuin;
		rm = rmin;	
		rv = rvin;
		NRegisters = rm.getNRegisters();
	}
	public void actionPerformed(ActionEvent event)
	{
		for(int i=0;i<NRegisters;i++)
		{
			rm.setRegister(i,rv.getFieldValue(i));	
		}
		rm.reset();
		vu.updateview();
	}	
}