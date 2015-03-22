package controller.commands.sceneChangers;

import controller.commands.Commandable;
import controller.sceneControllers.SceneType;

public class StatsUpdateSwitch extends SceneChangerCommands implements
		Commandable {

	@Override
	public void execute() {
		super.switchScene(SceneType.STATS_UPDATING);
	}

}
