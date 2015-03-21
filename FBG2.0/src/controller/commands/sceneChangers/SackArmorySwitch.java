package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class SackArmorySwitch extends SceneChangerCommands implements Commandable {

	@Override
	public void execute() {
		super.switchScene(SceneType.ARMORY);
	}
}
