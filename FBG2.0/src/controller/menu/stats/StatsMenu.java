package controller.menu.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import model.director.AvatarInteractionManager;
import controller.commands.Commandable;
import controller.commands.game.IncreaseAgility;
import controller.commands.game.IncreaseHardiness;
import controller.commands.game.IncreaseIntellect;
import controller.commands.game.IncreaseMovement;
import controller.commands.game.IncreaseStrength;
import controller.menu.Menuable;
import controller.util.Describeable;

public class StatsMenu extends Observable implements Describeable,Menuable, Observer {

	AvatarInteractionManager manager = AvatarInteractionManager.getInstance();
	
	private List<StatsOption> options;
	private int currentIndex = 0;
	private Map<StatsOption, Commandable> commands;
	
	public StatsMenu() {
		options = new ArrayList<>();
		options.add(StatsOption.AGILITY);
		options.add(StatsOption.STRENGTH);
		options.add(StatsOption.INTELLECT);
		options.add(StatsOption.HARDINESS);
		options.add(StatsOption.MOVEMENT);
		
		commands = new HashMap<>();
		commands.put(StatsOption.AGILITY, new IncreaseAgility());
		commands.put(StatsOption.STRENGTH, new IncreaseStrength());
		commands.put(StatsOption.INTELLECT, new IncreaseIntellect());
		commands.put(StatsOption.HARDINESS, new IncreaseHardiness());
		commands.put(StatsOption.MOVEMENT, new IncreaseMovement());
	}
	
	@Override
	public void next() {
		int index = options.indexOf(currentIndex);
		index = index + 1 <= options.size() - 1 ? index + 1 : index; 
		setActiveOption(index);
		
	}

	private void setActiveOption(int index) {
		this.currentIndex = index;
		
	}

	@Override
	public void previous() {
		int index = options.indexOf(currentIndex);
		index = index - 1 < 0 ? 0 : index - 1;
		setActiveOption(index);
	}

	@Override
	public void confirm() {
		commands.get(options.get(currentIndex)).execute();
	}

	@Override
	public int getCurrentIndex() {
		return this.currentIndex;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
