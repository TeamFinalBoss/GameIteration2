package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.commands.Commandable;
import controller.commands.ConfirmMenuCommand;
import controller.commands.NextMenuCommand;
import controller.commands.PreviousMenuCommand;
import controller.keyBindings.KeyOptions;
import controller.menu.Menu;
import controller.menu.MenuOption;
import controller.sceneControllers.SceneController;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis
 *
 * This class builds the controller. If the controller is set up incorrectly then the error
 * is in this class for sure. Currently only builds the MainMenu and PauseMenu.
 */
public class ControllerBuilder {

	public KeyListener build() {
		Menu mainMenu = buildMainMenu();
		Menu pauseMenu = buildPauseMenu();
		KeyOptions mainMenuOptions = buildMainMenuKeyOptions(mainMenu);
		SceneController mainMenuController = buildMainMenuController(mainMenuOptions);
		KeyOptions pauseMenuOptions = buildPauseMenuKeyOptions(pauseMenu);
		SceneController pauseMenuController = buildPauseMenuController(pauseMenuOptions);
		
		Map<SceneType, SceneController> controllers = new HashMap<SceneType, SceneController>();
		controllers.put(SceneType.MAIN_MENU, mainMenuController);
		controllers.put(SceneType.PAUSE_MENU, pauseMenuController);
		
		KeyDispatcher keyDispatcher = new KeyDispatcher(controllers, mainMenuController);
		return new InputParser(keyDispatcher);
	}

	private SceneController buildPauseMenuController(KeyOptions pauseMenuOptions) {
		return new SceneController(pauseMenuOptions);
	}

	private KeyOptions buildPauseMenuKeyOptions(Menu pauseMenu) {
		Map<Integer, Commandable> options = new HashMap<Integer, Commandable>();
		options.put(KeyEvent.VK_NUMPAD2, new PreviousMenuCommand(pauseMenu));
		options.put(KeyEvent.VK_NUMPAD8, new NextMenuCommand(pauseMenu));
		options.put(KeyEvent.VK_ENTER, new ConfirmMenuCommand(pauseMenu));
		
		return new KeyOptions(options);
	}

	private SceneController buildMainMenuController(KeyOptions mainMenuOptions) {
		return new SceneController(mainMenuOptions);
	}

	private KeyOptions buildMainMenuKeyOptions(Menu mainMenu) {
		Map<Integer, Commandable> options = new HashMap<Integer, Commandable>();
		options.put(KeyEvent.VK_NUMPAD2, new PreviousMenuCommand(mainMenu));
		options.put(KeyEvent.VK_NUMPAD8, new NextMenuCommand(mainMenu));
		options.put(KeyEvent.VK_ENTER, new ConfirmMenuCommand(mainMenu));
		
		return new KeyOptions(options);
	}

	private Menu buildPauseMenu() {
		List<MenuOption> options = new ArrayList<MenuOption>();
		options.add(MenuOption.RESUME_GAME);
		options.add(MenuOption.SAVE_GAME);
		options.add(MenuOption.LOAD_GAME);
		options.add(MenuOption.KEY_BINDINGS);
		options.add(MenuOption.NEW_GAME);
		options.add(MenuOption.EXIT_GAME);
		
		//TODO: Add commands associated with each options
		
		return new Menu(options, MenuOption.SAVE_GAME, new HashMap<MenuOption,Commandable>());
	}

	private Menu buildMainMenu() {
		List<MenuOption> options = new ArrayList<MenuOption>();
		options.add(MenuOption.NEW_GAME);
		options.add(MenuOption.LOAD_GAME);
		options.add(MenuOption.KEY_BINDINGS);
		options.add(MenuOption.EXIT_GAME);
		
		//TODO: Add commands associated with each options
		
		return new Menu(options, MenuOption.NEW_GAME, new HashMap<MenuOption,Commandable>());
	}
	
}
