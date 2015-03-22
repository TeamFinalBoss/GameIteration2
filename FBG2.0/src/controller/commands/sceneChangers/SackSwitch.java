package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class SackSwitch extends ArmorySackMaintainer implements Commandable {
	
	public SackSwitch() {
		
	}
	
	public void execute() {
		super.setPressedSack(true);
		super.switchScene(SceneType.SACK);
	}

}
