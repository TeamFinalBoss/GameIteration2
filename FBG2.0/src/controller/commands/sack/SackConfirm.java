package controller.commands.sack;

import controller.commands.Commandable;

public class SackConfirm extends SackActiveSelection implements Commandable {

	public SackConfirm(SackDetails details) {
		super(details);
	}

	@Override
	public void execute() {
		super.confirm();
	}

}
