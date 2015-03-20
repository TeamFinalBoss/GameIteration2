package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis
 * 
 * This class switches to the KeyBindingsMenu
 *
 */
public class KeyBindingSwitch extends SceneChangerCommands implements Commandable {
	
	public KeyBindingSwitch() {
		super();
	}
	
	@Override
	public void execute() {
		super.switchScene(SceneType.KEY_BINDINGS);
	}
}
