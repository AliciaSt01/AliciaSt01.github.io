package javaAirlinesMenuOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javaAirlines.*;

public class LoadFile extends MenuOption {
	@Override
	public String toString() {
		return super.toString() + "Load File";
		
	}
	
	public void executeOption() {
		String filename = FileLoc.pickLocRead();
		
		File file = new File(filename);
		
		FileInputStream fileInput = null;
		ObjectInputStream objectInput = null;
		
		try {
			fileInput = new FileInputStream(file);
			objectInput = new ObjectInputStream(fileInput);
			
			ArrayList<Serializable> list = (ArrayList<Serializable>) objectInput.readObject();
			customers = (ArrayList<Customer>) list.get(0);
			flightDates = (ArrayList<FlightDate>) list.get(1);
			planes = (ArrayList<Airplane>) list.get(2);

			objectInput.close();
			fileInput.close();
			
			System.out.println("File loaded successfully. \n");
			
		} catch (FileNotFoundException FNFE) {
			System.out.println("File not found\n");
		
		} catch (IOException IOE) {
			System.out.println("Error initializing stream\n");
		
		} catch (ClassNotFoundException CNFE) {
			System.out.println("Class not found\n");
		
		}
		
		
	}
	
}