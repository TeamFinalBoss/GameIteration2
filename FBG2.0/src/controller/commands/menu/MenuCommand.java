package controller.commands.menu;

import controller.commands.Commandable;
import controller.menu.Menuable;

/**
 * @author Kyle Kyrazis
 * 
 * Super class for all MenuCommands. Maintains a reference to 
 * a menu and does children reference these.
 *
 */
public abstract class MenuCommand implements Commandable {
	private Menuable menu;
	
	public MenuCommand(Menuable menu) {
		this.menu = menu;
	}
	
	protected Menuable getMenu() {
		return this.menu;
	}
	
	public abstract void execute();
}
