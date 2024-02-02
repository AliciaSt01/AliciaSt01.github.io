package javaAirlinesMenuOptions;

import java.util.Scanner;

import javaAirlines.Flight;
import javaAirlines.FlightDate;

public class PlaneLoad extends MenuOption {
	@Override
	public String toString() {
		return super.toString() + "See Plane Load";
		
	}
	
	public void executeOption() {
		while (true) {
			if (flightDates.size() == 0) {
				System.out.println("There are no dates available to view \n");
				break;
				
			}
			
			boolean flightAvailable = false;
			for (int i = 0; i < flightDates.size(); i++) {
				if (flightDates.get(i).getFlights().size() > 0) {
					flightAvailable = true;
					break;
					
				}
				
			}
			
			if (flightAvailable == false) {
				System.out.println("There are no flights available \n");
				break;
				
			}
			
			//Gets and checks date of flight
			FlightDate date;
			while (true) {
				date = selectFlightDate();
			
				//Checks if any flights available
				if (date.getFlights().size() == 0) {
					System.out.println("There are no flights available on that date \n");
					continue;
					
				}
				break;
				
			}
			
			Flight flight = date.selectFlight();
			flight.getPlane().printPlaneLoad();
			Scanner scan = new Scanner(System.in);
			System.out.println("Press enter to return to main menu");
			scan.nextLine();
			System.out.println();
			break;
		
		}
		
	}

}
