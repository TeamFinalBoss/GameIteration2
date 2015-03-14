package controller.commands.keyBindings.menu;

import controller.menu.keyBindings.KeyBindingsMenu;

/**
 * @author Kyle Kyrazis
 * 
 * Used to select the previous option in the KeyBindingsMenuCommand
 *
 */
public class PreviousBindingsMenuCommand extends KeyBindingsMenuCommand {

	public PreviousBindingsMenuCommand(KeyBindingsMenu bindingsMenu) {
		super(bindingsMenu);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		super.getBindingsMenu().previous();
	}

}
