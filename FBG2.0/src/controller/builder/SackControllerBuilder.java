package controller.builder;

import java.util.HashMap;
import java.util.Map;

import controller.commands.Commandable;
import controller.commands.sack.SackConfirm;
import controller.commands.sack.SackDetails;
import controller.commands.sack.SackDown;
import controller.commands.sack.SackDrop;
import controller.commands.sack.SackNext;
import controller.commands.sack.SackPrevious;
import controller.commands.sack.SackUp;
import controller.commands.sceneChangers.ArmorySwitch;
import controller.commands.sceneChangers.ArmorySackSwitch;
import controller.commands.sceneChangers.ResumeGame;
import controller.keyBindings.KeyBindingsOption;
import controller.keyBindings.KeyOptions;
import controller.sceneControllers.SceneController;

public class SackControllerBuilder {

	public static SceneController buildSackController(Map<KeyBindingsOption, Integer> map, SackDetails details) {
		Map<Integer, Commandable> commands = new HashMap<>();
		
		commands.put(map.get(KeyBindingsOption.UP), new SackUp(details));
		commands.put(map.get(KeyBindingsOption.DOWN), new SackDown(details));
		commands.put(map.get(KeyBindingsOption.RIGHT), new SackNext(details));
		commands.put(map.get(KeyBindingsOption.LEFT), new SackPrevious(details));
		commands.put(map.get(KeyBindingsOption.CONFIRM), new SackConfirm(details));
		commands.put(map.get(KeyBindingsOption.ARMORY), new ArmorySwitch());
		commands.put(map.get(KeyBindingsOption.SACK), new ResumeGame());
		commands.put(map.get(KeyBindingsOption.PAUSE), new ResumeGame());
		commands.put(map.get(KeyBindingsOption.DROP), new SackDrop(details));
		
		return new SceneController(new KeyOptions(commands));
	}
	
}
