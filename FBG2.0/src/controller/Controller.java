package controller;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.builder.ControllerBuilder;
import controller.builder.DefualtKeyBindingsBuilder;
import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsUpdate;
import controller.mouse.MouseParser;
import controller.sceneControllers.SceneType;

/**
 * @author Kyle Kyrazis
 * 
 * TODO Deal with loading and saving.
 */
public class Controller {
	private KeyBindings keyBindings;
	private KeyDispatcher dispatcher;
	private Map<SceneType, List<Observable>> describeable;
	private KeyListener activeListener;
	private MouseParser parser;
	
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
	
	public KeyBindings getKeyBindings() {
		return keyBindings;
	}
	
	public List<Observable> getObservables(SceneType type) {
		return this.describeable.get(type);
	}
	
	public void addMap(Map<SceneType, List<Observable>> map) {
		describeable = map;
	}
	
	public KeyListener buildController() {
		keyBindings = DefualtKeyBindingsBuilder.buildDefaultKeyBindings();
		return buildController(keyBindings);
	}
	public KeyListener buildController(KeyBindings bindings) {
		if(bindings == null) {
			keyBindings = DefualtKeyBindingsBuilder.buildDefaultKeyBindings();
		} else {
			keyBindings = bindings;
		}

		this.activeListener = ControllerBuilder.build(bindings);
		return this.activeListener;
	}
	
	public void updateControllerKeyBindings(KeyBindingsUpdate bindings) {
		dispatcher.updateKeyOptions(bindings);
		keyBindings.updateBindings(bindings);
		parser.setOptions(keyBindings.getBindingsReverse());
	}

	public void addObserver(Observer o, SceneType type) {
		for(Observable obs : describeable.get(type)) {
			obs.addObserver(o);
		}
	}

	public void setDispatcher(KeyDispatcher keyDispatcher) {
		dispatcher = keyDispatcher;
	}

	public KeyListener getActiveListener() {
		return this.activeListener;
	}

	public void setMouseAdapter(MouseParser parser) {
		this.parser = parser;
	}
	
	public MouseParser getMouseParser() {
		return this.parser;
	}
}
