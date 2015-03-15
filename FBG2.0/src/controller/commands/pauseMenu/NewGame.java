package controller.commands.pauseMenu;

import model.director.GameDirector;
import controller.commands.Commandable;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis
 * 
 * This game is responsible for starting a new game
 *
 */
public class NewGame implements Commandable {

	GameDirector gameDirector = GameDirector.getGameDirector();
	SceneChanger sceneChanger = SceneChanger.getInstance();
	
	@Override
	public void execute() {
		gameDirector.startNewGame();
		sceneChanger.changeScene(SceneType.GAME);
	}

}
