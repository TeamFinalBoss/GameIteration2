package controller.commands.sack;

import controller.commands.Commandable;

public class SackDown extends SackActiveSelection implements Commandable {

	public SackDown(SackDetails details) {
		super(details);
	}

	@Override
	public void execute() {
		super.down();
	}

}
