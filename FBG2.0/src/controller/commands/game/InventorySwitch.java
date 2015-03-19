package controller.commands.game;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class InventorySwitch extends SceneChangerCommands implements Commandable {
	
	public InventorySwitch() {
		
	}
	
	public void execute() {
		super.switchScene(SceneType.INEVENTORY);
	}

}
