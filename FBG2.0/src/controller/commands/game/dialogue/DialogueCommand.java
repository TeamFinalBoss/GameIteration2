package controller.commands.game.dialogue;

import controller.commands.Commandable;
import controller.commands.game.AvatarCommands;

public class DialogueCommand extends AvatarCommands implements Commandable {

	private int commandValue;
	
	public DialogueCommand(int value) {
		this.commandValue = value;
	}
	
	@Override
	public void execute() {
		super.dialogueOption(commandValue);
	}

}
