package controller.commands.sceneChangers;

import model.director.GameDirector;
import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class SummonerGame extends SceneChangerCommands implements Commandable {

	@Override
	public void execute() {
		GameDirector.getGameDirector().startNewGame(def);
		super.switchScene(SceneType.GAME);
	}

}
