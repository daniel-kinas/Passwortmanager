package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import fachkonzept.PasswortVerwaltung60Main;
import fachkonzept.Strings;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AboutGUI extends JFrame
{
	
	static Strings stringList = PasswortVerwaltung60Main.stringList;
	
	private static final long serialVersionUID = 70849350235944227L;

	public AboutGUI() {
		super(stringList.getString("About"));
		
	
		
		
		
		getContentPane().setBackground(UIManager.getColor("TabbedPane.light"));
		
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{414, 0};
		gridBagLayout.rowHeights = new int[]{205, 34, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(UIManager.getColor("TabbedPane.light"));
		textPane.setFont(new Font("Segoe UI Semilight", Font.BOLD, 15));
		textPane.setEnabled(true);
		textPane.setEditable(false);
		textPane.setCaretColor(Color.BLACK);
		textPane.setText(stringList.getString("Passwortverwaltung \nVersion: "+stringList.getVersion()+"\nContact: support@dkinas.de"));
		
		
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.insets = new Insets(0, 0, 5, 0);
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 0;
		getContentPane().add(textPane, gbc_textPane);
		
		JButton okButton = new JButton(stringList.getString("OK"));
		okButton.setFont(new Font("Segoe WP SemiLight", Font.BOLD, 15));
		okButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				setVisible(false);
			}
		});
		GridBagConstraints gbc_okButton = new GridBagConstraints();
		gbc_okButton.fill = GridBagConstraints.VERTICAL;
		gbc_okButton.gridx = 0;
		gbc_okButton.gridy = 1;
		getContentPane().add(okButton, gbc_okButton);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int)((d.width - getSize().width) /2);
		int y = (int)((d.height - getSize().height) /2);
		
		setLocation(x,y);
		
		setVisible(true);
	}
}
