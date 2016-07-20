package fachkonzept;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import datenhaltung.Installation;

public class Strings {

//	static String configParentFolder = PasswortVerwaltung58Main.configParentFolder;
	static Installation I = PasswortVerwaltung60Main.I;

	String lang;
	String id;
	ArrayList<String> strEN;
	ArrayList<String> strDE;
	File f;
	double version;

	public Strings(double version) {
		this.version = version;
		lang = "DE";
		try {

			f = I.getLang();
			if (!f.exists()) {
				f.createNewFile();
				setLang(lang);
			}

			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			lang = br.readLine();

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		strEN = new ArrayList<String>();
		strEN.add("Open Passwordmanager");
		strEN.add("Passwordmanager");
		strEN.add("Search");
		strEN.add("Login");
		strEN.add("My Passwordmanager");
		strEN.add("Data");
		strEN.add("List");
		strEN.add("Description");
		strEN.add("Username");
		strEN.add("Password");
		strEN.add("New Passwordmanager");
		strEN.add("Open Passwordmanager");
		strEN.add("Save");
		strEN.add("Change login password");
		strEN.add("Exit");
		strEN.add("New password");
		strEN.add("Delete selected password");
		strEN.add("Create");
		strEN.add("Cancel");
		strEN.add("Do you want to save changes?");
		strEN.add("Yes");
		strEN.add("No");
		strEN.add("Created new list.");
		strEN.add("Please set login password.");
		strEN.add("Old password");
		strEN.add("New password");
		strEN.add("Change");
		strEN.add("Change language");
		strEN.add("Changes will take place after a restart");
		strEN.add("English");
		strEN.add("German");
		strEN.add("Generate");
		strEN.add("Copy to clipboard");
		strEN.add("Copied password into clipboard");
		strEN.add("Copied passwordset into clipboard");
		strEN.add("Path");
		strEN.add("Password");
		strEN.add("Numbers");
		strEN.add("Letters");
		strEN.add("Search Passwordmanager");
		strEN.add("Create new Passwordmanager");
		strEN.add("Generate random password");
		strEN.add("Error while encrypting");
		strEN.add("Error while decrypting");
		strEN.add("Wrong password");
		strEN.add("Are you sure you want to delete this password?");
		strEN.add("Open");
		strEN.add("New");
		strEN.add("Logout");
		strEN.add("About");
		strEN.add("OK");
		strEN.add("Passwortverwaltung \nVersion: " + version+"\nContact: support@dkinas.de");
		strEN.add("Special");
		strEN.add("Copy passwordset");
		strEN.add("Copy the complete passwordset for other passwordmanager");
		strEN.add("Paste passwordset");
		strEN.add("Paste the copied passwordset into passwordmanager");
		strEN.add("No Password selected");
		strEN.add("No passwordset in clipboard\n Copy a passwordset first");
		strEN.add("System");
		strEN.add("Delete visited passwordmanager");
		strEN.add("Delete list of all visited passwordmanager at login");
		strEN.add("Please enter username and password");
		strEN.add("Edit entry");
		strEN.add("Edit selected entry");
		strEN.add("URL");
		strEN.add("Copy");
		strEN.add("Copy URL");
		strEN.add("Copy username");
		strEN.add("Copy password");
		strEN.add("Copied URL into clipboard");
		strEN.add("Copied username into clipboard");
		strEN.add("Open URL in browser");
		strEN.add("No URL to open");
		strEN.add("Edit");
		strEN.add("Undo unsaved changes");
		strEN.add("Do you really want to undo unsaved changes?");
		strEN.add("Undo all unsaved changes");
		strEN.add("Edit visited passwordmanager");
		strEN.add("Edit list of visited passwordmanager with an Text Editor");
		strEN.add("Can't open file: ");
		strEN.add("Check for updates.");
		strEN.add("Error while checking for updates");
		strEN.add("No update found");
		strEN.add(".exe (recommended)");
		strEN.add(".jar");
		strEN.add("What file do you want to download?");
		strEN.add("Delete");
		strEN.add("Do you really want to clear list of recently opened manager?");
		strEN.add("What's new?");
		strEN.add("New Version");
		strEN.add("Okay");
		strEN.add("Don't show again");
		strEN.add("New version available");
		strEN.add("Something went wrong while installing!\nTry as admin.");
		strEN.add("Settings");
		strEN.add("Apply");
		strEN.add("Converted manager to: ");
		strEN.add("Convert old manager");
		strEN.add("Converts manager with lower version than 6.0");
		strEN.add("Error! File does not exist.");
		strEN.add("Enter password");
		strEN.add(" already exists");
		strEN.add("Wrong password or manager ist already using sha512.");
		// TODO

		strDE = new ArrayList<String>();
		strDE.add("Öffne Verwaltung");
		strDE.add("Passwortverwaltung");
		strDE.add("Suchen");
		strDE.add("Login");
		strDE.add("Meine Passwortliste");
		strDE.add("Datei");
		strDE.add("Verwaltung");
		strDE.add("Beschreibung");
		strDE.add("Benutzername");
		strDE.add("Passwort");
		strDE.add("Neue Verwaltung");
		strDE.add("Öffne Verwaltung");
		strDE.add("Speichern");
		strDE.add("Ändere Loginpasswort");
		strDE.add("Beenden");
		strDE.add("Neues Passwort");
		strDE.add("Lösche markiertes Passwort");
		strDE.add("Erstellen");
		strDE.add("Abbrechen");
		strDE.add("Möchten sie die Änderungen speichern?");
		strDE.add("Ja");
		strDE.add("Nein");
		strDE.add("Erstelle neue Passwortverwaltung.");
		strDE.add("Bitte vergeben sie ein Passwort.");
		strDE.add("Altes Passwort");
		strDE.add("Neues Passwort");
		strDE.add("Ändern");
		strDE.add("Sprache ändern");
		strDE.add("Änderungen werden erst nach einem Neustart aktiv");
		strDE.add("Englisch");
		strDE.add("Deutsch");
		strDE.add("Generiere");
		strDE.add("Kopiere in die Zwischenablage");
		strDE.add("Passwort wurde in die Zwischenablage kopiert");
		strDE.add("Passwortset wurde in die Zwischenablage kopiert");
		strDE.add("Pfad");
		strDE.add("Passwort");
		strDE.add("Zahlen");
		strDE.add("Buchstaben");
		strDE.add("Suche Passwortverwaltung");
		strDE.add("Erstelle neue Passwortverwaltung");
		strDE.add("Generiere zufälliges Passwort");
		strDE.add("Fehler bei der Verschlüsselung");
		strDE.add("Fehler bei der Entschlüsselung");
		strDE.add("Falsches Passwort");
		strDE.add("Sind sie sich sicher, dass sie das Passwort löschen möchten?");
		strDE.add("Öffnen");
		strDE.add("Neu");
		strDE.add("Abmelden");
		strDE.add("Über");
		strDE.add("OK");
		strDE.add("Passwortverwaltung \nVersion: " + version + "\nKontakt: support@dkinas.de");
		strDE.add("Sonderzeichen");
		strDE.add("Passwortset kopieren");
		strDE.add("Kopiere das Passwortset für eine andere Passwortverwaltung");
		strDE.add("Passwortset einfügen");
		strDE.add("Füge das kopierte Passwortset in die Passwortverwaltung ein");
		strDE.add("Kein Passwort ausgewählt");
		strDE.add("Kein Passwortset in der Zwischenablage\n Kopiere zuerst ein Passwortset");
		strDE.add("System");
		strDE.add("Lösche letzte Passwortverwaltungen");
		strDE.add("Lösche die Liste der zuletzt geöffneten Passwortverwaltungen beim Login");
		strDE.add("Bitte Benutzername und Passwort ausfüllen");
		strDE.add("Eintrag ändern");
		strDE.add("Bearbeite den markierten Eintrag");
		strDE.add("URL");
		strDE.add("Kopieren");
		strDE.add("Kopiere URL");
		strDE.add("Kopiere Benutzername");
		strDE.add("Kopiere Passwort");
		strDE.add("URL wurde in die Zwischenablage kopiert");
		strDE.add("Benutzername wurde in die Zwischenablage kopiert");
		strDE.add("Öffne URL im Browser");
		strDE.add("Keine URL zum Öffnen");
		strDE.add("Ändern");
		strDE.add("Setze ungespeicherte Änderungen zurück");
		strDE.add("Sind sie sicher, dass sie alle ungespeicherten Änderungen rückgängig machen wollen?");
		strDE.add("Setze alle ungespeicherten Änderungen zurück");
		strDE.add("Bearbeite Liste geöffneter Passwortverwaltungen");
		strDE.add("Bearbeite die Liste der zuletzt geöffneten Passwortverwaltungen mit einem Text Editor");
		strDE.add("Datei kann nicht geöffnet werden: ");
		strDE.add("Neue Programmversion suchen.");
		strDE.add("Fehler beim suchen von Updates");
		strDE.add("Version ist aktuell");
		strDE.add(".exe (Empfohlen)");
		strDE.add(".jar");
		strDE.add("Welche Datei willst du herunterladen?");
		strDE.add("Löschen");
		strDE.add("Wollen sie wirklich die Liste der zuletzt geöffneten Verwaltungen löschen?");
		strDE.add("Was ist neu?");
		strDE.add("Neue Version");
		strDE.add("Okay");
		strDE.add("Nicht mehr anzeigen");
		strDE.add("Neue Version verfügbar");
		strDE.add("Etwas ist bei der Installation schief gelaufen!\nVersuchen sie es als Administrator.");
		strDE.add("Einstellungen");
		strDE.add("Übernehmen");
		strDE.add("Verwaltung konvertiert zu: ");
		strDE.add("Konvertiere alte Verwaltung.");
		strDE.add("Konvertiere Verwaltungen, die älter als V.6.0 sind.");
		strDE.add("Error! Datei existiert nicht.");
		strDE.add("Passwort eingeben");
		strDE.add(" existiert bereits");
		strDE.add("Falsches Passwort oder Verwaltung nutzt bereits sha512.");
		// TODO
		// Add here more Languages

	}

	public String getString(String id) {
		if (lang.equals("EN")) {
			for (int i = 0; i < strEN.size(); i++) {
				if (strEN.get(i).equals((String) id)) {
					return strEN.get(i);
				}
			}

		}

		if (lang.equals("DE")) {

			for (int i = 0; i < strEN.size(); i++) {
				if (strEN.get(i).equals((String) id)) {
					return strDE.get(i);
				}
			}
		}

		// and here

		return "error";
	}

	public void setLang(String lang) {
		try {
			FileWriter fw = new FileWriter(I.getLang());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(lang);

			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.lang = lang;
	}

	public String getChangelog() {
		return "Changelog:\n"
				+ "- Create every file needed at first startup(installation like)\n"
				+ "- Application is now platform independent(Windows, Linux, Mac)\n"
				+ "- Change file extension for config to .ini, lang, lastVisited and manList to txt\n"
				+ "- Start search for update in different thread\n";
	}

	public double getVersion() {
		return version;
	}

}
