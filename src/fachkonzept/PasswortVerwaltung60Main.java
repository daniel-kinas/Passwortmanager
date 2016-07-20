package fachkonzept;

import gui.Login;
import gui.StartupNewVersion;

import java.io.File;

import datenhaltung.Installation;

/* 
 * Changelog:
 * - WIP Add SettingsGUI
 * - WIP Redesign complete application(Everything happens in one Window but different frames)
 * - WIP Automatic logoff after a certain amount of time has passed.
 * - Changed hash method from MD5 to SHA-512
 * - Fixed installation for Linux
 * - Add conversion for md5 to sha512 manager
 * - WIP Error message if manager not found
*/

public class PasswortVerwaltung60Main 
{
	public static Strings stringList;
	public static Installation I;
	public static String configParentFolder = System.getenv("APPDATA");
	public static void main(String[] args)
	{
		
//		System.out.println(System.getProperty("user.home"));
		File f;
		if(configParentFolder == null || !System.getProperty("os.name").contains("Windows"))
		{
			f = new File(System.getProperty("user.home") + File.separator + ".Passwordmanager");
		}else{
			f = new File(configParentFolder + File.separator + "Passwordmanager");
		}
		
		I = new Installation(f);
		
		if(!f.exists())
		{
			I.install();
		}
		stringList = new Strings(6.0);	
		
		new Login();
		new Thread(){
            public void run() 
            {
            	new StartupNewVersion();
            }
        }.start();
	}
}
