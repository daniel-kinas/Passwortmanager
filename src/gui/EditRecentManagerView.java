package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import datenhaltung.Installation;
import fachkonzept.PasswortVerwaltung60Main;
import fachkonzept.Strings;

@SuppressWarnings("serial")
public class EditRecentManagerView extends JFrame
{
	JList<String> list;
	DefaultListModel<String> recentManager = new DefaultListModel<String>();
	File managerFile;
	int sizeOfMan = 0;
	static Strings stringList = PasswortVerwaltung60Main.stringList;
//	static String configParentFolder = PasswortVerwaltung58Main.configParentFolder;
	static Installation I = PasswortVerwaltung60Main.I;
	public EditRecentManagerView() 
	{
		super(stringList.getString("Edit visited passwordmanager"));
		
		
		initialize();
	}
	
	private void initialize() 
	{
		setSize(500,250);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int)((d.width - getSize().width) /2);
		int y = (int)((d.height - getSize().height) /2);
		
		setLocation(x,y);
		
		initList();
		
		list = new JList<String>(recentManager);
		
		
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));
		
		getContentPane().add(list, BorderLayout.CENTER);
		
		JButton deleteButton = new JButton(stringList.getString("Delete"));
		deleteButton.addActionListener(new performDelete());
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(deleteButton);
		getContentPane().add(buttonPanel, BorderLayout.EAST);
		
		
		
		
		
		
		setVisible(true);
	}
	
	public void initList()
	{
		
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
				recentManager.addElement(ldkLine);
			}
			
			br.close();
		
		} catch (IOException e1) 
		{	
			e1.printStackTrace();
		}
	}
	
	public class performDelete implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			managerFile.delete();
			int row = list.getSelectedIndex();
			
			try{
				FileWriter fw = new FileWriter(managerFile,true);
				BufferedWriter bw = new BufferedWriter(fw);
				recentManager.remove(row);
				for(int i = 0; i< recentManager.size(); i++)
				{
					bw.append(recentManager.getElementAt(i) + "\n");					
				}
				
				bw.close();
			}catch(IOException e1)
			{
				e1.printStackTrace();
			}
			
			
		}
	}

}
