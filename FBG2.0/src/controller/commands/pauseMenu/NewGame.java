package controller.commands.pauseMenu;

import model.director.GameDirector;
import controller.commands.Commandable;

/**
 * @author Kyle Kyrazis
 * 
 * This game is responsible for starting a new game
 *
 */
public class NewGame implements Commandable {

	GameDirector gameDirector = GameDirector.getGameDirector();
	
	@Override
	public void execute() {
		gameDirector.startNewGame();
	}

}
