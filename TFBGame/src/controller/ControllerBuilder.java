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
import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsOption;
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
 * TODO deal with saving the keybindings
 */
public class ControllerBuilder {
	
	private static Controller cont = Controller.getInstance();

	private static KeyBindings bindings; 
	
	public static KeyListener build(KeyBindings keyBindings) {
		
		bindings = keyBindings;
		
		Menu mainMenu = buildMainMenu();
		
		cont.setActiveMenu(mainMenu);
		
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

	private static SceneController buildPauseMenuController(KeyOptions pauseMenuOptions) {
		return new SceneController(pauseMenuOptions);
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

	private static Menu buildPauseMenu() {
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

	private static Menu buildMainMenu() {
		List<MenuOption> options = new ArrayList<MenuOption>();
		options.add(MenuOption.NEW_GAME);
		options.add(MenuOption.LOAD_GAME);
		options.add(MenuOption.KEY_BINDINGS);
		options.add(MenuOption.EXIT_GAME);
		
		//TODO: Add commands associated with each options
		
		return new Menu(options, MenuOption.NEW_GAME, new HashMap<MenuOption,Commandable>());
	}

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
