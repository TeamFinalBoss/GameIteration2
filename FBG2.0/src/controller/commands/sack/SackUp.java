package controller.commands.sack;

import controller.commands.Commandable;

public class SackUp extends SackActiveSelection implements Commandable {

	public SackUp(SackDetails details) {
		super(details);
	}

	@Override
	public void execute() {
		super.up();
	}

}
