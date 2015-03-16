package controller.commands.menu;

import controller.menu.Menuable;


/**
 * @author Kyle Kyrazis
 * 
 * Executes the currently selected MenuOption
 *
 */
public class ConfirmMenuCommand extends MenuCommand {

	public ConfirmMenuCommand(Menuable menu) {
		super(menu);
	}

	@Override
	public void execute() {
		super.getMenu().confirm();
	}

}
