package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;

import datenhaltung.Installation;
import fachkonzept.PasswortVerwaltung60Main;
import fachkonzept.Strings;

public class SettingGUI extends JFrame
{
	private static final long serialVersionUID = 70849350235944227L;
	
	static Strings stringList = PasswortVerwaltung60Main.stringList;
	static Installation I = PasswortVerwaltung60Main.I;
	
	public SettingGUI()
	{
		super(stringList.getString("Settings"));
		
		setSize(700,650);
		
		setResizable(true);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int)((d.width - getSize().width) /2);
		int y = (int)((d.height - getSize().height) /2);
		
		setLocation(x,y);
				
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setLayout(new GridLayout(3,2));
		
		JButton applyButton = new JButton(stringList.getString("Apply"));
		JButton cancelButton = new JButton(stringList.getString("Cancel"));
		
		applyButton.addActionListener(new PerformSaveChange());
		cancelButton.addActionListener(new PerformCancel());
		
		add(applyButton);
		add(cancelButton);
		
		setVisible(true);
	}
	
	@SuppressWarnings("serial")
	public class PerformSaveChange extends AbstractAction
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			
		}
	}
	
	@SuppressWarnings("serial")
	public class PerformCancel extends AbstractAction
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			setVisible(false);
			dispose();
		}
	}
}
