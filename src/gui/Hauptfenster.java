package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import datenhaltung.Installation;
import fachkonzept.Passwort;
import fachkonzept.PasswortVerwaltung60Main;
import fachkonzept.Strings;
import fachkonzept.ToastMessage;
import fachkonzept.Verwaltung;

public class Hauptfenster extends JFrame
{
	private Verwaltung vw;
	private JTable table;
	private TableModel tableModel;
	private JPanel panel;
	private boolean change = false;
	private File path;
	private String passString;
	
	//TODO
	public JMenuItem editEntryMI;
	public JMenuItem copyPasswordSetMI;
	public JMenuItem pastePasswordSetMI;
	public JMenuItem copyUrlMI;
	public JMenuItem copyUsernameMI;
	public JMenuItem copyPasswordMI;
	public JMenuItem convertManagerMI;
	
	JButton browseM = new JButton();
	JButton kopierenM = new JButton();
	JButton passwortM = new JButton();
	JButton loeschenM = new JButton();
	
	static Strings stringList = PasswortVerwaltung60Main.stringList;
//	static String configParentFolder = PasswortVerwaltung58Main.configParentFolder;
	static Installation I = PasswortVerwaltung60Main.I;
	
	
	private static final long serialVersionUID = 70849350235944227L;
	
	public Hauptfenster(final Verwaltung vw, File path, String passString, String managerName)
	{
		super(managerName + " : " + stringList.getString("My Passwordmanager"));
		
		this.vw = vw;
		this.path = path;
		this.passString = passString;
		
		setSize(700,650);
		addWindowListener(new FensterSaveExitAdapter(vw, this, path, passString));
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int)((d.width - getSize().width) /2);
		int y = (int)((d.height - getSize().height) /2);
		
		setLocation(x,y);
		
		JMenuBar menubar = new JMenuBar();
		
