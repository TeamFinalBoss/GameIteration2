package controller.commands.game;

import java.util.List;
import java.util.Observable;

import view.viewport.ObservationViewPort;
import model.director.AvatarInteractionManager;
import model.map.Direction;
import controller.commands.Commandable;
import controller.util.Describeable;

public abstract class AvatarCommands extends Observable implements Commandable, Describeable {
	
	private AvatarInteractionManager manager = AvatarInteractionManager.getInstance();
	private String[] array;
	
	public AvatarCommands() {
		
	}
	

	protected void moveAvatar(Direction direction) {
		manager.moveAvatar(direction);
	}
	
	protected void useAbility(int ability) {
		manager.useAbility(ability);
	}
	
	protected void increaseAgility() {
		manager.increaseAgility();
	}
	
	protected void incraseStrength() {
		manager.increaseStrength();
	}
	
	protected void increaseIntellect() {
		manager.increaseIntellect();
	}
	
	protected void increaseHardiness() {
		manager.increaseHardiness();
	}
	
	protected void increaseMovement() {
		manager.increaseMovement();
	}
	
	protected void increaseBoundWound() {
		manager.increaseBindWounds();
	}
	
	protected void increaseBargain() {
		manager.increaseBargain();
	}
	
	protected void increaseObservation() {
		manager.increaseObservation();
	}
	
	protected void increaseSkill1() {
		manager.increaseClassSkill1();
	}
	
	protected void increaseSkill2() {
		manager.increaseClassSkill2();
	}
	
	protected void increaseSkill3() {
		manager.increaseClassSkill3();
	}
	
	protected void increaseSkill4() {
		manager.increaseClassSkill4();
	}
	
	protected void dialogueOption(int value) {
		manager.useDialogueOption(value);
	}
	
	protected void getObservationInformation() {
		List<String> strings = manager.getObservationInformation();
		array = new String[strings.size()];
		strings.toArray(array);
		setChanged();
		notifyObservers();
	}
	
	public String[] getDescription() {
		return this.array;
	}
	
	public int getCurrentIndex(){
		return 0;
	}
	
	public abstract void execute();
}
