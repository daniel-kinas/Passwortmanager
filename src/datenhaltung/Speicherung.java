package datenhaltung;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import fachkonzept.PasswortVerwaltung60Main;
import fachkonzept.Strings;
import fachkonzept.ToastMessage;
import fachkonzept.Verwaltung;

public class Speicherung implements Serializable
{
	Verwaltung vw;
	File f;
	String keyString;
	private static final long serialVersionUID = 70849350235944227L;
	
	static Strings stringList = PasswortVerwaltung60Main.stringList;
	
	public Speicherung(Verwaltung vw)
	{
		this.vw = vw;
	}
	
	public void serPasswoerter(File f, String input, String mode)
	{
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		CipherOutputStream cos = null;	
		
		Cipher c = null;
		try {
			byte[] key = (input).getBytes("UTF-8");
			
			MessageDigest sha = MessageDigest.getInstance(mode);
			key = sha.digest(key);
			
			key = Arrays.copyOf(key, 16);
			
			SecretKey aesKey = new SecretKeySpec(key, "AES");
			
			c = Cipher.getInstance("AES");
			
			c.init(Cipher.ENCRYPT_MODE, aesKey);
		
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, stringList.getString("Error while encrypting"));
			e1.printStackTrace();
			System.exit(0);
		}
		
		try
		{
			this.f = f;
			fos = new FileOutputStream(f);
			cos = new CipherOutputStream(fos,c);
			oos = new ObjectOutputStream(cos);
			
			oos.writeObject(vw);
			
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				oos.close();
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public Verwaltung deserPasswoerter(File f, String input, String mode)
	{		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		CipherInputStream cis = null;	
		
		Cipher c = null;
		try {
			byte[] key = (input).getBytes("UTF-8");
			
			MessageDigest sha = MessageDigest.getInstance(mode);
			key = sha.digest(key);
			
			key = Arrays.copyOf(key, 16);
			
			SecretKey aesKey = new SecretKeySpec(key, "AES");
			
			
			c = Cipher.getInstance("AES");
			
			c.init( Cipher.DECRYPT_MODE, aesKey );
		
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, stringList.getString("Error while decrypting"));
			e1.printStackTrace();
			System.exit(0);
		}
		
		try
		{
			this.f = f;
			fis = new FileInputStream(f);
			cis = new CipherInputStream(fis, c);
			ois = new ObjectInputStream(cis);
			vw = (Verwaltung) ois.readObject();
		} catch (IOException e)
		{
			e.printStackTrace();
//			JOptionPane.showMessageDialog(null, "Fehler beim Laden der Verwaltung.");
			JOptionPane.showMessageDialog(null, stringList.getString("Wrong password"));
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				ois.close();
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return vw;
	}
	
	public void convertMD5ToSHA(File f, String input)
	{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		CipherInputStream cis = null;	
		
		Cipher c = null;
		try {
			byte[] key = (input).getBytes("UTF-8");
			
			MessageDigest sha = MessageDigest.getInstance("MD5");
			key = sha.digest(key);
			
			key = Arrays.copyOf(key, 16);
			
			SecretKey aesKey = new SecretKeySpec(key, "AES");
			
			
			c = Cipher.getInstance("AES");
			
			c.init( Cipher.DECRYPT_MODE, aesKey );
		
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, stringList.getString("Error while decrypting"));
			e1.printStackTrace();
			System.exit(0);
		}
		
		try
		{
			fis = new FileInputStream(f);
			cis = new CipherInputStream(fis, c);
			ois = new ObjectInputStream(cis);
			vw = (Verwaltung) ois.readObject();
		} catch (IOException e)
		{
			e.printStackTrace();
//			JOptionPane.showMessageDialog(null, "Fehler beim Laden der Verwaltung.");
			JOptionPane.showMessageDialog(null, stringList.getString("Wrong password or manager ist already using sha512."));
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				ois.close();
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		//Save again
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		CipherOutputStream cos = null;	
		
		Cipher c2 = null;
		try {
			byte[] key = (input).getBytes("UTF-8");
			
			MessageDigest sha = MessageDigest.getInstance("SHA-512");
			key = sha.digest(key);
			
			key = Arrays.copyOf(key, 16);
			
			SecretKey aesKey = new SecretKeySpec(key, "AES");
			
			c2 = Cipher.getInstance("AES");
			
			c2.init(Cipher.ENCRYPT_MODE, aesKey);
		
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, stringList.getString("Error while encrypting"));
			e1.printStackTrace();
			System.exit(0);
		}
		File fout;
		try
		{
			String cvtFile = f.getParent() + File.separator + "sha." + f.getName();
			
			fout = new File(cvtFile);
			if(!fout.exists())
			{
				fos = new FileOutputStream(fout);
				cos = new CipherOutputStream(fos,c2);
				oos = new ObjectOutputStream(cos);
				
				oos.writeObject(vw);
				
				JOptionPane.showMessageDialog(null, stringList.getString("Converted manager to: ") + cvtFile);
			}else{
				new ToastMessage(cvtFile + stringList.getString(" already exists"), 3000).setVisible(true);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				oos.close();
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}
