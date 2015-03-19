package controller.commands.game;

import model.director.ActiveMapManager;
import model.entity.Entity;
import model.map.Direction;
import controller.commands.Commandable;

public class MoveAvatar extends AvatarCommands implements Commandable{

	//TODO use whatever moves the entity to move it in the specified direction.
	private Direction direction;
	private ActiveMapManager manager = ActiveMapManager.getInstance();
	
	public MoveAvatar() {
		this.direction = Direction.North;
	}
	
	public MoveAvatar(Direction direction) {
		this.direction = direction;
	}
	
	@Override
	public void execute() {
		Entity e = manager.getAvatar();
		manager.requestMovement(e, direction);
	} 
	

}
