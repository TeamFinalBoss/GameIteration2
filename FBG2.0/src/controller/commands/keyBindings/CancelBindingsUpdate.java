package controller.commands.keyBindings;

import controller.commands.Commandable;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis 
 * 
 * This class discards any changes made and returns to the pause menu
 *
 */
public class CancelBindingsUpdate implements Commandable {

	private SceneChanger sceneChanger = SceneChanger.getInstance();
	
	@Override
	public void execute() {
		sceneChanger.changeScene(SceneType.PAUSE_MENU);
	}

}
