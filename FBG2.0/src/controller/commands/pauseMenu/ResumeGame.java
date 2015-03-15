package controller.commands.pauseMenu;

import controller.commands.Commandable;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis
 * 
 * This class is used to return to the game.
 *
 */
public class ResumeGame implements Commandable {

	SceneChanger sceneChanger = SceneChanger.getInstance();
	@Override
	public void execute() {
		sceneChanger.changeScene(SceneType.GAME);
	}

}
