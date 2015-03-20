package controller.commands.sceneChangers;

import model.director.GameDirector;
import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis
 * 
 * This game is responsible for starting a new game
 *
 */
public class NewGame extends SceneChangerCommands implements Commandable {

	private GameDirector gameDirector = GameDirector.getGameDirector();
	
	@Override
	public void execute() {
		gameDirector.startNewGame();
		super.switchScene(SceneType.GAME);
	}

}
