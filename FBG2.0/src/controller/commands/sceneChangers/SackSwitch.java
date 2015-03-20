package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class SackSwitch extends SceneChangerCommands implements Commandable {
	
	public SackSwitch() {
		
	}
	
	public void execute() {
		super.switchScene(SceneType.SACK);
	}

}
