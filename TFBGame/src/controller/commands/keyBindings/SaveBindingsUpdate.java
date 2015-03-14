package controller.commands.keyBindings;

import controller.Controller;
import controller.commands.Commandable;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis
 * 
 * This class saves the updated Keys and returns to the pause menu.
 *
 */
public class SaveBindingsUpdate extends BindingsUpdate implements Commandable {
	
	private Controller controller = Controller.getInstance();
	private SceneChanger sceneChanger = SceneChanger.getInstance();

	public void execute() {
		controller.updateControllerKeyBindings(super.getKeyBindingsUpdate());
		sceneChanger.changeScene(SceneType.PAUSE_MENU);
	}
}
