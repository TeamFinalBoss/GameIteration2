package controller.commands;

import java.util.List;

import controller.menu.Menu;
import controller.menu.MenuOption;


/**
 * @author Kyle Kyrazis
 * 
 * Used to select the next menu option. Updates the active option.
 *
 */
public class NextMenuCommand extends MenuCommand {

	public NextMenuCommand(Menu menu) {
		super(menu);
	}

	@Override
	public void execute() {
		List<MenuOption> options = super.getMenu().getMenuOptions();
		int index = options.indexOf(getMenu().getActiveOption());
		index = ++index % options.size();
		getMenu().setActiveOption(options.get(index));
	}

}
