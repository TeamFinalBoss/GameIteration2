package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.commands.Commandable;
import controller.commands.keyBindings.BindingsUpdate;
import controller.commands.keyBindings.CancelBindingsUpdate;
import controller.commands.keyBindings.SaveBindingsUpdate;
import controller.commands.keyBindings.menu.ConfirmBindingsMenuCommand;
import controller.commands.keyBindings.menu.NextBindingsMenuCommand;
import controller.commands.keyBindings.menu.PreviousBindingsMenuCommand;
import controller.commands.menu.ConfirmMenuCommand;
import controller.commands.menu.NextMenuCommand;
import controller.commands.menu.PreviousMenuCommand;
import controller.commands.pauseMenu.ExitGame;
import controller.commands.pauseMenu.KeyBindingSwitch;
import controller.commands.pauseMenu.LoadGame;
import controller.commands.pauseMenu.NewGame;
import controller.commands.pauseMenu.ResumeGame;
import controller.commands.pauseMenu.SaveGame;
import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsOption;
import controller.keyBindings.KeyOptions;
import controller.menu.Menu;
import controller.menu.MenuOption;
import controller.menu.keyBindings.KeyBindingsMenu;
import controller.sceneControllers.SceneController;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis
 *
 * This class builds the controller. If the controller is set up incorrectly then the error
 * is in this class for sure. Currently only builds the MainMenu and PauseMenu. 
 * TODO deal with saving the keybindings
 */
public class ControllerBuilder {
	
	private static Controller cont = Controller.getInstance();

	private static KeyBindings bindings; 
	
	public static KeyListener build(KeyBindings keyBindings) {
		
		bindings = keyBindings;
		
		Menu mainMenu = buildMainMenu();
		
		Menu pauseMenu = buildPauseMenu();
		KeyOptions mainMenuOptions = buildMainMenuKeyOptions(mainMenu);
		SceneController mainMenuController = buildMainMenuController(mainMenuOptions);
		KeyOptions pauseMenuOptions = buildPauseMenuKeyOptions(pauseMenu);
		SceneController pauseMenuController = buildPauseMenuController(pauseMenuOptions);
		
		KeyBindingsMenu bindingsMenu = buildBindingsMenu();
		KeyOptions bindingsMenuOptions = buildBindingsMenuKeyOptions(bindingsMenu);
		SceneController keyBindingsController = buildBindingsController(bindingsMenuOptions);
		
		KeyOptions gameOptions = buildGameKeyOptions();
		SceneController gameController = buildGameController(gameOptions);
		
		Map<SceneType, Observable> observerMap = new HashMap<>();
		observerMap.put(SceneType.MAIN_MENU, mainMenu);
		observerMap.put(SceneType.PAUSE_MENU, pauseMenu);
		observerMap.put(SceneType.KEY_BINDINGS, bindingsMenu);
		
		cont.addMap(observerMap);
		
		Map<SceneType, SceneController> controllers = new HashMap<SceneType, SceneController>();
		controllers.put(SceneType.MAIN_MENU, mainMenuController);
		controllers.put(SceneType.PAUSE_MENU, pauseMenuController);
		controllers.put(SceneType.KEY_BINDINGS, keyBindingsController);
		
		
		
		KeyDispatcher keyDispatcher = new KeyDispatcher(controllers, mainMenuController);
		return new InputParser(keyDispatcher);
	}
	
	
	/**********************************************************************************************
	 * 	   Key Bindings Controller Builder
	 *
	 ************************************************************************************************/
	
	private static SceneController buildGameController(KeyOptions gameOptions) {
		return null;
	}


	private static KeyOptions buildGameKeyOptions() {
		// TODO Auto-generated method stub
		return null;
	}


	/**********************************************************************************************
	 * 	   Key Bindings Controller Builder
	 *
	 ************************************************************************************************/
	
	private static SceneController buildBindingsController(
			KeyOptions bindingsMenuOptions) {
		return new SceneController(bindingsMenuOptions);
	}

	private static KeyOptions buildBindingsMenuKeyOptions(
			KeyBindingsMenu bindingsMenu) {
		Map<Integer, Commandable> options = new HashMap<Integer, Commandable>();
		for(Map.Entry<Integer, KeyBindingsOption> entry : bindings.getBindings().entrySet()) {
			switch(entry.getValue()) {
				case DOWN :
					options.put(entry.getKey(), new PreviousBindingsMenuCommand(bindingsMenu));
					break;
				case UP:
					options.put(entry.getKey(), new NextBindingsMenuCommand(bindingsMenu));
					break;
				case CONFIRM :
					options.put(entry.getKey(), new ConfirmBindingsMenuCommand(bindingsMenu));
					break;
				case PAUSE : 
					options.put(entry.getKey(), new CancelBindingsUpdate());
				default :
					break;	
			}
		}
		return new KeyOptions(options);
	}

	private static KeyBindingsMenu buildBindingsMenu() {
		
		Map<KeyBindingsOption, Commandable> commands = new HashMap<>();
		
		for(KeyBindingsOption option : KeyBindingsOption.values()) {
			if(option.equals(KeyBindingsOption.CANCEL)) {
				commands.put(KeyBindingsOption.CANCEL, new CancelBindingsUpdate());
			} else if(option.equals(KeyBindingsOption.SAVE)) {
				commands.put(KeyBindingsOption.SAVE, new SaveBindingsUpdate());
			} else {
				//TODO Iron out the key switching.
				commands.put(option, new BindingsUpdate());
			}
		}
		
		return new KeyBindingsMenu(bindings,
				Arrays.asList(KeyBindingsOption.values()),
				KeyBindingsOption.UP,
				commands);
	}

