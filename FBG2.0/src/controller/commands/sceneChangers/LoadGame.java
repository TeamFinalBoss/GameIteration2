package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;



/**
 * @author Kyle Kyrazis
 * 
 * This class is responsible for loading a saved game.
 *
 */
public class LoadGame extends SceneChangerCommands implements Commandable {

	
	@Override
	public void execute() {
		super.switchScene(SceneType.LOAD);
	}
}
