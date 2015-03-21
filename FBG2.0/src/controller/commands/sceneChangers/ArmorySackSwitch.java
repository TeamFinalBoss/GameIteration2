package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;
import controller.util.SceneObserver;

public class ArmorySackSwitch extends SceneChangerCommands implements Commandable, SceneObserver {
	
	private SceneChanger sceneChanger = SceneChanger.getInstance();
	
	public ArmorySackSwitch() {
		sceneChanger.registerObserver(this);
	}
	
	@Override
	public void execute() {
		super.switchScene(SceneType.SACK);
	}

	@Override
	public void update(SceneType type) {
		// TODO Auto-generated method stub
		
	}

	

}
