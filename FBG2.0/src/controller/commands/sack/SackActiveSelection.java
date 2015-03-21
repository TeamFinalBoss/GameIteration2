package controller.commands.sack;

import model.director.AvatarInteractionManager;
import controller.commands.Commandable;

public abstract class SackActiveSelection implements Commandable {

	private SackDetails details;
	private AvatarInteractionManager manager = AvatarInteractionManager.getInstance();

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

	public void drop() {
		manager.dropItemAtSlot(details.getCurrentIndex());
		
	}

	public void confirm() {
		manager.useItemAtSackSlot(details.getCurrentIndex());
	}

}
