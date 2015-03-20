package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class DialogueSwitch extends SceneChangerCommands implements Commandable {

	public DialogueSwitch() {
		
	}
	@Override
	public void execute() {
		super.switchScene(SceneType.DIALOGUE);
	}
	
}
