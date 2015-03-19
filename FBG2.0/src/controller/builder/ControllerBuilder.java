package controller.builder;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import model.map.Direction;
import controller.Controller;
import controller.InputParser;
import controller.KeyDispatcher;
import controller.commands.Commandable;
import controller.commands.game.DialogueSwitch;
import controller.commands.game.InventorySwitch;
import controller.commands.game.MoveAvatar;
import controller.commands.game.PauseSwitch;
import controller.commands.game.Skill;
import controller.commands.keyBindings.BindingsUpdate;
import controller.commands.keyBindings.CancelBindingsUpdate;
import controller.commands.keyBindings.SaveBindingsUpdate;
import controller.commands.menu.ConfirmMenuCommand;
import controller.commands.menu.NextMenuCommand;
import controller.commands.menu.PreviousMenuCommand;
import controller.commands.pauseMenu.ExitGame;
import controller.commands.pauseMenu.KeyBindingSwitch;
import controller.commands.pauseMenu.LoadGame;
import controller.commands.pauseMenu.NewGame;
import controller.commands.pauseMenu.ResumeGame;
import controller.commands.pauseMenu.SaveGame;
import controller.commands.saveLoad.LoadFileCommand;
import controller.commands.saveLoad.ReturnToPreviousMenu;
import controller.commands.saveLoad.SaveFileCommand;
import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsOption;
import controller.keyBindings.KeyBindingsUpdate;
import controller.keyBindings.KeyOptions;
import controller.menu.Menu;
import controller.menu.MenuOption;
import controller.menu.Menuable;
import controller.menu.keyBindings.KeyBindingsMenu;
import controller.menu.save.SaveLoadMenu;
import controller.menu.save.SaveOption;
import controller.sceneControllers.SceneController;
import controller.sceneControllers.SceneType;

/**
 * 
 * This class builds the controller. If the controller is set up incorrectly then the error
 * is in this class for sure. Currently only builds the MainMenu and PauseMenu. 
 * TODO deal with saving the keybindings
 * 
 * @author Kyle Kyrazis
 */
public class ControllerBuilder {
	
	private static Controller cont = Controller.getInstance();

	private static KeyBindings bindings; 
	//TODO Fix the fact that these are class level. Issue arises when trying to register as an
	//observer before observer map has been fixed. 
	private static BindingsUpdate update, cancelUpdate, saveUpdate;
	
	public static KeyListener build(KeyBindings keyBindings) {
		bindings = keyBindings;
		Map<KeyBindingsOption, Integer> map = bindings.getBindingsReverse();
		
		/******************************
		 * Main Menu Controller
		 *******************************/
		Menu mainMenu = buildMainMenu();
		Map<Integer, Commandable> mainMenuMap = buildDefaultMenuBindings(mainMenu,map);
		KeyOptions mainMenuOptions = buildMainMenuKeyOptions(mainMenuMap, map);
		SceneController mainMenuController = buildController(mainMenuOptions);
		
		/******************************
		 * Pause Menu Controller
		 *******************************/
		
		Menu pauseMenu = buildPauseMenu();
		Map<Integer, Commandable> pauseMenuMap = buildDefaultMenuBindings(pauseMenu,map);
		KeyOptions pauseMenuOptions = buildPauseMenuKeyOptions(pauseMenuMap, map);
		SceneController pauseMenuController = buildController(pauseMenuOptions);
		
		/******************************
		 * KeyBindingsController
		 *******************************/
		KeyBindingsUpdate update = new KeyBindingsUpdate(new HashMap<Integer, Integer>(), bindings);
		KeyBindingsMenu bindingsMenu = buildBindingsMenu(update);
		Map<Integer, Commandable> keyBindingsMap = buildDefaultMenuBindings(bindingsMenu,map);
		KeyOptions bindingsMenuOptions = buildBindingsMenuKeyOptions(keyBindingsMap, map, update);
		SceneController keyBindingsController = buildController(bindingsMenuOptions);
		
		/******************************
		 * Game Controller
		 *******************************/
		
		KeyOptions gameOptions = buildGameKeyOptions(map);
		SceneController gameController = buildController(gameOptions);
		
		/******************************
		 * Save Menu Controller
		 *******************************/
		
		SaveLoadMenu saveMenu = buildSaveMenu();
		Map<Integer, Commandable> saveMenuMap = buildDefaultMenuBindings(saveMenu,map);
		KeyOptions saveOptions = buildSaveMenuKeyOptions(saveMenuMap, map);
		SceneController saveController = buildController(saveOptions);
		
		/******************************
		 * Load Menu Controller
		 *******************************/
		
		SaveLoadMenu loadMenu = buildLoadMenu();
		Map<Integer, Commandable> loadMenuMap = buildDefaultMenuBindings(loadMenu,map);
		KeyOptions loadOptions = buildLoadMenuKeyOptions(loadMenuMap, map);
		SceneController loadController = buildController(loadOptions);
		
		/******************************
		 * Observers
		 *******************************/
		
		Map<SceneType, Observable> observerMap = new HashMap<>();
		observerMap.put(SceneType.MAIN_MENU, mainMenu);
		observerMap.put(SceneType.PAUSE_MENU, pauseMenu);
		observerMap.put(SceneType.SAVE, saveMenu);
		observerMap.put(SceneType.LOAD, loadMenu);
		observerMap.put(SceneType.KEY_BINDINGS, bindingsMenu);
		
		cont.addMap(observerMap);
		
		ControllerBuilder.update.register();
		cancelUpdate.register();
		saveUpdate.register();
		ControllerBuilder.update.addObserver(bindingsMenu);
		/******************************
		 * Controllers
		 *******************************/
		
		Map<SceneType, SceneController> controllers = new HashMap<SceneType, SceneController>();
		controllers.put(SceneType.MAIN_MENU, mainMenuController);
		controllers.put(SceneType.PAUSE_MENU, pauseMenuController);
		controllers.put(SceneType.KEY_BINDINGS, keyBindingsController);
		controllers.put(SceneType.GAME, gameController);
		controllers.put(SceneType.SAVE, saveController);
		controllers.put(SceneType.LOAD, loadController);
		
		
		KeyDispatcher keyDispatcher = new KeyDispatcher(controllers, mainMenuController);
		cont.setDispatcher(keyDispatcher);
		return new InputParser(keyDispatcher);
	}
	
	

