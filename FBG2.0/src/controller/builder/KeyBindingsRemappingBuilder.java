package controller.builder;

import java.util.Map;

import controller.commands.Commandable;
import controller.keyBindings.KeyBindingsOption;
import controller.keyBindings.KeyBindingsUpdate;
import controller.keyBindings.KeyOptions;
import controller.sceneControllers.SceneController;

public class KeyBindingsRemappingBuilder {

	public static SceneController buildController(
			Map<Integer, Commandable> keyBindingsMap,
			Map<KeyBindingsOption, Integer> map,
			KeyBindingsUpdate update,
			Commandable cancelUpdate)
	{
		keyBindingsMap.put(map.get(KeyBindingsOption.PAUSE),cancelUpdate);
		KeyOptions options = new KeyOptions(keyBindingsMap);
		return new SceneController(options);
	}

}
