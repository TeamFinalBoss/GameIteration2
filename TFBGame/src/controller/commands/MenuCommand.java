package controller.commands;

import controller.menu.Menu;

public abstract class MenuCommand implements Commandable {
	private Menu menu;
	
	public MenuCommand(Menu menu) {
		this.menu = menu;
	}
	
	protected Menu getMenu() {
		return this.menu;
	}
	
	public abstract void execute();
}
