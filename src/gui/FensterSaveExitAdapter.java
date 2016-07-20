package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JOptionPane;

import fachkonzept.PasswortVerwaltung60Main;
import fachkonzept.Strings;
import fachkonzept.Verwaltung;

public class FensterSaveExitAdapter extends WindowAdapter
{
	private Verwaltung vw;
	private Hauptfenster hf;
	private boolean change;
	private boolean check;
	private File path;
	private String passString;
	static Strings stringList = PasswortVerwaltung60Main.stringList;
	public FensterSaveExitAdapter(Verwaltung vw, Hauptfenster hf, File path, String passString) 
	{
		this.vw = vw;
		this.hf = hf;
		check = true;
		this.passString = passString;
		this.path = path;
	}
	
	public FensterSaveExitAdapter(Verwaltung vw) 
	{
		this.vw = vw;
		check = false;
	}

	public void windowClosing(WindowEvent event)
	{
		if(check == true)
		{
			change = hf.getChange();
		}
		
		if(change == true)
		{
			Object[] options = {"Ja", "Nein", "Abbrechen"};
			int n = JOptionPane.showOptionDialog(null, stringList.getString("Do you want to save changes?"),stringList.getString("My Passwordmanager"),
			JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			
			if(n == JOptionPane.YES_OPTION)
			{
				vw.ser(path, passString, "SHA-512");
				event.getWindow().setVisible(false);
				event.getWindow().dispose();
				System.exit(0);
			}
			
			if(n == JOptionPane.NO_OPTION || n == JOptionPane.CLOSED_OPTION)
			{
				System.exit(0);
			}
			
			if(n == JOptionPane.CANCEL_OPTION)
			{
				hf = new Hauptfenster(vw, path, passString, path.getName());
				hf.setChange(true);
				
			}
		}else
		{
			System.exit(0);
		}
	}
}
