package javaAirlinesMenuOptions;

import javaAirlines.Flight;
import javaAirlines.FlightDate;

public class RescheduleFlight extends MenuOption {
	@Override
	public String toString() {
		return super.toString() + "Reschedule Flight";
		
	}
	
	public void executeOption() {
		
		if (flightDates.size() == 0) {
			System.out.println("There are no dates in the system \n");
			return;
			
		}
		
		boolean availableFlight = false;
		for (int i = 0; i < flightDates.size(); i++) {
			FlightDate date = flightDates.get(i);
			if (date.getFlights().size() > 0) {
				availableFlight = true;
				break;
				
			}
			
		}
		
		//Get date of flight to move
		FlightDate date = selectFlightDate();
		
		//Get flight
		Flight flight = date.selectFlight();
		
		//Remove flight from old date
		date.removeFlight(flight);
		System.out.println("Flight has been removed from old date. \n");
		
		//Get new date
		date = selectFlightDate();
		
		//Add flight to new date
		date.addFlight(flight);
		System.out.println("Flight has been rescheduled \n");
		
		return;
		
	}
	
}
