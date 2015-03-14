package controller.commands.keyBindings.menu;

import controller.menu.keyBindings.KeyBindingsMenu;

/**
 * @author Kyle Kyrazis
 * 
 * This class executes the active selection in the KeyBindingsMenu
 * (e.g. Save the updates or add to the update)
 *
 */
public class ConfirmBindingsMenuCommand extends KeyBindingsMenuCommand {

	public ConfirmBindingsMenuCommand(KeyBindingsMenu bindingsMenu) {
		super(bindingsMenu);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		super.getBindingsMenu().confirm();
	}

}
