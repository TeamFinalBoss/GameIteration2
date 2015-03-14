package controller.commands.keyBindings.menu;

import controller.commands.Commandable;
import controller.menu.keyBindings.KeyBindingsMenu;

/**
 * @author Kyle Kyrazis
 * 
 * Super class for maintaing the current selection
 *
 */
public abstract class KeyBindingsMenuCommand implements Commandable {
	private KeyBindingsMenu bindingsMenu;
	
	public KeyBindingsMenuCommand(KeyBindingsMenu bindingsMenu) {
		this.bindingsMenu = bindingsMenu;
	}
	
	protected KeyBindingsMenu getBindingsMenu() {
		return this.bindingsMenu;
	}
	
	public abstract void execute();
}
