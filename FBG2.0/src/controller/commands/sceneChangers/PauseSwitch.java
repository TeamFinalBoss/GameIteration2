package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class PauseSwitch extends SceneChangerCommands implements Commandable {
	
	@Override
	public void execute() {
		super.switchScene(SceneType.PAUSE_MENU);
	}

}
