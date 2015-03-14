package controller.commands.pauseMenu;

import controller.commands.Commandable;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis
 * 
 * This class switches to the KeyBindingsMenu
 *
 */
public class KeyBindingSwitch implements Commandable {
	
	private SceneChanger sceneChanger = SceneChanger.getInstance();

	@Override
	public void execute() {
		sceneChanger.changeScene(SceneType.KEY_BINDINGS);
	}
}
