package controller.commands.pauseMenu;

import controller.commands.Commandable;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis
 * 
 * This class is responsible for saving the game.
 *
 */
public class SaveGame implements Commandable {
	
	private SceneChanger sceneChanger = SceneChanger.getInstance();

	@Override
	public void execute() {
		sceneChanger.changeScene(SceneType.SAVE);
	}

}
