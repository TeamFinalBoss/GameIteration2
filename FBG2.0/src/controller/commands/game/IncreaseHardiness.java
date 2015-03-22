package controller.commands.game;

import controller.commands.Commandable;

public class IncreaseHardiness extends AvatarCommands implements Commandable {

	@Override
	public void execute() {
		super.increaseHardiness();
	}

}
