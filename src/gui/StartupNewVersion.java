package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import datenhaltung.Installation;
import fachkonzept.PasswortVerwaltung60Main;
import fachkonzept.Strings;

public class StartupNewVersion extends JFrame
{
	private static final long serialVersionUID = 70849350235944227L;
	
	static Strings stringList = PasswortVerwaltung60Main.stringList;
	static Installation I = PasswortVerwaltung60Main.I;

	JCheckBox chckbxNewCheckBox;
	
	
	public StartupNewVersion()
	{
		super(stringList.getString("New Version"));
		
		if(showOnStartup())
		{
			if(checkVersion())
			{
				initGUI();
			}else{
				dispose();
			}	
		}else{
			dispose();
		}
	}
	
	public void initGUI()
	{
		setSize(400,200);
		
		setResizable(true);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int)((d.width - getSize().width) /2);
		int y = (int)((d.height - getSize().height) /2);
		
		setLocation(x,y);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel textLabel = new JLabel();
		textLabel.setBackground(SystemColor.menu);
		textLabel.setFont(new Font("Segoe WP SemiLight", Font.BOLD, 20));
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textLabel.setText(stringList.getString("New version available"));
		getContentPane().add(textLabel);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		chckbxNewCheckBox = new JCheckBox(stringList.getString("Don't show again"));
		panel.add(chckbxNewCheckBox, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton();
		panel.add(btnNewButton, BorderLayout.NORTH);
		KeyStroke ks1 = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0); 
		PerformClose performClose = new PerformClose();
		btnNewButton.setAction(performClose);
		btnNewButton.getActionMap().put("performClose", performClose);
				
		btnNewButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks1, "performClose"); 
		btnNewButton.setText(stringList.getString("Okay"));
		
		
		setVisible(true);
	}

	public boolean checkVersion()
	{
		try {
			URL vU = new URL("http://dkinas.de/Passwortverwaltung/Downloads/Version.txt");
			
			Scanner s = new Scanner(vU.openStream());
			
			double newestV = Double.valueOf(s.next());
			double v = stringList.getVersion();				
			s.close();
			
			if(v < newestV)
			{
				return true;
		    } else {
		    	return false;
		    }
		} catch(Exception e1)
		{
			e1.printStackTrace();
		}

		return false;
	}
	
	@SuppressWarnings("serial")
	public class PerformClose extends AbstractAction
	{
		//TODO Replace line instead of write a new line
		public void actionPerformed(ActionEvent e) 
		{
			File f = I.getConfig();
			if(chckbxNewCheckBox.isSelected())
			{				
				try{
					if(!f.exists())
					{
						f.createNewFile();
						writeConfig(false);
					}else{
						writeConfig(false);
					}
				}catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}else{
				try{
					if(!f.exists())
					{
						f.createNewFile();
						writeConfig(true);
					}else{
						writeConfig(true);
					}
				}catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
			dispose();
		}
		
	}
	
	private void writeConfig(boolean state)
	{
		File f = I.getConfig();
		try{
			f.delete();
			f.createNewFile();
			if(state)
			{
				FileWriter fw = new FileWriter(f);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("CheckForNewVersion :" + 1 + ";");
				
				bw.close();
			}else{
				FileWriter fw = new FileWriter(f);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("CheckForNewVersion :" + 0 + ";");
				
				bw.close();
			}
		}catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	private boolean showOnStartup()
	{
		File f = I.getConfig();
		
		String config;
		
		try{
			if(!f.exists())
			{
				f.createNewFile();
				writeConfig(true);
				return true;
			}
			
			FileReader fr = new FileReader(f);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);
			
			while((config = br.readLine()) != null)
			{
				if(config.startsWith("CheckForNewVersion :"))
				{
					if(config.contains("0"))
					{
						return false;
					}else{
						return true;
					}
				}
			
			}
			
			br.close();
			
		}catch(Exception e1)
		{
			e1.printStackTrace();
		}
		return false;
	}
	
}
