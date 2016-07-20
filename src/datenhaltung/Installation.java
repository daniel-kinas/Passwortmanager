package datenhaltung;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import fachkonzept.PasswortVerwaltung60Main;
import fachkonzept.Strings;

public class Installation 
{
	static Strings stringList = PasswortVerwaltung60Main.stringList;
	File f;
	
	File lang;
	File manList;
	File lastVisited;
	File config;
	
	public Installation(File f)
	{
		this.f = f;
		
		lang = new File(f + File.separator + "lang.txt");
		manList = new File(f + File.separator + "manList.txt");
		lastVisited = new File(f + File.separator + "lastVisited.txt");
		config = new File(f + File.separator + "config.ini");
	}
	
	public boolean install()
	{
		try{
			if(!f.exists())
			{
				f.mkdir();
				f.setWritable(true, false);
				
				
				lang.createNewFile();
				lang.setWritable(true, false);
				FileWriter fw = new FileWriter(lang);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("DE");

				bw.close();
				
				
				manList.createNewFile();
				manList.setWritable(true, false);
				
				lastVisited.createNewFile();
				lastVisited.setWritable(true, false);
				
				
				config.createNewFile();
				config.setWritable(true, false);;
				FileWriter fw2 = new FileWriter(config);
				BufferedWriter bw2 = new BufferedWriter(fw2);
				bw2.write("CheckForNewVersion :" + 1 + ";");
				
				bw2.close();
				
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Something went wrong while installing\nTry as admin.!");
			e.printStackTrace();
			System.exit(0);
		}
		return true;
	}
	
	public File getLang()
	{
		return lang;
	}
	
	public File getManList()
	{
		return manList;
	}
	
	public File getLastVisited()
	{
		return lastVisited;
	}
	
	public File getConfig()
	{
		return config;
	}
}
