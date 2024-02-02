package javaAirlinesMenuOptions;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import javaAirlines.FlightDate;

public class AddFlightDate extends MenuOption {
	@Override
	public String toString() {
		return super.toString() + "Add a new date for flights";
		
	}
	
	//Used to add a FlightDate object
	public void executeOption() {
		Scanner scan = new Scanner(System.in);
		LocalDate date;
		
		//Gets and checks if date is valid
		do {
			
			while (true) {
				try {
					//Gets date
					System.out.print("Enter date to add in YYYY-MM-DD format: ");
					date = LocalDate.parse(scan.nextLine());
					System.out.println();
					break;
					
				} catch (Exception E) {
					System.out.println("\nMust enter a valid date in YYYY-MM-DD format \n");
					
				}
			
			}
		
		} while(checkDateTaken(date));
		//Adds date
		flightDates.add(new FlightDate(date));
		return;
		
	}
	
	//Checks if Date is already created
	public boolean checkDateTaken(LocalDate date) {
		//Loops through flightDates comparing date
		for (int i = 0; i < flightDates.size();i++) {
			if (date.equals(flightDates.get(i).getDate())) {
				System.out.println("Date entered is already in the system \n");
				return true;
				
			}
			
		}
		return false;
		
	}

}
