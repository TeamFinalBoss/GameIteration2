package controller.menu.save;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.commands.Commandable;
import controller.menu.Menuable;
import controller.util.Describeable;

public class SaveLoadMenu extends Observable implements Describeable,Menuable {

	private List<SaveOption> saveOptions;
	private SaveOption currentOption;
	private Map<SaveOption, Commandable> commands;
	
	public SaveLoadMenu() {
		saveOptions = new ArrayList<>();
		currentOption = null;
		commands = new HashMap<>();
	}
	
	public SaveLoadMenu(List<SaveOption> opts, SaveOption currentOption, Map<SaveOption, Commandable> map) {
		saveOptions = opts;
		this.currentOption = currentOption;
		this.commands = map;
	}
	
	@Override
	public String[] getDescription() {
		String[] strArray = new String[saveOptions.size()];
		for(int i = 0; i < strArray.length; ++i) {
			strArray[i] = saveOptions.get(i).toString();
		}
		return strArray;
	}

	@Override
	public int getCurrentIndex() {
		return saveOptions.indexOf(currentOption);
	}
	
	public void next() {
		int index = saveOptions.indexOf(currentOption);
		index = ++index % saveOptions.size();
		setActiveOption(saveOptions.get(index));
	}
	
	private void setActiveOption(SaveOption saveOption) {
		this.currentOption = saveOption;
		setChanged();
		notifyObservers();
	}

	public void previous() {
		int index = saveOptions.indexOf(currentOption);
		index = index - 1 < 0 ? saveOptions.size() - 1 : index - 1;
		setActiveOption(saveOptions.get(index));
	}

	
	public void confirm() {
		commands.get(currentOption).execute();
	}
	
	public void addObserver(Observer o) {
		super.addObserver(o);
        setChanged();
        notifyObservers();
	}

}
