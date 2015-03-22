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
	public abstract void execute();
}
