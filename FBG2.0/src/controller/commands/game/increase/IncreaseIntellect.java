package controller.commands.game.increase;

import controller.commands.Commandable;
import controller.commands.game.AvatarCommands;

public class IncreaseIntellect extends AvatarCommands implements Commandable {

	@Override
	public void execute() {
		super.increaseIntellect();
	}

}
