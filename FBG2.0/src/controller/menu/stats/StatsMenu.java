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
import controller.commands.game.IncreaseBargain;
import controller.commands.game.IncreaseBoundWound;
import controller.commands.game.IncreaseHardiness;
import controller.commands.game.IncreaseIntellect;
import controller.commands.game.IncreaseMovement;
import controller.commands.game.IncreaseObservation;
import controller.commands.game.IncreaseSkill1;
import controller.commands.game.IncreaseSkill2;
import controller.commands.game.IncreaseSkill3;
import controller.commands.game.IncreaseSkill4;
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
		options.add(StatsOption.BIND_WOUND);
		options.add(StatsOption.BARGAIN);
		options.add(StatsOption.OBSERVATION);
		options.add(StatsOption.SKILL_1);
		options.add(StatsOption.SKILL_2);
		options.add(StatsOption.SKILL_3);
		options.add(StatsOption.SKILL_4);
		
		commands = new HashMap<>();
		commands.put(StatsOption.AGILITY, new IncreaseAgility());
		commands.put(StatsOption.STRENGTH, new IncreaseStrength());
		commands.put(StatsOption.INTELLECT, new IncreaseIntellect());
		commands.put(StatsOption.HARDINESS, new IncreaseHardiness());
		commands.put(StatsOption.MOVEMENT, new IncreaseMovement());	
		commands.put(StatsOption.BIND_WOUND, new IncreaseBoundWound());	
		commands.put(StatsOption.BARGAIN, new IncreaseBargain());	
		commands.put(StatsOption.OBSERVATION, new IncreaseObservation());	
		commands.put(StatsOption.SKILL_1, new IncreaseSkill1());	
		commands.put(StatsOption.SKILL_2, new IncreaseSkill2());	
		commands.put(StatsOption.SKILL_3, new IncreaseSkill3());	
		commands.put(StatsOption.SKILL_4, new IncreaseSkill4());	
		
	}
	
	@Override
	public void next() {
		int index = currentIndex;
		index = index + 1 <= options.size() - 1 ? index + 1 : index; 
		setActiveOption(index);
		
	}

	private void setActiveOption(int index) {
		this.currentIndex = index;
		setChanged();
		notifyObservers();
	}

	@Override
	public void previous() {
		int index = currentIndex;
		index = index - 1 < 0 ? 0 : index - 1;
		setActiveOption(index);
	}

	@Override
	public void confirm() {
		commands.get(options.get(currentIndex)).execute();
		setActiveOption(currentIndex);
	}

	@Override
	public int getCurrentIndex() {
		return this.currentIndex;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void addObserver(Observer o) {
		super.addObserver(o);
		setChanged();
		notifyObservers();
	}

	@Override
	public String[] getDescription() {
		List<String> strings = new ArrayList<>();
		strings.add("Modifiable Stats\tSkill Points " + manager.getSkillPoints() + " Stat Points " + manager.getStatPoints());

		for(int i = 0; i < 5; i++) {
			String str = options.get(i).toString() + "\t";

			switch(options.get(i)) {
				case STRENGTH :
					str += manager.getStrength();
					break;
				case INTELLECT :
					str += manager.getIntellect();
					break;
				case AGILITY :
					str += manager.getAgility();
					break;
				case HARDINESS :
					str += manager.getHardiness();
					break;
				case MOVEMENT :
					str += manager.getMovement();
					break;
				default :
					break;
			}
			strings.add(str);
		}
		strings.addAll(buildSkills());
		strings.addAll(buildDerivedStats());
		String[] array = strings.toArray(new String[strings.size()]);
		return array;
	}

	
	private List<String> buildDerivedStats() {
		List<String> derivedStats = new ArrayList<>();
		derivedStats.add("Derived Stats");
		derivedStats.add("Currency\t" + manager.getCurrency());
		derivedStats.add("Lives Left\t" + manager.getLivesLeft());
		derivedStats.add("Experience\t" + manager.getExperience());
		derivedStats.add("Level\t" + manager.getLevel());
		derivedStats.add("Offense\t" + manager.getOffense());
		derivedStats.add("Defense\t" + manager.getDefense());
		derivedStats.add("Total Armor\t" + manager.getArmor());
		derivedStats.add("Weapon Damage\t" + manager.getWeaponOffense());
		derivedStats.add("Armor From Equipment\t" + manager.getEquipArmor());
		
		return derivedStats;
	}
	
	private List<String> buildSkills() {
		List<String> skills = new ArrayList<>();
		skills.add("Bind Wounds\t" + manager.getBindWounds());
		skills.add("Bargain\t" + manager.getBargain());
		skills.add("Observation\t" + manager.getObservation());
		skills.addAll(buildOccupationList());
		return skills;
	}

	private List<String> buildOccupationList() {
		List<String> occupationSpecific = new ArrayList<>();
		switch(manager.getOccupation()) {
			case "summoner" :
				occupationSpecific.add("Enchantment\t" + manager.getClassSkill1().getSecond());
				occupationSpecific.add("Bane\t" + manager.getClassSkill2().getSecond());
				occupationSpecific.add("Boon\t" + manager.getClassSkill3().getSecond());
				occupationSpecific.add("Staff\t" + manager.getClassSkill4().getSecond());
				break;
			case "smasher" :
				occupationSpecific.add("One Handed\t" + manager.getClassSkill1().getSecond());
				occupationSpecific.add("Two Handed\t" + manager.getClassSkill2().getSecond());
				occupationSpecific.add("Brawling\t" + manager.getClassSkill3().getSecond());
				occupationSpecific.add("Chakra\t" + manager.getClassSkill4().getSecond());
				break;
			case "sneak" :
				occupationSpecific.add("Pickpocket\t" + manager.getClassSkill1().getSecond());
				occupationSpecific.add("Trap\t" + manager.getClassSkill2().getSecond());
				occupationSpecific.add("Creep\t" + manager.getClassSkill3().getSecond());
				occupationSpecific.add("Ranged Weapon\t" + manager.getClassSkill4().getSecond());
				break;
			default :	
				break;
		}
		return occupationSpecific;
	}
}
