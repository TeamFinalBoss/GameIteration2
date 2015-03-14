package controller.commands.keyBindings.menu;

import controller.menu.keyBindings.KeyBindingsMenu;

/**
 * @author Kyle Kyrazis
 * 
 * Used to advance the cursor to the next option in the KeyBindings Menu
 *
 */
public class NextBindingsMenuCommand extends KeyBindingsMenuCommand {

	public NextBindingsMenuCommand(KeyBindingsMenu bindingsMenu) {
		super(bindingsMenu);
	}

	@Override
	public void execute() {
		super.getBindingsMenu().next();
	}

}
