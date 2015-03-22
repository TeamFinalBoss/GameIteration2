package controller.builder;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import controller.Controller;
import controller.InputParser;
import controller.KeyDispatcher;
import controller.commands.Commandable;
import controller.commands.armory.ArmoryDetails;
import controller.commands.keyBindings.BindingsUpdate;
import controller.commands.keyBindings.CancelBindingsUpdate;
import controller.commands.keyBindings.SaveBindingsUpdate;
import controller.commands.menu.ConfirmMenuCommand;
import controller.commands.menu.NextMenuCommand;
import controller.commands.menu.PreviousMenuCommand;
import controller.commands.pauseMenu.ExitGame;
import controller.commands.sack.SackDetails;
import controller.commands.saveLoad.LoadFileCommand;
import controller.commands.saveLoad.ReturnToPreviousMenu;
import controller.commands.saveLoad.SaveFileCommand;
import controller.commands.sceneChangers.KeyBindingSwitch;
import controller.commands.sceneChangers.LoadGame;
import controller.commands.sceneChangers.NewGame;
import controller.commands.sceneChangers.PauseSwitch;
import controller.commands.sceneChangers.ResumeGame;
import controller.commands.sceneChangers.SaveGame;
import controller.commands.sceneChangers.SwitchToUpdate;
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
import controller.mouse.MouseDispatcher;
import controller.mouse.MouseParser;
import controller.sceneControllers.SceneController;
import controller.sceneControllers.SceneType;

/**
 * 
 * This class builds the controller. If the controller is set up incorrectly then the error
 * is in this class for sure. Currently only builds the MainMenu and PauseMenu. 
 * 
 * @author Kyle Kyrazis
 */
public class ControllerBuilder {
	
	private static Controller cont = Controller.getInstance();

	private static KeyBindings bindings; 
	
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
		 * Game Controller
		 *******************************/
		
		SceneController gameController = GameControllerBuilder.buildGameController(map);
		
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
		 * KeyBindingsController
		 *******************************/
		KeyBindingsUpdate update = new KeyBindingsUpdate(new HashMap<Integer, Integer>(), bindings);
		
		BindingsUpdate cancelUpdate = new CancelBindingsUpdate(bindings, update);
		BindingsUpdate saveUpdate= new SaveBindingsUpdate(bindings, update);
		Observable updatingSwitch = new SwitchToUpdate();
		
		KeyBindingsMenu bindingsMenu = buildBindingsMenu(update,
				(Commandable)cancelUpdate, (Commandable)saveUpdate, (Commandable)updatingSwitch);
		Map<Integer, Commandable> keyBindingsMap = buildDefaultMenuBindings(bindingsMenu,map);
		
		
		
		SceneController keyBindingsController = KeyBindingsRemappingBuilder.buildController(
				keyBindingsMap, map, update, (Commandable)cancelUpdate);
		
		/******************************
		 * Updating Controller
		 *******************************/
		BindingsUpdate bindingsUpdate = new BindingsUpdate(keyBindings, update);
		SceneController updateController =
				UpdatingControllerBuilder.buildUpdatingSceneController(update, keyBindings, bindingsUpdate);
		
		/******************************
		 * Sack Controller
		 *******************************/
		SackDetails details = new SackDetails();
		SceneController sackController = SackControllerBuilder.buildSackController(map, details);
		
		/******************************
		 * Armory Controller
		 *******************************/
		ArmoryDetails armoryDetails = new ArmoryDetails();
		SceneController armoryController = ArmoryControllerBuilder.buildArmoryController(map, armoryDetails);
		
		/******************************
		 * Observers
		 *******************************/
		
		Map<SceneType, List<Observable>> observerMap = new HashMap<>();
		
		List<Observable> mainMenuObervables = new ArrayList<>();
		mainMenuObervables.add(mainMenu);
		
		List<Observable> pauseMenuObservables = new ArrayList<>();
		pauseMenuObservables.add(pauseMenu);
		
		List<Observable> saveMenuObservables = new ArrayList<>();
		saveMenuObservables.add(saveMenu);
		
		List<Observable> loadMenuObservables = new ArrayList<>();
		loadMenuObservables.add(loadMenu);
		
		List<Observable> keyBindingsObservables = new ArrayList<>();
		keyBindingsObservables.add(bindingsMenu);
		
		List<Observable> updateObservables = new ArrayList<>();
		updateObservables.add(bindingsUpdate);
		updateObservables.add(updatingSwitch);
		
		List<Observable> sackObservables = new ArrayList<>();
		sackObservables.add(details);
		
		List<Observable> armoryObservables = new ArrayList<>();
		armoryObservables.add(armoryDetails);
		
		observerMap.put(SceneType.MAIN_MENU, mainMenuObervables);
		observerMap.put(SceneType.PAUSE_MENU, pauseMenuObservables);
		observerMap.put(SceneType.SAVE, saveMenuObservables);
		observerMap.put(SceneType.LOAD, loadMenuObservables);
		observerMap.put(SceneType.KEY_BINDINGS, keyBindingsObservables);
		observerMap.put(SceneType.UPDATING, updateObservables);
		observerMap.put(SceneType.SACK,sackObservables);
		observerMap.put(SceneType.ARMORY, armoryObservables);
		
		cont.addMap(observerMap);
		
		bindingsUpdate.register();
		cancelUpdate.register();
		saveUpdate.register();
		bindingsUpdate.addObserver(bindingsMenu);
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
		controllers.put(SceneType.UPDATING, updateController);
		controllers.put(SceneType.SACK, sackController);
		controllers.put(SceneType.ARMORY, armoryController);
		
		
		KeyDispatcher keyDispatcher = new KeyDispatcher(controllers, mainMenuController);
		cont.setDispatcher(keyDispatcher);
		
		MouseDispatcher mouseDispatcher = new MouseDispatcher(map,keyDispatcher);
		MouseParser parser = new MouseParser(mouseDispatcher);
		cont.setMouseAdapter(parser);
		
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
	 * 	   Key Bindings Controller Builder
	 * @param update 
	 *
	 ************************************************************************************************/
	


	private static KeyBindingsMenu buildBindingsMenu(KeyBindingsUpdate update, Commandable cancelUpdate,
			Commandable saveUpdate, Commandable updatingSwitch) {
		
		Map<KeyBindingsOption, Commandable> commands = new HashMap<>();
		
		
		for(KeyBindingsOption option : KeyBindingsOption.values()) {
			if(option.equals(KeyBindingsOption.CANCEL)) {
				commands.put(KeyBindingsOption.CANCEL, cancelUpdate);
			} else if(option.equals(KeyBindingsOption.SAVE)) {
				commands.put(KeyBindingsOption.SAVE, saveUpdate);
			} else {
				commands.put(option, updatingSwitch);
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