		JMenu dateiM = new JMenu(stringList.getString("Data"));
		JMenu systemM = new JMenu(stringList.getString("System"));
		JMenu copyM = new JMenu(stringList.getString("Copy"));
		
		
		JButton abmeldenM = new JButton();
		JButton infoM = new JButton();

		
		KeyStroke ks5 = KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.SHIFT_MASK); 
		
		PerformCopy performCopy = new PerformCopy(2);
		kopierenM.setAction(performCopy);
		kopierenM.getActionMap().put("performCopy", performCopy);
		kopierenM.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks5, "performCopy"); 
		
		kopierenM.setBackground(Color.white);
		kopierenM.setForeground(Color.black);
		kopierenM.setFocusable(false);
		kopierenM.setToolTipText(stringList.getString("Copy to clipboard"));
		
		
		passwortM.setBackground(Color.white);
		passwortM.setForeground(Color.black);
		passwortM.setFocusable(false);
		passwortM.setToolTipText(stringList.getString("New password"));
		
		
		KeyStroke ks6 = KeyStroke.getKeyStroke(Event.DELETE, 0); 
		PerformDel performDel = new PerformDel();
		loeschenM.setAction(performDel);
		loeschenM.getActionMap().put("performDel", performDel);
		loeschenM.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks6, "performDel"); 
		
		loeschenM.setBackground(Color.white);
		loeschenM.setForeground(Color.black);
		loeschenM.setFocusable(false);
		loeschenM.setToolTipText(stringList.getString("Delete selected password"));
		
		KeyStroke ks7 = KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK); 
		PerformBrowse performBrowse = new PerformBrowse();
		browseM.setAction(performBrowse);
		browseM.getActionMap().put("performBrowse", performBrowse);
		browseM.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks7, "performBrowse");
		
		browseM.setBackground(Color.white);
		browseM.setForeground(Color.black);
		browseM.setFocusable(false);
		browseM.setToolTipText(stringList.getString("Open URL in browser"));
		
		
		abmeldenM.setBackground(Color.white);
		abmeldenM.setForeground(Color.black);
		abmeldenM.setFocusable(false);
		abmeldenM.setToolTipText(stringList.getString("Logout"));
		
		infoM.setBackground(Color.white);
		infoM.setForeground(Color.black);
		infoM.setFocusable(false);
		infoM.setToolTipText(stringList.getString("About"));
		
		
		try{
			Image imgAdd = ImageIO.read(getClass().getResource("add.png"));
			passwortM.setIcon(new ImageIcon(imgAdd.getScaledInstance( 22, 22,  java.awt.Image.SCALE_SMOOTH )));
			passwortM.setMargin(new Insets(10, 10, 10, 10));
			
			Image imgDel = ImageIO.read(getClass().getResource("delete.png"));
			loeschenM.setIcon(new ImageIcon(imgDel.getScaledInstance( 22, 22,  java.awt.Image.SCALE_SMOOTH )));
			loeschenM.setMargin(new Insets(10, 10, 10, 10));
			
			Image imgCopy = ImageIO.read(getClass().getResource("copy.png"));
			kopierenM.setIcon(new ImageIcon(imgCopy.getScaledInstance( 22, 22,  java.awt.Image.SCALE_SMOOTH )));
			kopierenM.setMargin(new Insets(10, 10, 10, 10));
			
			Image imgBrowse = ImageIO.read(getClass().getResource("browse.png"));
			browseM.setIcon(new ImageIcon(imgBrowse.getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH)));
			browseM.setMargin(new Insets(10,10,10,10));
			
			Image imgLogout = ImageIO.read(getClass().getResource("logout.png"));
			abmeldenM.setIcon(new ImageIcon(imgLogout.getScaledInstance( 22, 22,  java.awt.Image.SCALE_SMOOTH )));
			abmeldenM.setMargin(new Insets(10, 10, 10, 10));
			
			Image imgInfo = ImageIO.read(getClass().getResource("about.png"));
			infoM.setIcon(new ImageIcon(imgInfo.getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH)));
			infoM.setMargin(new Insets(10,10,10,10));
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
		//TODO
		
		//DATA
		editEntryMI = new JMenuItem(stringList.getString("Edit entry"));
		JMenuItem undoMI = new JMenuItem(stringList.getString("Undo unsaved changes"));
		JMenuItem speichernMI = new JMenuItem(stringList.getString("Save"));
		JMenuItem loginAendernMI = new JMenuItem(stringList.getString("Change login password"));
		JMenu languageMI = new JMenu(stringList.getString("Change language"));
		JMenuItem languageDEMI = new JMenuItem(stringList.getString("German"));
		JMenuItem languageENMI = new JMenuItem(stringList.getString("English"));
		convertManagerMI = new JMenuItem(stringList.getString("Convert old manager"));
		JMenuItem beendenMI = new JMenuItem(stringList.getString("Exit"));
		
		//COPY
		copyPasswordSetMI = new JMenuItem(stringList.getString("Copy passwordset"));
		pastePasswordSetMI = new JMenuItem(stringList.getString("Paste passwordset"));
		copyUrlMI = new JMenuItem(stringList.getString("Copy URL"));
		copyUsernameMI = new JMenuItem(stringList.getString("Copy username"));
		copyPasswordMI = new JMenuItem(stringList.getString("Copy password"));
		
		//SYSTEM
		JMenuItem checkForUpdateMI = new JMenuItem(stringList.getString("Check for updates."));
		JMenuItem whatIsNewMI = new JMenuItem(stringList.getString("What's new?"));
		JMenuItem settingsMI = new JMenuItem(stringList.getString("Settings"));
		JMenuItem deleteRecentManagerMI = new JMenuItem(stringList.getString("Delete visited passwordmanager"));
		JMenuItem editRecentManagerMI = new JMenuItem(stringList.getString("Edit visited passwordmanager"));
		
		
		
		undoMI.addActionListener(new UndoChanges(this));
		undoMI.setToolTipText(stringList.getString("Undo all unsaved changes"));
		
		copyUrlMI.addActionListener(new PerformCopy(0));
		copyUsernameMI.addActionListener(new PerformCopy(1));
		copyPasswordMI.addActionListener(new PerformCopy(2));
		
		beendenMI.addActionListener(new beenden());
		speichernMI.addActionListener(new ser());
		loginAendernMI.addActionListener(new loginAendern(this));
		
		deleteRecentManagerMI.addActionListener(new deleteRecentManager());
		deleteRecentManagerMI.setToolTipText(stringList.getString("Delete list of all visited passwordmanager at login"));
		
		editRecentManagerMI.addActionListener(new EditRecentManager());
		editRecentManagerMI.setToolTipText(stringList.getString("Edit list of visited passwordmanager with an Text Editor"));
		
		copyPasswordSetMI.addActionListener(new copyPasswordset());
		copyPasswordSetMI.setToolTipText(stringList.getString("Copy the complete passwordset for other passwordmanager"));
		
		pastePasswordSetMI.addActionListener(new pastePasswordset());
		pastePasswordSetMI.setToolTipText(stringList.getString("Paste the copied passwordset into passwordmanager"));
		
		editEntryMI.addActionListener(new EditEntry(this));
		editEntryMI.setToolTipText(stringList.getString("Edit selected entry"));
		
		
		whatIsNewMI.addActionListener(new WhatIsNew());
		checkForUpdateMI.addActionListener(new CheckForUpdate());
		settingsMI.addActionListener(new OpenSettings());
		
		//loeschenM.addActionListener(new pwLoeschen());
		passwortM.addActionListener(new neuesPasswort(this));
		//kopierenM.addActionListener(new kopieren());
		abmeldenM.addActionListener(new abmelden());
		infoM.addActionListener(new about());
		
		languageMI.add(languageDEMI);
		languageMI.add(languageENMI);
		
		
		languageDEMI.addActionListener(new ChangeLanguage("DE"));
		languageENMI.addActionListener(new ChangeLanguage("EN"));
		
		convertManagerMI.addActionListener(new ConvertManager());
		convertManagerMI.setToolTipText(stringList.getString("Converts manager with lower version than 6.0"));
		
		KeyStroke ks1 = KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.ALT_MASK);
		copyPasswordSetMI.setAccelerator(ks1);
		
		KeyStroke ks2 = KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.ALT_MASK);
		pastePasswordSetMI.setAccelerator(ks2);
		
		KeyStroke ks3 = KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK);
		speichernMI.setAccelerator(ks3);
		
		KeyStroke ks4 = KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK);
		beendenMI.setAccelerator(ks4);
		
		
		
		menubar.add(dateiM);
		menubar.add(copyM);
		menubar.add(systemM);
		
		
		menubar.add(passwortM);
		menubar.add(loeschenM);
		menubar.add(kopierenM);
		menubar.add(browseM);
		
		// sets the following items on the right side of the menubar
		menubar.add(Box.createHorizontalGlue());
		
		menubar.add(infoM);
		menubar.add(abmeldenM);
		
		
		
		copyM.add(copyPasswordSetMI);
		copyM.add(pastePasswordSetMI);
		copyM.addSeparator();
		copyM.add(copyUrlMI);
		copyM.add(copyUsernameMI);
		copyM.add(copyPasswordMI);
		
		dateiM.add(editEntryMI);
		dateiM.add(undoMI);
		dateiM.add(speichernMI);
		dateiM.add(loginAendernMI);
		dateiM.add(languageMI);
		dateiM.add(convertManagerMI);
		dateiM.addSeparator();
		dateiM.add(beendenMI);
		
		systemM.add(checkForUpdateMI);
		systemM.add(whatIsNewMI);
		systemM.add(settingsMI);
		systemM.addSeparator();
		systemM.add(deleteRecentManagerMI);
		systemM.add(editRecentManagerMI);
		
