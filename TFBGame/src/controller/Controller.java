package controller;

import java.awt.event.KeyListener;

import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsUpdate;
import controller.menu.Menu;

/**
 * @author Kyle Kyrazis
 * 
 * TODO Deal with loading and saving.
 */
public class Controller {
	private KeyBindings keyBindings;
	private Menu activeMenu;
	
	private static Controller controller = null;
	
	public static Controller getInstance() {
		if(controller == null) {
			controller = new Controller();
		}
		return controller;
	}
	
	private Controller() {
	}
	
	public KeyListener buildController() {
		keyBindings = ControllerBuilder.buildDefaultKeyBindings();
		return ControllerBuilder.build(keyBindings);
	}
	public KeyListener buildController(KeyBindings bindings) {
		keyBindings = bindings;
		return ControllerBuilder.build(bindings);
	}
	
	public void updateControllerKeyBindings(KeyBindingsUpdate bindings) {
		//TODO fill in this
	}
	
	public Menu getActiveMenu() {
		return activeMenu;
	}
	
	public void setActiveMenu(Menu menu) {
		this.activeMenu = menu;
	}
	
}
