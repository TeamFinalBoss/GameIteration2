package controller.builder;

import java.util.HashMap;
import java.util.Map;

import controller.commands.Commandable;
import controller.commands.game.dialogue.Purchase;
import controller.commands.game.dialogue.Sell;
import controller.commands.sack.SackDetails;
import controller.commands.sack.SackDown;
import controller.commands.sack.SackNext;
import controller.commands.sack.SackPrevious;
import controller.commands.sack.SackUp;
import controller.commands.sceneChangers.DialogueSwitch;
import controller.commands.sceneChangers.StoreFrontSwitch;
import controller.keyBindings.KeyBindingsOption;
import controller.keyBindings.KeyOptions;
import controller.sceneControllers.SceneController;

public class StoreInventoryControllerBuilder {

	public static SceneController buildStoreFrontController(
			Map<KeyBindingsOption, Integer> map, SackDetails details) {
		
		Map<Integer, Commandable> commands = new HashMap<>();
		
		commands.put(map.get(KeyBindingsOption.UP), new SackUp(details));
		commands.put(map.get(KeyBindingsOption.DOWN), new SackDown(details));
		commands.put(map.get(KeyBindingsOption.RIGHT), new SackNext(details));
		commands.put(map.get(KeyBindingsOption.LEFT), new SackPrevious(details));
		commands.put(map.get(KeyBindingsOption.CONFIRM), new Sell(details));
		commands.put(map.get(KeyBindingsOption.PAUSE), new DialogueSwitch());
		commands.put(map.get(KeyBindingsOption.SACK), new StoreFrontSwitch());
		
		return new SceneController(new KeyOptions(commands));
	}

}
