package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis
 * 
 * This class is used to return to the game.
 *
 */
public class ResumeGame extends SceneChangerCommands implements Commandable {

	@Override
	public void execute() {
		ArmorySackMaintainer.setPressedArmory(false);
		ArmorySackMaintainer.setPressedSack(false);
		super.switchScene(SceneType.GAME);
	}

}
