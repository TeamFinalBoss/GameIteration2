package controller.commands.menu;

import controller.menu.Menuable;

/**
 * @author Kyle Kyrazis
 * 
 * Used to select the previous option in the menu. Updates the active option.
 *
 */
public class PreviousMenuCommand extends MenuCommand {

	public PreviousMenuCommand(Menuable menu) {
		super(menu);
	}

	@Override
	public void execute() {
		getMenu().previous();
	}

}
