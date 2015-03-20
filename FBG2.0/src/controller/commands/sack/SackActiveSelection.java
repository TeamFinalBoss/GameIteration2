package controller.commands.sack;

import controller.commands.Commandable;

public abstract class SackActiveSelection implements Commandable {

	private SackDetails details;

	public SackActiveSelection(SackDetails details) {
		this.details = details;
	}
	
	protected void next() {
		details.next();
	}
	
	protected void previous() {
		details.previous();
	}
	
	protected void down() {
		details.down();
	}
	
	protected void up() {
		details.up();
	}
	
	@Override
	public abstract void execute();

	public SackDetails getDetails() {
		return this.details;
	}

}
