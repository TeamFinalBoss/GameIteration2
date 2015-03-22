package controller.menu.save;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.commands.Commandable;
import controller.menu.Menuable;
import controller.util.Describeable;
import controller.util.Selectable;

public class SaveLoadMenu extends Observable implements Describeable,Menuable, Observer {

	private List<SaveOption> saveOptions;
	private SaveOption currentOption;
	private Map<SaveOption, Commandable> commands;
	private List<File> files;
	private final int maximumNumberOfFileDisplayed = 3;
	
	public SaveLoadMenu() {
		saveOptions = new ArrayList<>();
		currentOption = null;
		commands = new HashMap<>();
		files = new ArrayList<>();
		refresh();
	}
	
	public SaveLoadMenu(List<SaveOption> opts, SaveOption currentOption, Map<SaveOption, Commandable> map) {
		saveOptions = opts;
		this.currentOption = currentOption;
		this.commands = map;
		files = new ArrayList<>();
		refresh();
	}
	
	private void refresh() {
		File[] list = new File("src/resources/saves/").listFiles();
		for(File file : list) {
			if(file.isFile() && !file.getName().equals("default.xml")) {
				files.add(file);
			}
		}
	}
	
	@Override
	public String[] getDescription() {
		/* Old But I hate deleting
		String[] strArray = new String[saveOptions.size()];
		for(int i = 0; i < strArray.length; ++i) {
			strArray[i] = saveOptions.get(i).toString();
		}
		return strArray;
		*/
		String[] strArray = new String[maximumNumberOfFileDisplayed];
		for(int i = 0; i < strArray.length; i++) {
			strArray[i] = files.get(i).getName();
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

	@Override
	public void update(Observable o, Object arg) {
		Selectable selectable = (Selectable) o;
		this.currentOption = this.saveOptions.get(selectable.getCurrentIndex());
		
	}

}