	/**********************************************************************************************
	 * 	   Load Controller builder
	 *
	 ************************************************************************************************/

	private static KeyOptions buildLoadMenuKeyOptions(
			Map<Integer, Commandable> newCommands,
			Map<KeyBindingsOption, Integer> currentBindings )
	{
		
		newCommands.put(currentBindings.get(KeyBindingsOption.PAUSE), new ReturnToPreviousMenu());
		
		return new KeyOptions(newCommands);
	}


	private static SaveLoadMenu buildLoadMenu() {
		List<SaveOption> options = new ArrayList<>();
		for(SaveOption option : SaveOption.values()) {
			options.add(option);
		}
		
		Map<SaveOption, Commandable> map = buildLoadCommands();
		return new SaveLoadMenu(options, SaveOption.OPTION_1, map);
	}


	private static Map<SaveOption, Commandable> buildLoadCommands() {
		Map<SaveOption, Commandable> map = new HashMap<>();
		
		map.put(SaveOption.OPTION_1, new LoadFileCommand(0));
		map.put(SaveOption.OPTION_2, new LoadFileCommand(1));
		map.put(SaveOption.OPTION_3, new LoadFileCommand(2));
		
		return map;
	}



	/**********************************************************************************************
	 * 	   Save Controller builder
	 *
	 ************************************************************************************************/
	
	private static SaveLoadMenu buildSaveMenu() {
		List<SaveOption> options = new ArrayList<>();
		for(SaveOption option : SaveOption.values()) {
			options.add(option);
		}
		
		Map<SaveOption, Commandable> map = buildSaveCommands();
		
		return new SaveLoadMenu(options, SaveOption.OPTION_1, map);
	}


	private static Map<SaveOption, Commandable> buildSaveCommands() {
		Map<SaveOption, Commandable> map = new HashMap<>();
		
		map.put(SaveOption.OPTION_1, new SaveFileCommand(0));
		map.put(SaveOption.OPTION_2, new SaveFileCommand(1));
		map.put(SaveOption.OPTION_3, new SaveFileCommand(2));
		
		return map;
	}
	
	private static KeyOptions buildSaveMenuKeyOptions(
			Map<Integer, Commandable> newCommands,
			Map<KeyBindingsOption, Integer> currentBindings)
	{
		newCommands.put(currentBindings.get(KeyBindingsOption.PAUSE), new PauseSwitch());
	
		return new KeyOptions(newCommands);
	}
	
	/**********************************************************************************************
	 * 	   Game Controller builder
	 *
	 ************************************************************************************************/
	
	private static KeyOptions buildGameKeyOptions(Map<KeyBindingsOption, Integer> map) {
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
		
		options.put(map.get(KeyBindingsOption.INVENTORY), new InventorySwitch());
		options.put(map.get(KeyBindingsOption.DIALOGUE), new DialogueSwitch());
		options.put(map.get(KeyBindingsOption.PAUSE), new PauseSwitch());

		
		
		return new KeyOptions(options);
	}


	/**********************************************************************************************
	 * 	   Key Bindings Controller Builder
	 * @param update 
	 *
	 ************************************************************************************************/
	
	private static KeyOptions buildBindingsMenuKeyOptions(
			Map<Integer, Commandable> newCommands,
			Map<KeyBindingsOption, Integer> currentBindings,
			KeyBindingsUpdate update)
	{
		newCommands.put(
				currentBindings.get(KeyBindingsOption.PAUSE),
				cancelUpdate);
		return new KeyOptions(newCommands);
	}

