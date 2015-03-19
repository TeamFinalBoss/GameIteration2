package controller.keyBindings;

import controller.commands.Commandable;
import controller.commands.ForwardingCommand;
import controller.commands.keyBindings.BindingsUpdate;

public class KeyOptionsUpdating extends KeyOptions {
	
	private ForwardingCommand command;
	
	public KeyOptionsUpdating() {
		super();
		command = new BindingsUpdate();
	}
	
	public KeyOptionsUpdating(ForwardingCommand command) {
		super();
		this.command = command;
	}
	
	public void useKey(Integer key) {
		command.execute(key);
	}
}
