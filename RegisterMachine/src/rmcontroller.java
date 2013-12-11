import javax.swing.JButton;
import java.awt.event.ActionListener;
public class rmcontroller
{
	private int NRegisters;
	private RegisterViewer rv;
	private RegisterMachine rm;
	private viewupdater vu;
	public rmcontroller(int Nreg)
	{
		NRegisters = Nreg;
		rv = new RegisterViewer(NRegisters);
		rm = new RegisterMachine(NRegisters);
		vu = new viewupdater(rm,rv);
		
		JButton cb = rv.getcopyButton();
		cb.addActionListener(new copyAction(rm,rv,vu));
		JButton sb = rv.getstepButton();
		sb.addActionListener(new stepAction(rm,rv,vu));
		JButton rb = rv.getrunButton();
		rb.addActionListener(new runAction(rm,rv,vu));
		JButton lb = rv.getloadButton();
		lb.addActionListener(new loadAction(rm,rv,vu));
		
		rm.loadProgram("examples/mult.reg");
		vu.updateview();
		vu.updateprogramview();
				
		
/*		int nLines = rm.getnLines();
		String[] program = new String[nLines];
		for(int i=0;i<nLines;i++)
		{
			program[i] = "Line " + i + ": " + rm.getInstructionString(i) + " (" + rm.getComment(i) + ")";	
		}
		rv.updateListing(program,0);*/
	}	

	public static void main(String[] args)
	{
		rmcontroller rc = new rmcontroller(10);	
	}
	

	
}