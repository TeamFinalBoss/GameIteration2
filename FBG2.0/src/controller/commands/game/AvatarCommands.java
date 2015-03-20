package controller.commands.game;

import model.director.ActiveMapManager;
import model.map.Direction;
import controller.commands.Commandable;

public abstract class AvatarCommands implements Commandable {
	
	ActiveMapManager manager = ActiveMapManager.getInstance();
	
	public AvatarCommands() {
		
	}
	

	protected void moveAvatar(Direction direction) {
		manager.moveAvatar(direction);
	}
	
	protected void useAbility(int ability) {
		manager.useAvatarAbility(ability);
	}
	
	public abstract void execute();
}
