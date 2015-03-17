package controller.menu.keyBindings;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.commands.Commandable;
import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsOption;
import controller.menu.Menuable;
import controller.util.Describeable;

/**
 * @author Kyle Kyrazis
 * 
 * This class is used to display the options for remapping keys.
 *
 */
public class KeyBindingsMenu extends Observable implements Describeable, Menuable {
	
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

	
	//TODO is this necessary?
	@Override
	public String[] getDescription() {
		Map<KeyBindingsOption, Integer> map = keyBindings.getBindingsReverse();
		String[] strsToReturn = new String[bindingsOptions.size()];
		for(int i = 0; i < bindingsOptions.size(); i++) {
			strsToReturn[i] =
					bindingsOptions.get(i).toString() + " ";
			if(map.get(bindingsOptions.get(i)) != null) {
				strsToReturn[i] += KeyEvent.getKeyText(map.get(bindingsOptions.get(i)));
			}
		}
		return strsToReturn;
	}
	
	public int getCurrentIndex() {
		return bindingsOptions.indexOf(currentSelection);
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
		//TODO might not need this
		setChanged();
		notifyObservers();
	}
	
	private void setActiveOption(KeyBindingsOption keyBindingsOption) {
		this.currentSelection = keyBindingsOption;
		setChanged();
		notifyObservers();
	}
	
	public KeyBindingsOption getActiveOption() {
		return this.currentSelection;
	}
	
	public void addObserver(Observer o) {
		super.addObserver(o);
        setChanged();
        notifyObservers();
	}
	
}
