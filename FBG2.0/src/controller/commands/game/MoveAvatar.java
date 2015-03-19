package controller.commands.game;

import model.map.Direction;
import controller.commands.Commandable;

public class MoveAvatar extends AvatarCommands implements Commandable{

	//TODO use whatever moves the entity to move it in the specified direction.
	private Direction direction;
	
	public MoveAvatar() {
		this.direction = Direction.North;
	}
	
	public MoveAvatar(Direction direction) {
		this.direction = direction;
	}
	
	@Override
	public void execute() {
		super.moveAvatar(direction);
	} 
	

}
