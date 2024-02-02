package javaAirlinesMenu;

import javaAirlinesMenuOptions.*;

public class AirplaneMenu extends Menu {
	//Menu for manipulating Airplane inventory
	public void loadOptions() {
		menuOptions.add(new AddPlane());
		menuOptions.add(new RemovePlane());
		menuOptions.add(new TotalRemovePlane());
		
	}

}
