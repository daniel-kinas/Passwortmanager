package gui;

import javax.swing.table.AbstractTableModel;

import fachkonzept.Passwort;
import fachkonzept.PasswortVerwaltung60Main;
import fachkonzept.Strings;
import fachkonzept.Verwaltung;

public class TableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 70849350235944227L;
	public static Strings stringList = PasswortVerwaltung60Main.stringList;
	private static final String[] COLHEADING = {stringList.getString("Description"),stringList.getString("URL"), stringList.getString("Username"), stringList.getString("Password")};
	private final Verwaltung liste;
	
	private int glRow = -1;
	
	public TableModel(final Verwaltung liste)
	{
		this.liste = liste;
	}
	
	public int getColumnCount() 
	{
		return COLHEADING.length;
	}

	
	public int getRowCount() 
	{
		return liste.getListe().size();
	}

	public String getColumnName(final int index)
	{
		return COLHEADING[index];
	}
	
	public Object getValueAt(int row, int col) 
	{
		Passwort a = liste.getListe().get(row);
		
		if (col == 0)
		{
			return a.getBeschreibung(); 
		}else if(col == 1)
		{
			return a.getUrl();
			
		}else if(col == 2)
		{ 
			return a.getUsername();
		}else if(col == 3)
		{
			
			if(row == glRow)
			{
				return a.getPass();
			}else{
				return "********";						
			}
		}
		
		throw new IllegalArgumentException("Ung�ltiger Index col = " + col);
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		if(columnIndex == 2)
		{
			//If password is selected
			return false;
		}else{
			//If Username or Description is selected
			return false;
		}
	    
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
	    Passwort pw = liste.getListe().get(rowIndex);
	    if(0 == columnIndex) {
	        pw.setBeschreibung((String) aValue);
	    }else if(1 == columnIndex)
	    {
	    	//pw.setUsername((String) aValue);
	    }
	    else if(2 == columnIndex) {
//	        pw.setPass((String) aValue);
	    }
	}
	
	public String getDescriptionAt(int row)
	{
		return liste.getListe().get(row).getBeschreibung();
	}
	
	public String getUsernameAt(int row)
	{
		return liste.getListe().get(row).getUsername();
	}
	
	public String getPasswordAt(int row)
	{
		return liste.getListe().get(row).getPass();
	}
	
	public String getUrlAt(int row)
	{
		return liste.getListe().get(row).getUrl();
	}
	
	public void changeLanguage()
	{
		COLHEADING[0] = stringList.getString("Description");
		COLHEADING[1] = stringList.getString("URL");
		COLHEADING[2] = stringList.getString("Username");
		COLHEADING[3] = stringList.getString("Password");
	}
	
	public void showPasswortKlartext(int row)
	{
		fireTableCellUpdated(row, 3);
		glRow = row;
	}
	
	public void hidePasswortKlartext(int row)
	{
		fireTableCellUpdated(row, 3);
		glRow = -1;
	}
	
}
