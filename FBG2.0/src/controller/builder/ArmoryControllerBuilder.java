package controller.builder;

import java.util.HashMap;
import java.util.Map;

import controller.commands.Commandable;
import controller.commands.armory.ArmoryConfrim;
import controller.commands.armory.ArmoryDetails;
import controller.commands.armory.ArmoryDown;
import controller.commands.armory.ArmoryNext;
import controller.commands.armory.ArmoryPrevious;
import controller.commands.armory.ArmoryUp;
import controller.commands.sceneChangers.ArmorySackSwitch;
import controller.commands.sceneChangers.ResumeGame;
import controller.commands.sceneChangers.SackSwitch;
import controller.keyBindings.KeyBindingsOption;
import controller.keyBindings.KeyOptions;
import controller.sceneControllers.SceneController;

public class ArmoryControllerBuilder {

	public static SceneController buildArmoryController(
			Map<KeyBindingsOption, Integer> map, 
			ArmoryDetails details) {
		Map<Integer, Commandable> commands = new HashMap<>();
		
		commands.put(map.get(KeyBindingsOption.UP), new ArmoryUp(details));
		commands.put(map.get(KeyBindingsOption.DOWN), new ArmoryDown(details));
		commands.put(map.get(KeyBindingsOption.RIGHT), new ArmoryNext(details));
		commands.put(map.get(KeyBindingsOption.LEFT), new ArmoryPrevious(details));
		commands.put(map.get(KeyBindingsOption.CONFIRM), new ArmoryConfrim(details));
		commands.put(map.get(KeyBindingsOption.ARMORY), new ResumeGame());
		commands.put(map.get(KeyBindingsOption.SACK), new SackSwitch());
		commands.put(map.get(KeyBindingsOption.PAUSE), new ResumeGame());
		
		return new SceneController(new KeyOptions(commands));
	}
}
