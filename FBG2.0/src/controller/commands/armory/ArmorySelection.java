package controller.commands.armory;

import controller.commands.Commandable;

public abstract class ArmorySelection implements Commandable {

	private ArmoryDetails details;
	
	public ArmorySelection(ArmoryDetails details) {
		this.details = details;
	}
	
	public abstract void execute();
	
	protected void next() {
		details.next();
	}

	protected void previous() {
		details.previous();
	}
	
	protected void up() {
		details.up();
	}
	
	protected void down() {
		details.down();
	}
	
	protected void confirm() {
		details.confirm();
	}

}
