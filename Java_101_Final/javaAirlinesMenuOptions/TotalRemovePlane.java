package javaAirlinesMenuOptions;

import javaAirlines.Airplane;

public class TotalRemovePlane extends MenuOption {
	@Override
	public String toString() {
		return super.toString() + "Remove airplane with cancellation of all flights (To be used for emergencies)";
	}
	
	public void executeOption() {
		if (planes.size() == 0) {
			System.out.println("There are no planes in the system");
			return;
			
		}
		
		//Gets airplane
		Airplane plane = selectAirplane();
		
		//Removes plane from inventory
		removeAirplane(plane);
		
		//Removes all flights that involve this airplane
		for (int i = 0; i < flightDates.size(); i++) {
			for (int j = 0; j < flightDates.get(i).getFlights().size(); j++) {
				if (plane.getName().equals(flightDates.get(i).getFlights().get(j).getPlane().getName())) {
					flightDates.get(i).removeFlight(flightDates.get(i).getFlights().get(j));
					j--;
					
				}
				
			}
			
		}
		System.out.println("Airplane has been removed \n");
		return;
		
	}

}
