package controller.commands.sceneChangers;

import java.util.Observable;

import controller.commands.Commandable;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;
import controller.util.Errorable;

public class SwitchToUpdate extends Observable implements Commandable, Errorable {

	SceneChanger changer = SceneChanger.getInstance();
	
	@Override
	public void execute() {
		setChanged();
		notifyObservers();
		changer.changeScene(SceneType.UPDATING);
	}

	@Override
	public String getErrorString() {
		return "Press any Key to change the mapping.";
	}

}
