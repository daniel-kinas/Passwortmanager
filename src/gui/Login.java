package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import datenhaltung.Installation;
import fachkonzept.PasswortVerwaltung60Main;
import fachkonzept.Strings;
import fachkonzept.ToastMessage;
import fachkonzept.Verwaltung;

public class Login extends JFrame implements Serializable
{
	private static final long serialVersionUID = 70849350235944227L;
	private static Verwaltung vw;
	private static File path;
	private String version;
	public JTextField pfadText;
	public JPasswordField password;
	
	public JComboBox<String> pathCombo;
	
	static Strings stringList = PasswortVerwaltung60Main.stringList;
	static Installation I = PasswortVerwaltung60Main.I;
//	static String configParentFolder = PasswortVerwaltung58Main.configParentFolder;
	private File managerFile;
	
	public Login()
	{
		super(stringList.getString("Open Passwordmanager"));

		version = String.valueOf(stringList.getVersion());	
		
		vw = new Verwaltung();
		
		setSize(410,320);
		
		setResizable(true);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int)((d.width - getSize().width) /2);
		int y = (int)((d.height - getSize().height) /2);
		
		setLocation(x,y);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblPasswortverwaltung = new JLabel(stringList.getString("Passwordmanager"));
		lblPasswortverwaltung.setBackground(SystemColor.menu);
		lblPasswortverwaltung.setFont(new Font("Segoe WP SemiLight", Font.BOLD, 20));
		lblPasswortverwaltung.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblPasswortverwaltung, BorderLayout.NORTH);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.menu);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		GridBagLayout gbl_desktopPane = new GridBagLayout();
		gbl_desktopPane.columnWidths = new int[]{33, 203, 89, 46, 0};
		gbl_desktopPane.rowHeights = new int[]{44, 14, 10, 23, 14, 23, 14, 0};
		gbl_desktopPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_desktopPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		desktopPane.setLayout(gbl_desktopPane);
		
		JLabel lblPfad = new JLabel(stringList.getString("Path"));
		lblPfad.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		GridBagConstraints gbc_lblPfad = new GridBagConstraints();
		gbc_lblPfad.anchor = GridBagConstraints.WEST;
		gbc_lblPfad.fill = GridBagConstraints.VERTICAL;
		gbc_lblPfad.insets = new Insets(0, 0, 5, 5);
		gbc_lblPfad.gridx = 1;
		gbc_lblPfad.gridy = 1;
		desktopPane.add(lblPfad, gbc_lblPfad);
		
		
		JButton btnOpen = new JButton(stringList.getString("New"));
		btnOpen.setToolTipText((String) null);
		btnOpen.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		GridBagConstraints gbc_btnOpen = new GridBagConstraints();
		gbc_btnOpen.anchor = GridBagConstraints.SOUTH;
		gbc_btnOpen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpen.insets = new Insets(0, 0, 5, 5);
		gbc_btnOpen.gridheight = 2;
		gbc_btnOpen.gridx = 2;
		gbc_btnOpen.gridy = 1;
		desktopPane.add(btnOpen, gbc_btnOpen);
		
		btnOpen.setToolTipText(stringList.getString("Create new Passwordmanager"));
		
		btnOpen.addActionListener(new open());
		
		
		pathCombo = new JComboBox<String>();
		pathCombo.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 11));
		GridBagConstraints gbc_pathCombo = new GridBagConstraints();
		gbc_pathCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_pathCombo.insets = new Insets(0,0,5,5);
		gbc_pathCombo.gridx = 1;
		gbc_pathCombo.gridy = 2;
		desktopPane.add(pathCombo, gbc_pathCombo);
		
		
		
		
		managerFile = I.getManList();
		try 
		{
			if(!managerFile.exists())
			{
				managerFile.createNewFile();
				
			}
			
			
			FileReader fr = new FileReader(managerFile);
			BufferedReader br = new BufferedReader(fr);
			
			String ldkLine;
			while((ldkLine = br.readLine()) != null)
			{
				pathCombo.addItem(ldkLine);
			}
			
			br.close();
		
		} catch (IOException e1) 
		{	
			e1.printStackTrace();
		}
		
		
		
		JButton btnSearch = new JButton(stringList.getString("Open"));
		btnSearch.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.BOTH;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 2;
		gbc_btnSearch.gridy = 3;
		desktopPane.add(btnSearch, gbc_btnSearch);
		
		btnSearch.setToolTipText(stringList.getString("Search Passwordmanager"));

		btnSearch.addActionListener(new pfadSuche());
		
		
		JLabel lblPasswort = new JLabel(stringList.getString("Password"));
		lblPasswort.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		GridBagConstraints gbc_lblPasswort = new GridBagConstraints();
		gbc_lblPasswort.anchor = GridBagConstraints.WEST;
		gbc_lblPasswort.fill = GridBagConstraints.VERTICAL;
		gbc_lblPasswort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswort.gridx = 1;
		gbc_lblPasswort.gridy = 4;
		desktopPane.add(lblPasswort, gbc_lblPasswort);
		
		password = new JPasswordField();
		password.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 11));
		GridBagConstraints gbc_password = new GridBagConstraints();
		gbc_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_password.insets = new Insets(0, 0, 5, 5);
		gbc_password.gridx = 1;
		gbc_password.gridy = 5;
		desktopPane.add(password, gbc_password);
		password.setColumns(10);

		
		//Set focus on password input after selecting one entry from pathCombo combobox
		pathCombo.addActionListener(new SetFocusOnPassword());
		
		
		JButton btnLogin = new JButton();
		btnLogin.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		
		KeyStroke ks1 = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0); 
		PerformLogin performLogin = new PerformLogin();
		btnLogin.setAction(performLogin);
		btnLogin.getActionMap().put("performLogin", performLogin);
		btnLogin.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks1, "performLogin"); 
		btnLogin.setText(stringList.getString("Login"));
		
		
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.BOTH;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 5;
		desktopPane.add(btnLogin, gbc_btnLogin);
		
		JLabel lblV = new JLabel(version);
		GridBagConstraints gbc_lblV = new GridBagConstraints();
		gbc_lblV.anchor = GridBagConstraints.NORTH;
		gbc_lblV.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblV.gridx = 3;
		gbc_lblV.gridy = 6;
		desktopPane.add(lblV, gbc_lblV);
		
		
		
		
		setDropTarget(new dragndrop());
		
		
		
		setVisible(true);
		
		//Fit size of GUI to comboBox
		setSize(pathCombo.getSize().width + 200, 320);
		x = (int)((d.width - getSize().width) /2);
		y = (int)((d.height - getSize().height) /2);
		setLocation(x,y);
		repaint();
		
		
		
		password.requestFocus();
	}
		
	@SuppressWarnings("serial")
	public class dragndrop extends DropTarget
	{
		public synchronized void drop(DropTargetDropEvent evt) 
		{
            try 
            {
                evt.acceptDrop(DnDConstants.ACTION_COPY);
                @SuppressWarnings("unchecked")
				List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                for (File file : droppedFiles) 
                {
                	boolean isInCombo = false;
    				for(int i = 0; i < pathCombo.getItemCount();i++)
    				{
    					if(pathCombo.getItemAt(i).equals(file.getAbsolutePath()))
    					{
    						isInCombo = true;
    						pathCombo.setSelectedItem(file.getAbsolutePath());
    					}
    				}
    				
    				if(!isInCombo)
    				{
    					try{
    						FileWriter fw = new FileWriter(managerFile,true);
    						BufferedWriter bw = new BufferedWriter(fw);
    						
    						
							pathCombo.addItem(file.getAbsolutePath());
							pathCombo.setSelectedItem(file.getAbsolutePath());
							bw.append(file.getAbsolutePath()+"\n");
						
    						bw.close();
    					}catch(IOException e2)
    					{
    						e2.printStackTrace();
    					}
    				}
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
	}
	
	public class pfadSuche implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String lastVisited = "";
			File f = I.getLastVisited();
			
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
			
			
			JFileChooser chooser = new JFileChooser();

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
					new ToastMessage(stringList.getString("Created new list.")+"\n" + stringList.getString("Please set login password."), 2000).setVisible(true);;
				}
				
				boolean isInCombo = false;
				
				for(int i = 0; i < pathCombo.getItemCount();i++)
				{
					if(pathCombo.getItemAt(i).equals(path.getAbsolutePath() + ".ldk") || pathCombo.getItemAt(i).equals(path.getAbsolutePath()))
					{
						isInCombo = true;
						pathCombo.setSelectedItem(path.getAbsolutePath());
					}
				}
				
				if(!isInCombo)
				{
					try{
						FileWriter fw = new FileWriter(managerFile,true);
						BufferedWriter bw = new BufferedWriter(fw);
						
						
					
						if(!path.getAbsoluteFile().toString().endsWith("ldk"))
						{
							
							pathCombo.addItem(path.getAbsolutePath() + ".ldk");
							pathCombo.setSelectedItem(path.getAbsolutePath() + ".ldk");
							bw.append(path.getAbsolutePath() + ".ldk"+"\n");
						}else{
							pathCombo.addItem(path.getAbsolutePath());
							pathCombo.setSelectedItem(path.getAbsolutePath());
							bw.append(path.getAbsolutePath()+"\n");
						}
						
						bw.close();
					}catch(IOException e2)
					{
						e2.printStackTrace();
					}
				}
				
				
				try
				{
					FileWriter fw = new FileWriter(f);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(chooser.getCurrentDirectory().toString());
					
					bw.close();
					
					
				} catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}	
			//Set focus on password input
			password.requestFocus();
			
		}		
	}
	
	
	public class open implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String lastVisited = "";
			File f = I.getLastVisited();
			
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
			
			
			JFileChooser chooser = new JFileChooser();

			try{
				chooser.setCurrentDirectory(new File(lastVisited));
			}catch(Exception e3)
			{
				e3.printStackTrace();
			}
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("ldk","ldk");
			chooser.setFileFilter(filter);
			int state = chooser.showSaveDialog(null);
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
					new ToastMessage(stringList.getString("Created new list.")+"\n" + stringList.getString("Please set login password."), 2000).setVisible(true);;
				}
				
				boolean isInCombo = false;
				for(int i = 0; i < pathCombo.getItemCount();i++)
				{
					if(pathCombo.getItemAt(i).equals(path.getAbsolutePath() + ".ldk") || pathCombo.getItemAt(i).equals(path.getAbsolutePath()))
					{
						isInCombo = true;
						pathCombo.setSelectedItem(path.getAbsolutePath());
					}
				}
				
				if(!isInCombo)
				{
					try{
						FileWriter fw = new FileWriter(managerFile,true);
						BufferedWriter bw = new BufferedWriter(fw);
						
						
					
						if(!path.getAbsoluteFile().toString().endsWith("ldk"))
						{
							
							pathCombo.addItem(path.getAbsolutePath() + ".ldk");
							pathCombo.setSelectedItem(path.getAbsolutePath() + ".ldk");
							bw.append(path.getAbsolutePath() + ".ldk"+"\n");
						}else{
							pathCombo.addItem(path.getAbsolutePath());
							pathCombo.setSelectedItem(path.getAbsolutePath());
							bw.append(path.getAbsolutePath()+"\n");
						}
						
						bw.close();
					}catch(IOException e2)
					{
						e2.printStackTrace();
					}
				}
				
				
				try
				{
					FileWriter fw = new FileWriter(f);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(chooser.getCurrentDirectory().toString());
					
					bw.close();
					
					
				} catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}	
			//Set focus on password input
			password.requestFocus();
		}
	}
	
	@SuppressWarnings("serial")
	public class PerformLogin extends AbstractAction
	{  
	    public void actionPerformed(ActionEvent e) {  
	    	String pathString = (String) pathCombo.getSelectedItem();
			@SuppressWarnings("deprecation")
			String passString = password.getText();
			
			File pathFile = new File(pathString);
			
			if(pathFile.exists())
			{
				vw = vw.deser(pathFile,passString, "SHA-512");
			}else
			{
				vw.ser(pathFile, passString, "SHA-512");
			}
			
			dispose();
			@SuppressWarnings("unused")
			Hauptfenster pf = new Hauptfenster(vw, pathFile, passString, pathFile.getName());
	    }
	}
	
	public class SetFocusOnPassword implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			password.requestFocus();
		}
	}
}
