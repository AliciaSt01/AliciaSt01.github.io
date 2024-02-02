package javaAirlines;

import java.awt.FileDialog;
import java.awt.Frame;


public class FileLoc {
	
	public static String pickLocOut() {
		Frame f = new Frame();
		// decide from where to read the file
		FileDialog foBox = new FileDialog(f, "Pick location for writing your file", FileDialog.SAVE);
		System.out.println("The dialog box might appear behind Eclipse.  " + 
			      "\n   Choose where you would like to write your data.");
		foBox.setVisible(true);
		// get the absolute path to the file
		String foName = foBox.getFile();
		String dirPath = foBox.getDirectory();
		String name = dirPath + foName;
		
		return name;
	}
	
	public static String pickLocRead() {
		Frame f = new Frame();
		// decide from where to read the file
		FileDialog fiBox = new FileDialog(f, "Pick location for writing your file", FileDialog.LOAD);
		System.out.println("The dialog box might appear behind Eclipse.  " + 
			      "\n   Choose where you would like to read for your data.");
		fiBox.setVisible(true);
		// get the absolute path to the file
		String fiName = fiBox.getFile();
		String dirPath = fiBox.getDirectory();

		// create a file instance for the absolute path
		String name = dirPath + fiName;
		return name;
	}

}
