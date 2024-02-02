package javaAirlinesMenu;

import javaAirlinesMenuOptions.*;

public class ReservationMenu extends Menu {
	//Menu for Editing Reservations
	public void loadOptions() {
		menuOptions.add(new NewReservation());
		menuOptions.add(new CancelReservation());
		
	}
	
}