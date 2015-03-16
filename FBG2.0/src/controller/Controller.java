package controller;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsUpdate;
import controller.menu.Menu;
import controller.sceneControllers.SceneController;
import controller.sceneControllers.SceneType;
import controller.util.Describeable;

/**
 * @author Kyle Kyrazis
 * 
 * TODO Deal with loading and saving.
 */
public class Controller {
	private KeyBindings keyBindings;
	private Map<SceneType, Observable> describeable;
	
	private static Controller controller = null;
	
	public static Controller getInstance() {
		if(controller == null) {
			controller = new Controller();
		}
		return controller;
	}
	
	private Controller() {
		keyBindings = new KeyBindings();
		describeable = new HashMap<>();
	}
	
	public void addMap(Map<SceneType, Observable> map) {
		describeable = map;
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

	public void addObserver(Observer o, SceneType type) {
		describeable.get(type).addObserver(o);
	}
	
}
