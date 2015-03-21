package controller.commands.armory;

import controller.commands.Commandable;

public class ArmoryPrevious extends ArmorySelection implements Commandable {

	public ArmoryPrevious(ArmoryDetails details) {
		super(details);
	}

	@Override
	public void execute() {
		super.previous();
	}

}
