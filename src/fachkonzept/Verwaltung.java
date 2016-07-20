package fachkonzept;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import datenhaltung.Speicherung;

public class Verwaltung implements Serializable
{
//	private int anzahl;
//	private Login login;
	private static final long serialVersionUID = 70849350235944227L;
	
	Speicherung sp = new Speicherung(this);
	
	private ArrayList<Passwort> liste;
	
	public Verwaltung()
	{
		liste = new ArrayList<Passwort>();
//		anzahl = 0;
//		login = new Login(null);
	}
	
	public void addPasswort(Passwort pw)
	{
		liste.add(pw);
//		anzahl++;
	}
	
	public void removePasswort(int i)
	{
		liste.remove(i);
//		anzahl--;
	}
	
	/*
	public void druckeDaten()
	{
		for(Passwort pw: liste)
		{
			pw.druckeDaten();
		}
	}*/
	
	public Iterator<Passwort> iterator()
	{
		return liste.iterator();
	}
	
	public void ser(File f, String pass, String mode)
	{
		sp.serPasswoerter(f, pass, mode);
	}

	public Verwaltung deser(File f, String pass, String mode)
	{
		return sp.deserPasswoerter(f, pass, mode);
	}
	
	public void convert(File f, String pass)
	{
		sp.convertMD5ToSHA(f, pass);
	}
	
	public ArrayList<Passwort> getListe()
	{
		return liste;
	}	
	
//	public void aendereLogin(String l)
//	{
//		login.aenderLogin(l);
//	}
	
//	public Login getLogin()
//	{
//		return login;
//	}
	
	public void neueVerwaltung()
	{
		liste.clear();
	}
}
