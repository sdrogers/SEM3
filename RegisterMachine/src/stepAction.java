import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class stepAction implements ActionListener
{
	private RegisterMachine rm;
	private RegisterViewer rv;
	private viewupdater vu;
	private int NRegisters;
	public stepAction(RegisterMachine rmin,RegisterViewer rvin,viewupdater vuin)
	{
		vu = vuin;
		rm = rmin;	
		rv = rvin;
		NRegisters = rm.getNRegisters();
	}
	public void actionPerformed(ActionEvent event)
	{
		rm.step();
		vu.updateview();
	}	
}