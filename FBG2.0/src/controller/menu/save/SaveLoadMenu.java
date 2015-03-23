package controller.menu.save;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
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
		File[] list = new File("./src/resources/saves/").listFiles();
		files.clear();
		for(File file : list) {
			if(file.isFile() && !file.getName().equals("default.xml")) {
				files.add(file);
			}
		}
		files.sort(new Comparator<File>() {
			@Override
			public int compare(File arg0, File arg1) {
				return Long.valueOf(arg1.lastModified()).compareTo(arg0.lastModified());
			}  
		});
		setChanged();
		notifyObservers();
	}
	
	@Override
	public String[] getDescription() {
		String[] strArray = new String[maximumNumberOfFileDisplayed];
		for(int i = 0; i < strArray.length; i++) {
			strArray[i] = files.get(i).getName().substring(0, files.get(i).getName().lastIndexOf("."));
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
		refresh();
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
