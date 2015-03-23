package controller.builder;

import java.util.HashMap;
import java.util.Map;

import model.map.Direction;
import controller.commands.Commandable;
import controller.commands.game.GetInformation;
import controller.commands.game.MoveAvatar;
import controller.commands.game.Skill;
import controller.commands.sceneChangers.ArmorySwitch;
import controller.commands.sceneChangers.DialogueSwitch;
import controller.commands.sceneChangers.PauseSwitch;
import controller.commands.sceneChangers.SackSwitch;
import controller.commands.sceneChangers.StatsUpdateSwitch;
import controller.keyBindings.KeyBindingsOption;
import controller.keyBindings.KeyOptions;
import controller.sceneControllers.SceneController;

public class GameControllerBuilder {

	/**********************************************************************************************
	 * 	   Game Controller builder
	 *
	 ************************************************************************************************/
	
	public static SceneController buildGameController(
			Map<KeyBindingsOption, Integer> map)
	{
		Map<Integer, Commandable> options = new HashMap<Integer, Commandable>();
			
		options.put(map.get(KeyBindingsOption.UP), new MoveAvatar(Direction.North));
		options.put(map.get(KeyBindingsOption.UP_LEFT), new MoveAvatar(Direction.NorthWest));
		options.put(map.get(KeyBindingsOption.UP_RIGHT), new MoveAvatar(Direction.NorthEast));
		options.put(map.get(KeyBindingsOption.DOWN), new MoveAvatar(Direction.South));
		options.put(map.get(KeyBindingsOption.DOWN_LEFT), new MoveAvatar(Direction.SouthWest));
		options.put(map.get(KeyBindingsOption.DOWN_RIGHT), new MoveAvatar(Direction.SouthEast));
		options.put(map.get(KeyBindingsOption.LEFT), new MoveAvatar(Direction.West));
		options.put(map.get(KeyBindingsOption.RIGHT), new MoveAvatar(Direction.East));
			
		options.put(map.get(KeyBindingsOption.SKILL_0), new Skill(0));
		options.put(map.get(KeyBindingsOption.SKILL_1), new Skill(1));
		options.put(map.get(KeyBindingsOption.SKILL_2), new Skill(2));
		options.put(map.get(KeyBindingsOption.SKILL_3), new Skill(3));
		options.put(map.get(KeyBindingsOption.SKILL_4), new Skill(4));
		options.put(map.get(KeyBindingsOption.SKILL_5), new Skill(5));
		options.put(map.get(KeyBindingsOption.SKILL_6), new Skill(6));
		options.put(map.get(KeyBindingsOption.SKILL_7), new Skill(7));
		options.put(map.get(KeyBindingsOption.SKILL_8), new Skill(8));
		options.put(map.get(KeyBindingsOption.SKILL_9), new Skill(9));
		
		options.put(map.get(KeyBindingsOption.TILE_INFO), new GetInformation());	
		options.put(map.get(KeyBindingsOption.SACK), new SackSwitch());
		options.put(map.get(KeyBindingsOption.ARMORY), new ArmorySwitch());
		options.put(map.get(KeyBindingsOption.DIALOGUE), new DialogueSwitch());
		options.put(map.get(KeyBindingsOption.PAUSE), new PauseSwitch());
		options.put(map.get(KeyBindingsOption.STATS_UPDATE), new StatsUpdateSwitch());
		
		KeyOptions keyOptions =  new KeyOptions(options);
		return new SceneController(keyOptions);
	}

}
