package controller.commands.pauseMenu;

import controller.commands.Commandable;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;



/**
 * @author Kyle Kyrazis
 * 
 * This class is responsible for loading a saved game.
 *
 */
public class LoadGame implements Commandable {

	private SceneChanger sceneChanger = SceneChanger.getInstance();
	
	@Override
	public void execute() {
		sceneChanger.changeScene(SceneType.LOAD);
	}
}
