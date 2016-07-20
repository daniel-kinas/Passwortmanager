package fachkonzept;

import java.io.Serializable;

public class Passwort implements Serializable
{
	String beschreibung;
	String username;
	String pass;
	String url;
	
	static Strings stringList = PasswortVerwaltung60Main.stringList;
	
	
	private static final long serialVersionUID = 70849350235944227L;
	
	public Passwort(String beschreibung, String pass, String username, String url)
	{
		this.beschreibung = beschreibung;
		this.pass = pass;
		this.username = username;
		this.url = url;
	}
	
	public void setBeschreibung(String beschreibung)
	{
		this.beschreibung = beschreibung;
	}
	
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public String getBeschreibung()
	{
		return this.beschreibung;
	}
	
	public String getPass()
	{
		return this.pass;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public String getUrl()
	{
		return this.url;
	}
	
	/*
	public void druckeDaten()
	{
		System.out.println(beschreibung + " Username: " + username + " Passwort: " + pass);
	}
	
	public String toString()
	{
		String s = new String("Nutzername: " + beschreibung + " Username: " + username + " Passwort: " + pass);
		return s;
	}
*/
}
