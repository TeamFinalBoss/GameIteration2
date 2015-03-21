package controller.commands.armory;

import controller.commands.Commandable;

public class ArmoryUp extends ArmorySelection implements Commandable {

	public ArmoryUp(ArmoryDetails details) {
		super(details);
	}

	@Override
	public void execute() {
		super.up();
	}

}
