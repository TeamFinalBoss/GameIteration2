package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;

public abstract class SceneChangerCommands implements Commandable {
	
	private SceneChanger sceneChanger = SceneChanger.getInstance();
	
	protected void switchScene(SceneType type) {
		sceneChanger.changeScene(type);
	}
	
	public abstract void execute();
	
}
