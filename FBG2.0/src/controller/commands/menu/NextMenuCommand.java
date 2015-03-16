package controller.commands.menu;

import controller.menu.Menuable;


/**
 * @author Kyle Kyrazis
 * 
 * Used to select the next menu option. Updates the active option.
 *
 */
public class NextMenuCommand extends MenuCommand {

	public NextMenuCommand(Menuable menu) {
		super(menu);
	}

	@Override
	public void execute() {
		getMenu().next();
	}

}
