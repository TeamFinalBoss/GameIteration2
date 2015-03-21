package controller.commands.armory;

import controller.commands.Commandable;

public class ArmoryDown extends ArmorySelection implements Commandable {

	public ArmoryDown(ArmoryDetails details) {
		super(details);
	}

	@Override
	public void execute() {
		super.down();
	}

}
