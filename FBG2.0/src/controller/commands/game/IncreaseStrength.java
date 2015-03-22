package controller.commands.game;

import controller.commands.Commandable;

public class IncreaseStrength extends AvatarCommands implements Commandable {

	@Override
	public void execute() {
		super.incraseStrength();
	}

}
