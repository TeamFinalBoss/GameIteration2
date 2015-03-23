package controller.commands.sceneChangers;

import java.io.File;

import model.director.GameDirector;
import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class SummonerGame extends SceneChangerCommands implements Commandable {

	@Override
	public void execute() {
		GameDirector.getGameDirector().startNewGame(new File("./src/resources/saves/default.xml"));
		super.switchScene(SceneType.GAME);
	}

}
