package javaAirlinesMenu;

import javaAirlinesMenuOptions.*;

public class FileMenu extends Menu {
	//Menu for File options
	public void loadOptions() {
		menuOptions.add(new SaveFile());
		menuOptions.add(new LoadFile());
		
	}
	
}
