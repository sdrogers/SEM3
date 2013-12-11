import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class runAction implements ActionListener
{
	private RegisterMachine rm;
	private RegisterViewer rv;
	private viewupdater vu;
	private int NRegisters;
	public runAction(RegisterMachine rmin,RegisterViewer rvin,viewupdater vuin)
	{
		vu = vuin;
		rm = rmin;	
		rv = rvin;
		NRegisters = rm.getNRegisters();
	}
	public void actionPerformed(ActionEvent event)
	{
		rm.run();
		vu.updateview();
	}	
}