package javaAirlinesMenuOptions;

import javaAirlinesMenu.FileMenu;

public class FileMenuOption extends MenuOption {
	@Override 
	public String toString() {
		return super.toString() + "Save and Load File";
		
	}
	
	public void executeOption() {
		FileMenu menu = new FileMenu();
		menu.chooseOption();
		
	}
	
}
