package controller.builder;

import java.util.HashMap;
import java.util.Map;

import controller.commands.Commandable;
import controller.commands.game.dialogue.Purchase;
import controller.commands.sack.SackConfirm;
import controller.commands.sack.SackDetails;
import controller.commands.sack.SackDown;
import controller.commands.sack.SackDrop;
import controller.commands.sack.SackNext;
import controller.commands.sack.SackPrevious;
import controller.commands.sack.SackUp;
import controller.commands.sceneChangers.ArmorySwitch;
import controller.commands.sceneChangers.DialogueSwitch;
import controller.commands.sceneChangers.ResumeGame;
import controller.commands.sceneChangers.SackArmorySwitch;
import controller.keyBindings.KeyBindingsOption;
import controller.keyBindings.KeyOptions;
import controller.sceneControllers.SceneController;

public class StorefrontControllerBuilder {
	public static SceneController buildStoreFrontController(Map<KeyBindingsOption, Integer> map, SackDetails details) {
		Map<Integer, Commandable> commands = new HashMap<>();
		
		commands.put(map.get(KeyBindingsOption.UP), new SackUp(details));
		commands.put(map.get(KeyBindingsOption.DOWN), new SackDown(details));
		commands.put(map.get(KeyBindingsOption.RIGHT), new SackNext(details));
		commands.put(map.get(KeyBindingsOption.LEFT), new SackPrevious(details));
		commands.put(map.get(KeyBindingsOption.CONFIRM), new Purchase(details));
		commands.put(map.get(KeyBindingsOption.PAUSE), new DialogueSwitch());

		
		return new SceneController(new KeyOptions(commands));
	}
}
