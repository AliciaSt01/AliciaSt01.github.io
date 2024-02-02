package javaAirlinesMenuOptions;

import javaAirlines.Flight;
import javaAirlines.FlightDate;

public class CancelFlight extends MenuOption {
	
	@Override
	public String toString() {
		return super.toString() + "Cancel Flight";
		
	}
	
	//Cancels Flight
	public void executeOption() {
			//Checks if any dates exist
			if (flightDates.size() == 0) {
				System.out.println("There are no dates in the system \n");
				return;
				
			}
			
			//Checks if any Flights exist
			boolean availableFlight = false;
			for (int i = 0; i < flightDates.size(); i++) {
				FlightDate date = flightDates.get(i);
				if (date.getFlights().size() > 0) {
					availableFlight = true;
					break;
					
				}
				
			}
			
			if (availableFlight == false) {
				System.out.println("There are no available flights in the system \n");
				return;
				
			}
		
			//Gets flight date
			FlightDate date = selectFlightDate();
			
			//Gets flight
			Flight flight = date.selectFlight();
			
			//Removes flight
			date.removeFlight(flight);
			System.out.println("Flight has been canceled");
			
			return;
		
	}

}
