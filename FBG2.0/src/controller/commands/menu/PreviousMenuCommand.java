package controller.commands.menu;

import java.util.List;

import controller.menu.Menu;
import controller.menu.MenuOption;

/**
 * @author Kyle Kyrazis
 * 
 * Used to select the previous option in the menu. Updates the active option.
 *
 */
public class PreviousMenuCommand extends MenuCommand {

	public PreviousMenuCommand(Menu menu) {
		super(menu);
	}

	@Override
	public void execute() {
		getMenu().previous();
	}

}
