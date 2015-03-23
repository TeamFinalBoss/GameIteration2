package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class StoreInvSwitch extends SceneChangerCommands implements Commandable {

	@Override
	public void execute() {
		super.switchScene(SceneType.STORE_INV);

	}

}
