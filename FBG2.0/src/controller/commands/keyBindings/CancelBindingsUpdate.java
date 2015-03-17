package controller.commands.keyBindings;

import controller.commands.Commandable;
import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsUpdate;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis 
 * 
 * This class discards any changes made and returns to the pause menu
 *
 */
public class CancelBindingsUpdate extends BindingsUpdate implements Commandable {

	private SceneChanger sceneChanger = SceneChanger.getInstance();

	public CancelBindingsUpdate(KeyBindings bindings, KeyBindingsUpdate update) {
		super(bindings,update);
	}

	@Override
	public void execute() {
		super.clear();
		sceneChanger.changeScene(SceneType.PAUSE_MENU);
	}

}
