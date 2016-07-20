package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import fachkonzept.Passwort;
import fachkonzept.PasswortVerwaltung60Main;
import fachkonzept.Strings;
import fachkonzept.Verwaltung;

public class AnlegenView extends JFrame
{
	private static final long serialVersionUID = 70849350235944227L;
	Verwaltung vw;
	JTextField beschreibungInput;
	JTextField passwortInput;
	JTextField usernameInput;
	JTextField urlInput;
	JComboBox<Integer> laengePasswort;
	JCheckBox buchstabenCheck;
	JCheckBox zahlenCheck;
	JCheckBox sonderCheck;
	Hauptfenster hf;
	
	boolean edit = false;
	int oldRow;
	
	private TableModel tableModel;
	File f;
	static Strings stringList = PasswortVerwaltung60Main.stringList;
	
	public AnlegenView(Verwaltung vw, TableModel tableModel, Hauptfenster hf)
	{
		super(stringList.getString("New password"));
		this.vw = vw;
		this.tableModel = tableModel;
		this.hf = hf;
		
		
		initGUI(0);
		beschreibungInput.requestFocus();
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public AnlegenView(Verwaltung vw, TableModel tableModel, Hauptfenster hf,int oldRow, String description, String username, String password, String url)
	{
		super(stringList.getString("New password"));
		this.vw = vw;
		this.tableModel = tableModel;
		this.hf = hf;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		initGUI(1);
		
		edit = true;
		this.oldRow = oldRow;
		
		beschreibungInput.setText(description);
		usernameInput.setText(username);
		passwortInput.setText(password);
		urlInput.setText(url);
		
		beschreibungInput.requestFocus();
	}
	
	public void initGUI(int con)
	{
		setSize(450,350);
		
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int)((d.width - getSize().width) /2);
		int y = (int)((d.height - getSize().height) /2);
		
		setLocation(x,y);
		
		
		setResizable(false);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.menu);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JLabel beschreibungTxt = new JLabel(stringList.getString("Description"));
		beschreibungTxt.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		beschreibungTxt.setBounds(10, 10, 125, 20);
		desktopPane.add(beschreibungTxt);
		
		JLabel passwortTxt = new JLabel(stringList.getString("Password"));
		passwortTxt.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		passwortTxt.setBounds(10, 130, 125, 20);
		desktopPane.add(passwortTxt);
		
		JLabel usernameTxt = new JLabel(stringList.getString("Username"));
		usernameTxt.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		usernameTxt.setBounds(10, 90, 125, 20);
		desktopPane.add(usernameTxt);
		
		JLabel urlTxt = new JLabel(stringList.getString("URL"));
		urlTxt.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		urlTxt.setBounds(10, 50, 125, 20);
		desktopPane.add(urlTxt);
		
		beschreibungInput = new JTextField();
		beschreibungInput.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		beschreibungInput.setBounds(145, 10, 264, 20);
		desktopPane.add(beschreibungInput);
		beschreibungInput.setColumns(10);
		
		passwortInput = new JTextField();
		passwortInput.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		passwortInput.setBounds(145, 130, 264, 20);
		desktopPane.add(passwortInput);
		passwortInput.setColumns(10);		
		
		usernameInput = new JTextField();
		usernameInput.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		usernameInput.setBounds(145, 90, 264, 20);
		desktopPane.add(usernameInput);
		usernameInput.setColumns(10);
		
		urlInput = new JTextField();
		urlInput.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		urlInput.setBounds(145, 50, 264, 20);
		desktopPane.add(urlInput);
		urlInput.setColumns(10);
		
		JButton zufall = new JButton(stringList.getString("Generate"));
		zufall.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		zufall.setBounds(314, 178, 110, 28);
		desktopPane.add(zufall);
		zufall.setToolTipText(stringList.getString("Generate random password"));
		
