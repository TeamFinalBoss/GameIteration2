package controller.commands.game;

import controller.commands.Commandable;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;

public class Pause implements Commandable {

	SceneChanger sceneChanger = SceneChanger.getInstance();
	
	@Override
	public void execute() {
		sceneChanger.changeScene(SceneType.PAUSE_MENU);
	}

}
