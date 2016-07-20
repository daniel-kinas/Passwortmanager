package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import fachkonzept.*;

public class LoginAendernFenster extends JFrame
{
	private static final long serialVersionUID = 70849350235944227L;
	@SuppressWarnings("unused")
	private Verwaltung vw;
	@SuppressWarnings("unused")
	private File path;
	@SuppressWarnings("unused")
	private Hauptfenster hf;
	
	static Strings stringList = PasswortVerwaltung60Main.stringList;
	
	public LoginAendernFenster(final Verwaltung vw, final File path, final Hauptfenster hf)
	{
		super(stringList.getString("Change login password"));
		setSize(280,250);
		setLayout(new GridLayout(3,2));
		setResizable(true);
		
		addWindowListener(new WindowListener() 
		{
			
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowClosing(WindowEvent e) 
			{
				dispose();
				hf.setVisible(true);
			}
			
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.vw = vw;
		this.path = path;
		this.hf = hf;
		
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int)((d.width - getSize().width) /2);
		int y = (int)((d.height - getSize().height) /2);
		
		setLocation(x,y);
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel5.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel txtAltesPW = new JLabel(stringList.getString("Old password"));
		txtAltesPW.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		JLabel txtNeuesPW = new JLabel(stringList.getString("New password"));
		txtNeuesPW.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		
		final JPasswordField altesPW = new JPasswordField(10);
		final JPasswordField neuesPW = new JPasswordField(10);
		JButton oKButton = new JButton(stringList.getString("Change"));
		oKButton.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		JButton abbrechenButton = new JButton(stringList.getString("Cancel"));
		abbrechenButton.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		
		oKButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) 
			{
				@SuppressWarnings("deprecation")
				String altesPWEingabe = altesPW.getText();
				@SuppressWarnings("deprecation")
				String neuesPWEingabe = neuesPW.getText();
				
				try{
					vw.deser(path, altesPWEingabe, "SHA-512");
					vw.ser(path, neuesPWEingabe, "SHA-512");
					JOptionPane.showMessageDialog(null, "Passwort erfolgreich ge�ndert.\nBitte erneut einloggen.");
					dispose();
					hf.dispose();
					@SuppressWarnings("unused")
					Login l = new Login();
				}catch(Exception e1)
				{
//					JOptionPane.showMessageDialog(null, "Falsches Passwort");
				}
			}
			
		});
		
		abbrechenButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				hf.setVisible(true);
			}
			
		});
		
		panel1.add(txtAltesPW);
		panel2.add(altesPW);
		panel3.add(txtNeuesPW);
		panel4.add(neuesPW);
		panel5.add(oKButton);
		panel6.add(abbrechenButton);
		
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panel5);
		add(panel6);
		
		setVisible(true);
	}
}
