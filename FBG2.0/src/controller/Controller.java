package controller;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.builder.ControllerBuilder;
import controller.builder.DefualtKeyBindingsBuilder;
import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsUpdate;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis
 * 
 * TODO Deal with loading and saving.
 */
public class Controller {
	private KeyBindings keyBindings;
	private KeyDispatcher dispatcher;
	private Map<SceneType, Observable> describeable;
	private KeyListener activeListener;
	
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
		keyBindings = DefualtKeyBindingsBuilder.buildDefaultKeyBindings();
		return buildController(keyBindings);
	}
	public KeyListener buildController(KeyBindings bindings) {
		keyBindings = bindings;
		this.activeListener = ControllerBuilder.build(bindings);
		return this.activeListener;
	}
	
	public void updateControllerKeyBindings(KeyBindingsUpdate bindings) {
		dispatcher.updateKeyOptions(bindings);
		keyBindings.updateBindings(bindings);
	}

	public void addObserver(Observer o, SceneType type) {
		describeable.get(type).addObserver(o);
	}

	public void setDispatcher(KeyDispatcher keyDispatcher) {
		dispatcher = keyDispatcher;
	}

	public KeyListener getActiveListener() {
		return this.activeListener;
	}
	
}
