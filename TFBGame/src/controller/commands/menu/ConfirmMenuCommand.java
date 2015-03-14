package controller.commands.menu;

import controller.menu.Menu;


/**
 * @author Kyle Kyrazis
 * 
 * Executes the currently selected MenuOption
 *
 */
public class ConfirmMenuCommand extends MenuCommand {

	public ConfirmMenuCommand(Menu menu) {
		super(menu);
	}

	@Override
	public void execute() {
		super.getMenu().execute();
	}

}
