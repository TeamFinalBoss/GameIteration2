package controller.commands.sceneChangers;

import java.io.File;

import model.director.GameDirector;
import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class SneakGame extends SceneChangerCommands implements Commandable {

	@Override
	public void execute() {
		GameDirector.getGameDirector().startNewGame(new File("./src/resources/saves/defaultSneak.xml"));
		super.switchScene(SceneType.GAME);
	}

}
