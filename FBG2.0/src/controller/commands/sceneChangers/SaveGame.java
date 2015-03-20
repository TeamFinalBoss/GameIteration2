package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis
 * 
 * This class is responsible for saving the game.
 *
 */
public class SaveGame extends SceneChangerCommands implements Commandable {
	
	

	@Override
	public void execute() {
		super.switchScene(SceneType.SAVE);
	}

}
