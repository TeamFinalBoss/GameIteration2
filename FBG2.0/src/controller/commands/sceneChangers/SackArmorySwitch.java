package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class SackArmorySwitch extends ArmorySackMaintainer implements Commandable {

	@Override
	public void execute() {
		if(super.isPressedSack()) {
			super.setPressedSack(false);
			if(super.isPressedArmory()){
				super.switchScene(SceneType.ARMORY);
			} else {
				super.switchScene(SceneType.GAME);
			}
		} else {
			super.switchScene(SceneType.GAME);
		}
	}
}
