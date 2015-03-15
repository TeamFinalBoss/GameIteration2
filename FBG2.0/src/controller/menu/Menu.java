package controller.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.commands.Commandable;
import controller.util.Describeable;


/**
 * @author Kyle Kyrazis
 * 
 * Maintains all possible options and the currently selected option.
 * 
 * TODO I think this class needs to be updated, but for now it should work.
 *
 */
public class Menu implements Describeable, Commandable {
	private List<MenuOption> menuOptions;
	private MenuOption activeOption;
	private Map<MenuOption, Commandable> menuCommands;
	
	public Menu() {
		menuOptions = new ArrayList<MenuOption>();
		menuCommands = new HashMap<MenuOption, Commandable>();
	}
	
	public Menu(
			List<MenuOption> menuOptions,
			MenuOption activeOption,
			Map<MenuOption, Commandable> menuCommands)
	{
		this.menuOptions = menuOptions;
		this.activeOption = activeOption;
		this.menuCommands = menuCommands;
	}
	
	
	/**
	 * Returns all possible enums as human readable text.
	 * @return List<String>
	 */
	@Override
	public List<String> getDescription() {
		List<String> returnList = new ArrayList<String>();
		
		for(MenuOption opt : menuOptions) {
			returnList.add(opt.toString());
		}
		return returnList;
	}
	
	public List<MenuOption> getMenuOptions() {
		return this.menuOptions;
	}
	
	public void setActiveOption(MenuOption option) {
		this.activeOption = option;
	}
	
	public MenuOption getActiveOption() {
		return this.activeOption;
	}
	
	public void next() {
		int index = menuOptions.indexOf(activeOption);
		index = ++index % menuOptions.size();
		setActiveOption(menuOptions.get(index));
	}
	
	public void previous() {
		int index = menuOptions.indexOf(activeOption);
		index = index - 1 < 0 ? menuOptions.size() - 1 : index - 1;
		setActiveOption(menuOptions.get(index));
	}

	@Override
	public void execute() {
		menuCommands.get(activeOption).execute();
	}
}
