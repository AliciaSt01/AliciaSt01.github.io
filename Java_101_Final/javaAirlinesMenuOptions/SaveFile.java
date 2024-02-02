package javaAirlinesMenuOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javaAirlines.Airplane;
import javaAirlines.Customer;
import javaAirlines.FileLoc;
import javaAirlines.FlightDate;
import javaAirlinesMenu.Menu;

public class SaveFile extends MenuOption {
	@Override
	public String toString() {
		return super.toString() + "Save File";
		
	}
	
	public void executeOption() {
		String filename = FileLoc.pickLocOut();
		
		File file = new File(filename);
		
		try {
		FileOutputStream fileOutput = new FileOutputStream(filename);
		ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
		
		ArrayList<Customer> cust = new ArrayList<Customer>(customers);
		ArrayList<FlightDate> dates = new ArrayList<FlightDate>(flightDates);
		ArrayList<Airplane> airplanes = new ArrayList<Airplane>(planes);
		
		ArrayList<Serializable> list = new ArrayList<Serializable>();
		list.add(cust);
		list.add(dates);
		list.add(airplanes);
		
		objectOutput.writeObject(list);
		
		objectOutput.close();
		fileOutput.close();
		
		System.out.println("File read successfully \n");
		
		} catch (FileNotFoundException FNFE) {
			System.out.println("File not found \n");
			
		} catch (IOException IOE) {
			System.out.println("Error initializing stream \n");
			
		}
		
	}
	
}