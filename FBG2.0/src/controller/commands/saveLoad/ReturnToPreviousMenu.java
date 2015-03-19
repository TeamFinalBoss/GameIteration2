package controller.commands.saveLoad;

import controller.commands.Commandable;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;
import controller.util.Observer;

public class ReturnToPreviousMenu implements Commandable, Observer {
	
	private SceneType type;
	private SceneChanger changer = SceneChanger.getInstance();
	
	public ReturnToPreviousMenu() {
		type = SceneType.MAIN_MENU;
		changer.registerObserver(this);
	}
	
	@Override
	public void execute() {
		if(type.equals(SceneType.MAIN_MENU)) {
			changer.changeScene(type);
		} else {
			changer.changeScene(SceneType.PAUSE_MENU);
		}
	}

	@Override
	public void update(SceneType type) {
		if(type.equals(SceneType.PAUSE_MENU) || type.equals(SceneType.MAIN_MENU)) {
			this.type = type;
		}
	}

}
