package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class StoreFrontSwitch extends SceneChangerCommands implements
		Commandable {

	@Override
	public void execute() {
		super.switchScene(SceneType.STORE);

	}

}