//		verwaltungM.add(passwortMI);
//		verwaltungM.add(loeschenMI);
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		tableModel = new TableModel(vw);
		table = new JTable(tableModel);
		table.setAutoCreateRowSorter(false);
		
		table.addMouseListener(new showPasswortListener());
		
		panel.add(new JScrollPane(table));
		add(panel);
		
		setJMenuBar(menubar);		
		
		add(panel);
		
		setVisible(true);
		switchButtonState(false);
		
//		Object[] options = {"Yes", "No"};
//		int n = JOptionPane.showOptionDialog(null, "Test" , "Test",
//		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
//		
//		if(n == JOptionPane.YES_OPTION)
//		{
//			vw.convert(new File("/media/blob/SHARE/md5.ldk"), "md5");
//		}		
	}
	
	public class showPasswortListener implements MouseListener
	{		
		public void mouseClicked(MouseEvent e) 
		{
			switchButtonState(true);			
		}

		public void mousePressed(MouseEvent e) 
		{
			tableModel.showPasswortKlartext(table.getSelectedRow());
		}

		public void mouseReleased(MouseEvent e) 
		{
			tableModel.hidePasswortKlartext(table.getSelectedRow());
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}
	}
	
	public class beenden implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(change == true)
			{
				Object[] options = {stringList.getString("Yes"), stringList.getString("No"), stringList.getString("Cancel")};
				int n = JOptionPane.showOptionDialog(null, stringList.getString("Do you want to save changes?"),stringList.getString("My Passwordmanager"),
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if(n == JOptionPane.YES_OPTION)
				{
						vw.ser(path, passString, "SHA-512");
						setChange(false);
					System.exit(0);
				}
				
				if(n == JOptionPane.NO_OPTION)
				{
					System.exit(0);
				}
				
				if(n == JOptionPane.CANCEL_OPTION || n == JOptionPane.CLOSED_OPTION)
				{
					
				}
			} else
			{
				System.exit(0);
			}
			
		}
	}
	
	public class neuesPasswort implements ActionListener
	{
		Hauptfenster hf;
		public neuesPasswort(Hauptfenster hf)
		{
			this.hf = hf;
		}
		public void actionPerformed(ActionEvent e)
		{
			@SuppressWarnings("unused")
			AnlegenView av = new AnlegenView(vw, tableModel, hf);
		}
	}
		
	public class ser implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{			
				vw.ser(path, passString, "SHA-512");
				setChange(false);
		}
	}
	
	public class UndoChanges implements ActionListener
	{
		Hauptfenster hf;
		public UndoChanges(Hauptfenster hf)
		{
			this.hf = hf;
		}
		
		public void actionPerformed(ActionEvent e)
		{			
			Object[] options = {stringList.getString("Yes"), stringList.getString("No")};
			int n = JOptionPane.showOptionDialog(null, stringList.getString("Do you really want to undo unsaved changes?"),stringList.getString("My Passwordmanager"),JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		
			if(n == JOptionPane.YES_OPTION)
			{
				vw = vw.deser(path, passString, "SHA-512");
				new Hauptfenster(vw, path, passString, path.getName());
				hf.setVisible(false);
				hf.dispose();
				setChange(false);
			}	
		}
	}
	
	
	public class abmelden implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(change == true)
			{
				Object[] options = {stringList.getString("Yes"), stringList.getString("No"), stringList.getString("Cancel")};
				int n = JOptionPane.showOptionDialog(null, stringList.getString("Do you want to save changes?"),stringList.getString("My Passwordmanager"),
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if(n == JOptionPane.YES_OPTION)
				{
						vw.ser(path, passString, "SHA-512");
						setChange(false);
				}
				
				if(n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION)
				{
						dispose();
						@SuppressWarnings("unused")
						Login lo = new Login();
				}
			} else
			{
					dispose();
					@SuppressWarnings("unused")
					Login lo = new Login();
			}
			
		}
	}
	
	public class loginAendern implements ActionListener
	{
		private Hauptfenster hauptf;
		public loginAendern(Hauptfenster hauptf) 
		{
			this.hauptf = hauptf;
		}

		public void actionPerformed(ActionEvent e)
		{
			if(change == true)
			{
				Object[] options = {stringList.getString("Yes"), stringList.getString("No"), stringList.getString("Cancel")};
				int n = JOptionPane.showOptionDialog(null, stringList.getString("Do you want to save changes?"),stringList.getString("My Passwordlist"),
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if(n == JOptionPane.YES_OPTION)
				{
						vw.ser(path, passString, "SHA-512");
						setChange(false);
				}
				
				if(n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION)
				{
					setVisible(false);
					@SuppressWarnings("unused")
					LoginAendernFenster lf = new LoginAendernFenster(vw, path,hauptf);
					setChange(false);
				}
			} else
			{
				setVisible(false);
				@SuppressWarnings("unused")
				LoginAendernFenster lf = new LoginAendernFenster(vw, path,hauptf);
				setChange(false);
			}
		}
	}
	
	 public class deleteRecentManager implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 Object[] options = {stringList.getString("Yes"), stringList.getString("No")};
			int n = JOptionPane.showOptionDialog(null, stringList.getString("Do you really want to clear list of recently opened manager?"),stringList.getString("My Passwordmanager"),JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		
			if(n == JOptionPane.YES_OPTION)
			{
				 File f = I.getManList();
				 f.delete();
			}	
		 }
	 }
	 
	 public class EditRecentManager implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 new EditRecentManagerView();
		 }
	 }
	
	public class ChangeLanguage implements ActionListener
	{
		String lang;
		public ChangeLanguage(String lang) 
		{
			this.lang = lang;
		}

		public void actionPerformed(ActionEvent e)
		{
			if(change == true)
			{
				Object[] options = {stringList.getString("Yes"), stringList.getString("No"), stringList.getString("Cancel")};
				int n = JOptionPane.showOptionDialog(null, stringList.getString("Do you want to save changes?"),stringList.getString("My Passwordlist"),
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if(n == JOptionPane.YES_OPTION)
				{
						vw.ser(path, passString, "SHA-512");
						setChange(false);
				}
				
				if(n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION)
				{
					stringList.setLang(lang);
					JOptionPane.showMessageDialog(null, stringList.getString("Changes will take place after a restart"));
					tableModel.changeLanguage();
					dispose();
					new Login();
					setChange(false);
				}
			} else
			{
				stringList.setLang(lang);				
				JOptionPane.showMessageDialog(null, stringList.getString("Changes will take place after a restart"));
				tableModel.changeLanguage();
				dispose();
				new Login();
				setChange(false);
			}
			
			
		}
	}
	
	public class about implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new AboutGUI();
		}
	}
	
	public class copyPasswordset implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int row = table.getSelectedRow();
			if(row!= -1)
			{
				String copiedDescription = (String) tableModel.getDescriptionAt(row);
				String copiedUrl = (String) tableModel.getUrlAt(row);
				String copiedUsername = (String) tableModel.getUsernameAt(row);
				String copiedPassword = (String) tableModel.getPasswordAt(row);
				
				String copiedSet = "||^!||" + copiedDescription + "||" + copiedUrl + "||" + copiedUsername + "||" + copiedPassword + "||!^||";

				int[] asdf = new int[copiedSet.length()];
				
				String copiedSetInt = "";
				
				for(int i = 0;i<copiedSet.length();i++)
				{
					asdf[i] = copiedSet.charAt(i);
					copiedSetInt = copiedSetInt + asdf[i] + "|";
				}
						
				StringSelection stringSelection = new StringSelection(copiedSetInt);
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
				
				new ToastMessage(stringList.getString("Copied passwordset into clipboard"),3000).setVisible(true);
				//JOptionPane.showMessageDialog(null, stringList.getString("Copied password into clipboard"));
			}else
			{
				new ToastMessage(stringList.getString("No Password selected"),3000).setVisible(true);
				//JOptionPane.showMessageDialog(null, stringList.getString("No Password selected"));
			}
		}
	}
	
	public class pastePasswordset implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable contents = clpbrd.getContents(null);
		   
			String passwordset = "";
			
			boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);			
			
			String copiedPasswordset = "";
			LinkedList<Character> temp = new LinkedList<Character>();
			if (hasTransferableText) 
		    {
			      try 
			      {
			    	  copiedPasswordset = (String)contents.getTransferData(DataFlavor.stringFlavor);
			    	  int start = 0;
			    	  int end;
			    	  for(int i=0;i<copiedPasswordset.length();i++)
			    	  {
			    		  if(Character.toString(copiedPasswordset.charAt(i)).equals("|"))
			    		  {
			    			  end = i-1;
			    			  temp.add((char) Integer.parseInt(copiedPasswordset.subSequence(start, end+1).toString()));
			    			  start = i+1;  
			    		  }
			    	  }		
			      }
				  catch (Exception ex)
				  {
					  System.out.println(ex);
				      ex.printStackTrace();
				  }
		    }
		    
			for(Character ls : temp)
			{
				passwordset = passwordset + ls;
			}
			
		    if(passwordset.startsWith("||^!||") && passwordset.endsWith("||!^||"))
		    {
		    	passwordset = passwordset.substring(6,passwordset.length()-6);

		    	String description = "";
		    	String url = "";
		    	String username = "";
		    	
		    	String seperator = "|";
		    	for(int i = 0; i< passwordset.length(); i++)
		    	{
		    		if(passwordset.charAt(i) == seperator.charAt(0) && passwordset.charAt(i+1) == seperator.charAt(0))
		    		{
		    			description = passwordset.substring(0, i);
		    			passwordset = passwordset.substring(i+2,passwordset.length());
		    			break;
		    		}
		    	}
		    	
		    	for(int i = 0; i< passwordset.length(); i++)
		    	{
		    		if(passwordset.charAt(i) == seperator.charAt(0) && passwordset.charAt(i+1) == seperator.charAt(0))
		    		{
		    			url = passwordset.substring(0, i);
		    			passwordset = passwordset.substring(i+2,passwordset.length());
		    			break;
		    		}
		    	}
		    	
		    	for(int i = 0; i< passwordset.length(); i++)
		    	{
		    		if(passwordset.charAt(i) == seperator.charAt(0) && passwordset.charAt(i+1) == seperator.charAt(0))
		    		{
		    			username = passwordset.substring(0, i);
		    			passwordset = passwordset.substring(i+2,passwordset.length());
		    			break;
		    		}
		    	}
		    	
		    	//Prevent to write "NULL" instead of nothing
		    	if(description.isEmpty())
		    	{
		    		description = "";
		    	}
		    	
		    	if(passwordset.isEmpty())
		    	{
		    		passwordset = "";
		    	}
		    	
		    	if(username.isEmpty())
		    	{
		    		username = "";
		    	}
		    	
		    	if(url.isEmpty())
		    	{
		    		url = "";
		    	}
		    	
		    	Passwort pw = new Passwort(description,passwordset, username, url);
				
				vw.addPasswort(pw);
				tableModel.fireTableDataChanged();
				
				StringSelection emptyString = new StringSelection("");
				clpbrd.setContents(emptyString, null);
				setChange(true);
				switchButtonState(false);
		    }else
		    {
		    	new ToastMessage(stringList.getString("No passwordset in clipboard\n Copy a passwordset first"),4000).setVisible(true);
		    	//JOptionPane.showMessageDialog(null, stringList.getString("No passwordset in clipboard\nCopy a passwordset first"));
		    }
		    
		}
	}
	
	public class EditEntry implements ActionListener
	{
		Hauptfenster hf;
		public EditEntry(Hauptfenster hf)
		{
			this.hf = hf;
		}
		public void actionPerformed(ActionEvent e)
		{
			int row = table.getSelectedRow();
			if(row!= -1)
			{
				//change = true;
				@SuppressWarnings("unused")
				AnlegenView av = new AnlegenView(vw, tableModel, hf, row, tableModel.getDescriptionAt(row), tableModel.getUsernameAt(row), tableModel.getPasswordAt(row), tableModel.getUrlAt(row));
				//vw.getListe().remove(row);
				//tableModel.fireTableDataChanged();
			}else{
				new ToastMessage(stringList.getString("No Password selected"), 2000).setVisible(true);;
			}
		}
	}
	
	@SuppressWarnings("serial")
	public class PerformCopy extends AbstractAction
	{
		int index;
		// index = 0 => copy URL
		// index = 1 => copy Username
		// index = 2 => copy Password
		
		
		public PerformCopy(int index)
		{
			this.index = index;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			int row = table.getSelectedRow();
			if(row!= -1)
			{
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				
				if(index == 0)
				{
					String copiedURL = (String) tableModel.getUrlAt(row);
					StringSelection stringSelection = new StringSelection(copiedURL);
					clpbrd.setContents(stringSelection, null);
					new ToastMessage(stringList.getString("Copied URL into clipboard"),3000).setVisible(true);
				}
				
				if(index == 1)
				{
					String copiedUsername = (String) tableModel.getUsernameAt(row);
					StringSelection stringSelection = new StringSelection(copiedUsername);
					clpbrd.setContents(stringSelection, null);
					new ToastMessage(stringList.getString("Copied username into clipboard"),3000).setVisible(true);
				}
				
				if(index == 2)
				{
					String copiedPassword = (String) tableModel.getPasswordAt(row);
					StringSelection stringSelection = new StringSelection(copiedPassword);
					clpbrd.setContents(stringSelection, null);
					new ToastMessage(stringList.getString("Copied password into clipboard"),3000).setVisible(true);
				}
			}
		}
	}
	
	@SuppressWarnings("serial")
	public class PerformDel extends AbstractAction
	{  
	    public void actionPerformed(ActionEvent e) 
	    {     
	    	int row = table.getSelectedRow();
			if(row!= -1)
			{
				
				Object[] options = {stringList.getString("Yes"), stringList.getString("No")};
				int n = JOptionPane.showOptionDialog(null, stringList.getString("Are you sure you want to delete this password?"),stringList.getString("My Passwordmanager"),JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				
				if(n == JOptionPane.YES_OPTION)
				{
					vw.getListe().remove(row);
					tableModel.fireTableDataChanged();
					setChange(true);
					switchButtonState(false);
				}
			}
			
	    }
	}
	
	@SuppressWarnings("serial")
	public class PerformBrowse extends AbstractAction
	{  
	    public void actionPerformed(ActionEvent e) 
	    {     
	    	int row = table.getSelectedRow();
			if(row!= -1)
			{
				
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) 
			    {			    	
			    	String url = tableModel.getUrlAt(row);
			        try 
			        {		
			        	desktop.browse(new URI(url));
			            
			        } catch (Exception e1) 
			        {
			        	e1.printStackTrace();
			        	try{
			        		desktop.browse(new URI("www."+url));
			        	}catch(Exception e2)
			        	{
			        		new ToastMessage(stringList.getString("No URL to open"), 2000).setVisible(true);
				            e2.printStackTrace();
			        	}
			        	
			        }
			    }
			}
	    }
	}
	
	
	public boolean getChange()
	{
		return change;
	}
	
	
	public void switchButtonState(boolean state) 
	{
		// TODO		
		editEntryMI.setEnabled(state);
		copyPasswordSetMI.setEnabled(state);
		copyUrlMI.setEnabled(state);
		copyUsernameMI.setEnabled(state);
		copyPasswordMI.setEnabled(state);
		
		browseM.setEnabled(state);
		kopierenM.setEnabled(state);
		loeschenM.setEnabled(state);
	}

	public void setChange(boolean newChange)
	{
		change = newChange;
		
		if(change)
		{
			//Add * to title
			if(!getTitle().endsWith("*"))
			{
				setTitle(getTitle() + "*");
			}
			
		}else{
			//Remove * from title
			setTitle(getTitle().substring(0, getTitle().length()-1));
		}
		
	}
	
	public class CheckForUpdate implements ActionListener
	{
		private double newestV;
		private double v;
		public void actionPerformed(ActionEvent e)
		{
			new Thread(){
	            public void run() 
	            {
	            	check();
	            }
	        }.start();
		}
		
		private void check()
		{
			try {
				//URL vU = new URL("http://googledrive.com/host/0B32x7KV3vndDcUh1TUV1VG1Zc3M/Downloads/Version.txt");
//				URL vU = new URL("https://2e2d0f8ebd572b9d05004cbdc8d7baa4ea156560.googledrive.com/host/0B32x7KV3vndDcUh1TUV1VG1Zc3M/Downloads/Version.txt");
				URL vU = new URL("http://dkinas.de/Passwortverwaltung/Downloads/Version.txt");
				URI dU = null;
				
				Scanner s = new Scanner(vU.openStream());
				
				newestV = Double.valueOf(s.next());
				v = stringList.getVersion();				
				s.close();
				
				if(v < newestV)
				{
					Object[] options = {stringList.getString(".exe (recommended)"), stringList.getString(".jar")};
					int n = JOptionPane.showOptionDialog(null, stringList.getString("What file do you want to download?"),stringList.getString("My Passwordmanager"),JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					
					if(n == JOptionPane.YES_OPTION)
					{
						dU = new URI("http://dkinas.de/Passwortverwaltung?v=" + newestV + "&format=.exe");
					}
					
					if(n == JOptionPane.NO_OPTION)
					{
						dU = new URI("http://dkinas.de/Passwortverwaltung?v=" + newestV + "&format=.jar");
					}
					
					if(n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION)
					{
						Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
					    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) 
					    {			    	
					        try 
					        {		
					        	desktop.browse(dU);
					            
					        } catch (Exception e1) 
					        {
				        		new ToastMessage(stringList.getString("Error while checking for updates"), 3000).setVisible(true);
					            e1.printStackTrace();
					        	
					        }
					    }	
					}
			    }else{
			    	new ToastMessage(stringList.getString("No update found"), 3000).setVisible(true);
			    }	
			} catch(Exception e1)
			{
				new ToastMessage(stringList.getString("Error while checking for updates"), 3000).setVisible(true);
				e1.printStackTrace();
			}
		}
	}
	
	public class WhatIsNew implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JOptionPane.showMessageDialog(null, stringList.getChangelog());			
		}
		
	}
	
	public class OpenSettings implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new SettingGUI();
		}
	}
	
	public class ConvertManager implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String lastVisited = "";
			File f = I.getLastVisited();
			JFileChooser chooser = new JFileChooser();
			
			try
			{
				if(!f.exists())
				{
					f.createNewFile();
				}
				
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				lastVisited = br.readLine();
				
				br.close();
				
				
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}

			try{
				chooser.setCurrentDirectory(new File(lastVisited));
			}catch(Exception e3)
			{
				e3.printStackTrace();
			}
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("ldk","ldk");
			chooser.setFileFilter(filter);
			int state = chooser.showOpenDialog(null);
			if(state == JFileChooser.APPROVE_OPTION)
			{				
				path = new File(chooser.getSelectedFile().getPath());
				if(!path.toString().endsWith(".ldk"))
				{
					path = new File(path.toString()+".ldk");
				}
				
				if(path.exists())
				{
					path = chooser.getSelectedFile();
				} else
				{
					new ToastMessage(stringList.getString("Error! File does not exist."), 2000).setVisible(true);
				}
				
//				JFrame convertGUI = new JFrame(stringList.getString("Enter password"));

			    // prompt the user to enter their name
			    String pass = JOptionPane.showInputDialog(null, stringList.getString("Enter password"));
			    
			    if(!pass.isEmpty())
			    {
			    	vw.convert(path, pass);
			    }
				
			}	
		}
	}
	
}
