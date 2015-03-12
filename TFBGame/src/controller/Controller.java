package controller;

import java.awt.event.KeyListener;

import controller.keyBindings.KeyBindings;

/**
 * @author Kyle Kyrazis
 * 
 * TODO Deal with loading and saving.
 */
public class Controller {
	private KeyBindings keyBindings;
	
	public KeyListener buildController() {
		keyBindings = ControllerBuilder.buildDefaultKeyBindings();
		return ControllerBuilder.build(keyBindings);
	}
	public KeyListener buildController(KeyBindings bindings) {
		keyBindings = bindings;
		return ControllerBuilder.build(bindings);
	}
}
