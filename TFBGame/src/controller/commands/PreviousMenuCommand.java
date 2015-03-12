package controller.commands;

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
		List<MenuOption> options = super.getMenu().getMenuOptions();
		int index = options.indexOf(getMenu().getActiveOption());
		if(--index < 0) {
			index = options.size() - 1;
		}
		getMenu().setActiveOption(options.get(index));
	}

}
