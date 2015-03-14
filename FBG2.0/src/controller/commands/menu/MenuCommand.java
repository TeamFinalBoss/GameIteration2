package controller.commands.menu;

import controller.commands.Commandable;
import controller.menu.Menu;

/**
 * @author Kyle Kyrazis
 * 
 * Super class for all MenuCommands. Maintains a reference to 
 * a menu and does children reference these.
 *
 */
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