	private static KeyBindingsMenu buildBindingsMenu(KeyBindingsUpdate update) {
		
		Map<KeyBindingsOption, Commandable> commands = new HashMap<>();

		ControllerBuilder.update = new BindingsUpdate(bindings, update);
		cancelUpdate = new CancelBindingsUpdate(bindings, update);
		saveUpdate= new SaveBindingsUpdate(bindings, update);
		
		for(KeyBindingsOption option : KeyBindingsOption.values()) {
			if(option.equals(KeyBindingsOption.CANCEL)) {
				commands.put(KeyBindingsOption.CANCEL, cancelUpdate);
			} else if(option.equals(KeyBindingsOption.SAVE)) {
				commands.put(KeyBindingsOption.SAVE, saveUpdate);
			} else {
				commands.put(option, ControllerBuilder.update);
			}
		}
		
		return new KeyBindingsMenu(bindings,
				Arrays.asList(KeyBindingsOption.values()),
				update,
				KeyBindingsOption.UP,
				commands);
	}

	/**********************************************************************************************
	 * 	   PauseMenu Builder
	 *
	 ************************************************************************************************/

	private static Map<MenuOption, Commandable> buildPauseMenuCommands() {
		Map<MenuOption, Commandable> pauseMenuCommands = new HashMap<MenuOption, Commandable>();
		
		pauseMenuCommands.put(MenuOption.RESUME_GAME, new ResumeGame());
		pauseMenuCommands.put(MenuOption.SAVE_GAME, new SaveGame());
		pauseMenuCommands.put(MenuOption.LOAD_GAME, new LoadGame());
		pauseMenuCommands.put(MenuOption.KEY_BINDINGS, new KeyBindingSwitch());
		pauseMenuCommands.put(MenuOption.NEW_GAME, new NewGame());
		pauseMenuCommands.put(MenuOption.EXIT_GAME, new ExitGame());
		
		return pauseMenuCommands;
	}

	private static KeyOptions buildPauseMenuKeyOptions(
			Map<Integer, Commandable> newCommands,
			Map<KeyBindingsOption, Integer> currentBindings)
	{
		newCommands.put(currentBindings.get(KeyBindingsOption.PAUSE), new ResumeGame());
		
		return new KeyOptions(newCommands);
	}
	
	private static Menu buildPauseMenu() {
		List<MenuOption> options = new ArrayList<MenuOption>();
		options.add(MenuOption.RESUME_GAME);
		options.add(MenuOption.SAVE_GAME);
		options.add(MenuOption.LOAD_GAME);
		options.add(MenuOption.KEY_BINDINGS);
		options.add(MenuOption.NEW_GAME);
		options.add(MenuOption.EXIT_GAME);
		
		Map<MenuOption, Commandable> pauseMenuCommands = buildPauseMenuCommands();
		
		return new Menu(options, MenuOption.RESUME_GAME, pauseMenuCommands);
	}

	/**********************************************************************************************
	 * 	  Main Menu Builder
	 *
	 ************************************************************************************************/
	
	private static KeyOptions buildMainMenuKeyOptions(
			Map<Integer, Commandable> newCommands,
			Map<KeyBindingsOption, Integer> currentBindings)
	{
		return new KeyOptions(newCommands);
	}

	
	
	
	private static Menu buildMainMenu() {
		List<MenuOption> options = new ArrayList<MenuOption>();
		options.add(MenuOption.NEW_GAME);
		options.add(MenuOption.LOAD_GAME);
		options.add(MenuOption.EXIT_GAME);
		
		Map<MenuOption, Commandable> mainMenuCommands = buildMainMenuCommands();
		
		return new Menu(options, MenuOption.NEW_GAME, mainMenuCommands);
	}

	private static Map<MenuOption, Commandable> buildMainMenuCommands() {
		Map<MenuOption,Commandable> menuCommands = new HashMap<MenuOption, Commandable>();
		
		menuCommands.put(MenuOption.NEW_GAME, new NewGame());
		menuCommands.put(MenuOption.LOAD_GAME, new LoadGame());
		menuCommands.put(MenuOption.EXIT_GAME, new ExitGame());
		
		return menuCommands;
	}

	
	/**********************************************************************************************
	 * 	  Default Builders
	 *
	 ************************************************************************************************/
	
	private static Map<Integer, Commandable> buildDefaultMenuBindings(
			Menuable menu,
			Map<KeyBindingsOption, Integer> currentBindings)
	{
		Map<Integer, Commandable> options = new HashMap<Integer, Commandable>();
		
		options.put(currentBindings.get(KeyBindingsOption.DOWN), new NextMenuCommand(menu));
		options.put(currentBindings.get(KeyBindingsOption.UP), new PreviousMenuCommand(menu));
		options.put(currentBindings.get(KeyBindingsOption.CONFIRM), new ConfirmMenuCommand(menu));

		return options;
	}
	
	
	private static SceneController buildController(KeyOptions options) {
		return new SceneController(options);
	}
	
}
