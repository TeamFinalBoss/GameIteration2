package controller.menu.keyBindings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.commands.Commandable;
import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsOption;
import controller.util.Describeable;

/**
 * @author Kyle Kyrazis
 * 
 * This class is used to display the options for remapping keys.
 *
 */
public class KeyBindingsMenu implements Describeable {
	
	private KeyBindings keyBindings;
	private List<KeyBindingsOption> bindingsOptions;
	private KeyBindingsOption currentSelection;
	private Map<KeyBindingsOption, Commandable> bindingsCommands;
	
	public KeyBindingsMenu() {
		keyBindings = new KeyBindings();
		currentSelection = KeyBindingsOption.UP;
		bindingsCommands = new HashMap<KeyBindingsOption, Commandable>();
	}

	public KeyBindingsMenu(KeyBindings keyBindings,
			List<KeyBindingsOption> bindingsOptions,
			KeyBindingsOption currentSelection,
			Map<KeyBindingsOption, Commandable> bindingsCommands)
	{
		this.keyBindings = keyBindings;
		this.bindingsOptions = bindingsOptions;
		this.currentSelection = currentSelection;
		this.bindingsCommands = bindingsCommands;
	}

	@Override
	public List<String> getDiscription() {
		return keyBindings.getDiscription();
	}

	
	
	public void next() {
		int index = bindingsOptions.indexOf(currentSelection);
		index = ++index % bindingsOptions.size();
		setActiveOption(bindingsOptions.get(index));
	}
	
	public void previous() {
		int index = bindingsOptions.indexOf(currentSelection);
		index = index - 1 < 0 ? bindingsOptions.size() - 1 : index - 1;
		setActiveOption(bindingsOptions.get(index));
	}
	
	public void confirm() {
		bindingsCommands.get(currentSelection).execute();
	}
	
	private void setActiveOption(KeyBindingsOption keyBindingsOption) {
		this.currentSelection = keyBindingsOption;
	}
	
}
