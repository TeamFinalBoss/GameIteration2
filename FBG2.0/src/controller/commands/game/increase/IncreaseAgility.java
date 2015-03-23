package controller.commands.game.increase;

import controller.commands.Commandable;
import controller.commands.game.AvatarCommands;

public class IncreaseAgility extends AvatarCommands implements Commandable {

	@Override
	public void execute() {
		super.increaseAgility();
	}

}