		laengePasswort = new JComboBox<Integer>();
		laengePasswort.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 15));
		laengePasswort.setBounds(246, 178, 58, 28);
		desktopPane.add(laengePasswort);
		
		zahlenCheck = new JCheckBox(stringList.getString("Numbers"));
		zahlenCheck.setSelected(true);
		zahlenCheck.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 15));
		zahlenCheck.setBounds(10, 178, 110, 23);
		desktopPane.add(zahlenCheck);
		
		buchstabenCheck = new JCheckBox(stringList.getString("Letters"));
		buchstabenCheck.setSelected(true);
		buchstabenCheck.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 15));
		buchstabenCheck.setBounds(119, 178, 110, 23);
		desktopPane.add(buchstabenCheck);
		
		sonderCheck = new JCheckBox(stringList.getString("Special"));
		sonderCheck.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 15));
		sonderCheck.setBounds(10, 204, 219, 23);
		desktopPane.add(sonderCheck);
		
		
		
		JButton ok = new JButton();
		ok.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		ok.setBounds(74, 244, 120, 50);
		desktopPane.add(ok);
		
		KeyStroke ks1 = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0); 
		
		OkListener okListener = new OkListener();
		ok.setAction(okListener);
		ok.getActionMap().put("performLogin", okListener);
		ok.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks1, "performLogin"); 
		
		if(con == 0)
		{
			ok.setText(stringList.getString("Create"));
		}else{
			ok.setText(stringList.getString("Edit"));
		}
		
		
		JButton abbruch = new JButton(stringList.getString("Cancel"));
		abbruch.setFont(new Font("Segoe WP SemiLight", Font.PLAIN, 17));
		abbruch.setBounds(257, 244, 120, 50);
		desktopPane.add(abbruch);
		
		
		
		for(int i = 4;i<=512;i++)
		{
			laengePasswort.addItem(i);
		}
		
		
		//ok.addActionListener(new OkListener());
		abbruch.addActionListener(new abbruchListener());
		zufall.addActionListener(new zufallListener());
		
		
		setVisible(true);
	}
	
	@SuppressWarnings("serial")
	public class OkListener extends AbstractAction
	{

		public void actionPerformed(ActionEvent e) 
		{
			String beschreibung = beschreibungInput.getText();
			String passwort = passwortInput.getText();
			String username = usernameInput.getText();
			String url = urlInput.getText();
			
			if(passwort.isEmpty() || username.isEmpty())
			{
				try {
					throw new EmptyFileNameException();
				} catch (EmptyFileNameException e1) {
					JOptionPane.showMessageDialog(null, stringList.getString("Please enter username and password"));
					if(username.isEmpty())
					{
						usernameInput.requestFocus();
					} else if(passwort.isEmpty())
					{
						passwortInput.requestFocus();
					}
					e1.printStackTrace();
				}
			} else
			{
				hf.setChange(true);
				Passwort pw = new Passwort(beschreibung,passwort, username, url);
				
				if(edit == true)
				{
					vw.getListe().remove(oldRow);
				}
				
				vw.addPasswort(pw);
				
				hf.switchButtonState(false);
				tableModel.fireTableDataChanged();
				setVisible(false);
				dispose();
			}
			
			
		}
	}
	
	public class abbruchListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			setVisible(false);
			dispose();
		}
	}
	
	public class zufallListener implements ActionListener
	{
		Random r = new Random();
		
		public void actionPerformed(ActionEvent e)
		{
			String zufallPasswort = "";
			String zufallZeichen = "";
			
			for(int i = 0; i< (Integer) laengePasswort.getSelectedItem(); i++)
			{				
				//Case 1 letter, number and spec are selected
				// X X X
				if(buchstabenCheck.isSelected() && zahlenCheck.isSelected() && sonderCheck.isSelected())
				{
					int temp = r.nextInt(3);
					if(temp == 0)
					{
						// Hier Buchstaben
						zufallZeichen = getLetter();
					}
					
					if(temp == 1)
					{
						// Hier zahlen
						zufallZeichen = getNumber();
					}
					
					if(temp == 2)
					{
						zufallZeichen = getSpecialCharacter();
					}
				}
				
				
				//Case 2 only letter is selected
				// X 0 0
				if(buchstabenCheck.isSelected() && !zahlenCheck.isSelected() && !sonderCheck.isSelected())
				{
					// Hier Buchstaben
					zufallZeichen = getLetter();
				}
				
				
				//Case 3 only number is selected
				// 0 X 0
				if(!buchstabenCheck.isSelected() && zahlenCheck.isSelected() && !sonderCheck.isSelected())
				{
					// Hier zahlen
					zufallZeichen = getNumber();
				}
				
				//Case 4 only spec is selected
				// 0 0 X
				if(!buchstabenCheck.isSelected() && !zahlenCheck.isSelected() && sonderCheck.isSelected())
				{
					// Hier zahlen
					zufallZeichen = getSpecialCharacter();
				}
				
				//Case 5 letter and spec are selected
				// X 0 X
				if(buchstabenCheck.isSelected() && !zahlenCheck.isSelected() && sonderCheck.isSelected())
				{
					if(r.nextBoolean())
					{
						//Hier Buchstaben
						zufallZeichen = getLetter();
					}else{
						//Hier Sonderzeichen
						zufallZeichen = getSpecialCharacter();
					}
				}
				
				//Case 6 letter and number are selected
				// X X 0
				if(buchstabenCheck.isSelected() && zahlenCheck.isSelected() && !sonderCheck.isSelected())
				{
					if(r.nextBoolean())
					{
						//Hier Buchstaben
						zufallZeichen = getLetter();
					}else{
						//Hier Zahlen
						zufallZeichen = getNumber();
					}
				}
				
				//Case 7 number and spec are selected
				// 0 X X
				if(!buchstabenCheck.isSelected() && zahlenCheck.isSelected() && sonderCheck.isSelected())
				{
					if(r.nextBoolean())
					{
						//Hier Zahlen
						zufallZeichen = getNumber();
					}else{
						//Hier Sonderzeichen
						zufallZeichen = getSpecialCharacter();
					}
				}
				
				
				zufallPasswort = zufallPasswort.concat(zufallZeichen);
			}
			
			passwortInput.setText(zufallPasswort);		
		}
		
		public String getLetter()
		{
			// Hier Buchstaben
			if(r.nextBoolean())
			{
				// Hier Gro�buchstaben
				return Character.toString((char) (r.nextInt(26) + 65));
			} else{
				// Hier Kleinbuchstaben
				return Character.toString((char) (r.nextInt(26) + 97));
			}
		}
		
		public String getNumber()
		{
			// Hier Zahlen
			return Integer.toString(r.nextInt(10));
		}
		
		public String getSpecialCharacter()
		{
			// Hier Sonderzeichen
			return Character.toString((char) (r.nextInt(15) + 33));
		}
	}
	
}
