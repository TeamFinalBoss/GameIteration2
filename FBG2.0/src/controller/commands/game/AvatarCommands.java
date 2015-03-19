package controller.commands.game;

import model.entity.Entity;
import controller.commands.Commandable;

public abstract class AvatarCommands implements Commandable {
	
	private Entity avatar;
	
	public AvatarCommands() {
		
	}
	
	public AvatarCommands(Entity e) {
		this.avatar = e;
	}
	
	protected Entity getAvatar() {
		return this.avatar;
	}
	
	public abstract void execute();
}
