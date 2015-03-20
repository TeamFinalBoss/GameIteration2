package controller.commands.sack;

import controller.commands.Commandable;

public class SackDrop extends SackActiveSelection implements Commandable {

	public SackDrop(SackDetails details) {
		super(details);
	}

	@Override
	public void execute() {
		int index = super.getDetails().getCurrentIndex();
		//TODO Drop the item here.
	}

}