	/**********************************************************************************************
	 * 	   PauseMenu Builder
	 *
	 ************************************************************************************************/
	
	private static SceneController buildPauseMenuController(KeyOptions pauseMenuOptions) {
		return new SceneController(pauseMenuOptions);
	}
	
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

	private static KeyOptions buildPauseMenuKeyOptions(Menu pauseMenu) {
		Map<Integer, Commandable> options = new HashMap<Integer, Commandable>();
		for(Map.Entry<Integer, KeyBindingsOption> entry : bindings.getBindings().entrySet()) {
			switch(entry.getValue()) {
				case DOWN :
					options.put(entry.getKey(), new PreviousMenuCommand(pauseMenu));
					break;
				case UP:
					options.put(entry.getKey(), new NextMenuCommand(pauseMenu));
					break;
				case CONFIRM :
					options.put(entry.getKey(), new ConfirmMenuCommand(pauseMenu));
					break;
				default :
					break;	
			}
		}
		return new KeyOptions(options);
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
		
		return new Menu(options, MenuOption.SAVE_GAME, pauseMenuCommands);
	}

	/**********************************************************************************************
	 * 	  Main Menu Builder
	 *
	 ************************************************************************************************/
	
	private static SceneController buildMainMenuController(KeyOptions mainMenuOptions) {
		return new SceneController(mainMenuOptions);
	}

	private static KeyOptions buildMainMenuKeyOptions(Menu mainMenu) {
		Map<Integer, Commandable> options = new HashMap<Integer, Commandable>();
		
		for(Map.Entry<Integer, KeyBindingsOption> entry : bindings.getBindings().entrySet()) {
			switch(entry.getValue()) {
				case DOWN :
					options.put(entry.getKey(), new PreviousMenuCommand(mainMenu));
					break;
				case UP:
					options.put(entry.getKey(), new NextMenuCommand(mainMenu));
					break;
				case CONFIRM :
					options.put(entry.getKey(), new ConfirmMenuCommand(mainMenu));
					break;
				default :
					break;	
			}
		}
		return new KeyOptions(options);
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
	 * 	  Default Key Bindings Builder
	 *
	 ************************************************************************************************/
	
	public static KeyBindings buildDefaultKeyBindings() {
		KeyBindings bindings = new KeyBindings();
		
		bindings.addBinding(KeyEvent.VK_Z, KeyBindingsOption.DOWN_LEFT);
		bindings.addBinding(KeyEvent.VK_X, KeyBindingsOption.DOWN);
		bindings.addBinding(KeyEvent.VK_C, KeyBindingsOption.DOWN_RIGHT);
		bindings.addBinding(KeyEvent.VK_D, KeyBindingsOption.RIGHT);
		bindings.addBinding(KeyEvent.VK_E, KeyBindingsOption.UP_RIGHT);
		bindings.addBinding(KeyEvent.VK_W, KeyBindingsOption.UP);
		bindings.addBinding(KeyEvent.VK_Q, KeyBindingsOption.UP_LEFT);
		bindings.addBinding(KeyEvent.VK_A, KeyBindingsOption.LEFT);
		bindings.addBinding(KeyEvent.VK_ENTER, KeyBindingsOption.CONFIRM);
		bindings.addBinding(KeyEvent.VK_SHIFT, KeyBindingsOption.DIALOGUE);
		bindings.addBinding(KeyEvent.VK_S, KeyBindingsOption.DROP);
		bindings.addBinding(KeyEvent.VK_I, KeyBindingsOption.INVENTORY);
		bindings.addBinding(KeyEvent.VK_TAB, KeyBindingsOption.NEAREST_ENTITY);
		bindings.addBinding(KeyEvent.VK_ESCAPE, KeyBindingsOption.PAUSE);
		bindings.addBinding(KeyEvent.VK_1, KeyBindingsOption.SKILL_1);
		bindings.addBinding(KeyEvent.VK_2, KeyBindingsOption.SKILL_2);
		bindings.addBinding(KeyEvent.VK_3, KeyBindingsOption.SKILL_3);
		bindings.addBinding(KeyEvent.VK_4, KeyBindingsOption.SKILL_4);
		bindings.addBinding(KeyEvent.VK_5, KeyBindingsOption.SKILL_5);
		bindings.addBinding(KeyEvent.VK_6, KeyBindingsOption.SKILL_6);
		bindings.addBinding(KeyEvent.VK_7, KeyBindingsOption.SKILL_7);
		bindings.addBinding(KeyEvent.VK_8, KeyBindingsOption.SKILL_8);
		bindings.addBinding(KeyEvent.VK_9, KeyBindingsOption.SKILL_9);
		bindings.addBinding(KeyEvent.VK_0, KeyBindingsOption.SKILL_0);
		bindings.addBinding(KeyEvent.VK_T, KeyBindingsOption.TILE_INFO);
		
		return bindings;
	}
	
}
