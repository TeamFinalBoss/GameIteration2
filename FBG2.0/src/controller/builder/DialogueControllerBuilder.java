package controller.builder;

import java.util.HashMap;
import java.util.Map;

import controller.commands.Commandable;
import controller.commands.game.dialogue.DialogueCommand;
import controller.commands.sceneChangers.ResumeGame;
import controller.keyBindings.KeyBindingsOption;
import controller.keyBindings.KeyOptions;
import controller.menu.dialogue.DialogueMenu;
import controller.sceneControllers.SceneController;

public class DialogueControllerBuilder {

	
	public static SceneController buildDialogueController(Map<KeyBindingsOption, Integer> map, DialogueMenu menu) {
		Map<Integer, Commandable> newCommands = new HashMap<>();
		newCommands.put(map.get(KeyBindingsOption.PAUSE), new ResumeGame());
		newCommands.put(map.get(KeyBindingsOption.DIALOGUE), new ResumeGame());
		newCommands.put(map.get(KeyBindingsOption.SKILL_1), new DialogueCommand(1));
		newCommands.put(map.get(KeyBindingsOption.SKILL_2), new DialogueCommand(2));
		newCommands.put(map.get(KeyBindingsOption.SKILL_3), new DialogueCommand(3));
		newCommands.put(map.get(KeyBindingsOption.SKILL_4), new DialogueCommand(4));
		newCommands.put(map.get(KeyBindingsOption.SKILL_5), new DialogueCommand(5));
		newCommands.put(map.get(KeyBindingsOption.SKILL_6), new DialogueCommand(6));
		newCommands.put(map.get(KeyBindingsOption.SKILL_7), new DialogueCommand(7));
		newCommands.put(map.get(KeyBindingsOption.SKILL_8), new DialogueCommand(8));
		newCommands.put(map.get(KeyBindingsOption.SKILL_9), new DialogueCommand(9));
		
		KeyOptions bindings = new KeyOptions(newCommands);
		return new SceneController(bindings);
	}
}
