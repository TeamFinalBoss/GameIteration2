package controller.commands.armory;

public class ArmoryNext extends ArmorySelection {

	public ArmoryNext(ArmoryDetails details) {
		super(details);
	}

	@Override
	public void execute() {
		super.next();
	}

}
