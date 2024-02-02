package javaAirlinesMenu;

import javaAirlinesMenuOptions.*;

public class FlightMenu extends Menu{
	//Menu for Editing Flights
	public void loadOptions() {
		menuOptions.add(new CreateFlight());
		menuOptions.add(new RescheduleFlight());
		menuOptions.add(new CancelFlight());
		
	}
	
	
}
