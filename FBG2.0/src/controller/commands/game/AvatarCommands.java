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
		
	}
	
	protected void incraseStrength() {
		
	}
	
	protected void increaseIntellect() {
		
	}
	
	protected void increaseHardiness() {
		
	}
	
	protected void increaseMovement() {
		
	}
	public abstract void execute();
}
