package controller.commands.sack;

import controller.commands.Commandable;

public class SackNext extends SackActiveSelection implements Commandable {

	public SackNext(SackDetails details) {
		super(details);
	}

	@Override
	public void execute() {
		super.next();
	}

}
