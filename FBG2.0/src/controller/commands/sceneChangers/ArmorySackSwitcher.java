package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class ArmorySackSwitcher extends ArmorySackMaintainer implements Commandable{

	@Override
	public void execute() {
		if(super.isPressedArmory()) {
			super.setPressedArmory(false);
			if(super.isPressedSack()) {
				super.switchScene(SceneType.SACK);
			} else {
				super.switchScene(SceneType.GAME);
			}
		} else {
			super.switchScene(SceneType.SACK);
		}
	}

	

}
