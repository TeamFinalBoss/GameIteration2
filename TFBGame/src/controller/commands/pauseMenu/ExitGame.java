package controller.commands.pauseMenu;

import controller.commands.Commandable;

/**
 * @author Kyle Kyrazis
 * 
 * This class exits the game.
 *
 */
public class ExitGame implements Commandable {

	@Override
	public void execute() {
		System.exit(0);
	}

}
