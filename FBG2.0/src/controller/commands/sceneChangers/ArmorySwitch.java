package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class ArmorySwitch extends ArmorySackMaintainer implements Commandable {

	@Override
	public void execute() {
		super.setPressedArmory(true);
		super.switchScene(SceneType.ARMORY);
	}

}
