package controller.commands.game;

import model.director.AvatarInteractionManager;
import model.map.Direction;
import controller.commands.Commandable;

public abstract class AvatarCommands implements Commandable {
	
	AvatarInteractionManager manager = AvatarInteractionManager.getInstance();
	
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
	
	public abstract void execute();
}
