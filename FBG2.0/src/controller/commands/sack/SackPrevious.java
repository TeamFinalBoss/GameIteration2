package controller.commands.sack;

import controller.commands.Commandable;

public class SackPrevious extends SackActiveSelection implements Commandable {
	
	public SackPrevious(SackDetails details) {
		super(details);
	}

	@Override
	public void execute() {
		super.previous();
	}
}
