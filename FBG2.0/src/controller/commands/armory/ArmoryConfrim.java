package controller.commands.armory;

import controller.commands.Commandable;

public class ArmoryConfrim extends ArmorySelection implements Commandable {

	public ArmoryConfrim(ArmoryDetails details) {
		super(details);
	}

	@Override
	public void execute() {
		super.confirm();
	}

}
