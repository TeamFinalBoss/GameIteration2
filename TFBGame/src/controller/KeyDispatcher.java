package controller;

import java.util.HashMap;
import java.util.Map;

import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneController;
import controller.sceneControllers.SceneType;
import controller.util.Observer;

/**
 * @author Kyle Kyrazis
 * 
 * Maintains all the Controllers and forwards key presses to the current controller.
 * Able to update itself whenever the controller needs to switch.
 *
 */
public class KeyDispatcher implements Observer {
	private SceneController activeController;
	private Map<SceneType, SceneController> sceneControllers;
	private SceneChanger sceneChanger = SceneChanger.getInstance();
	
	public KeyDispatcher() {
		sceneControllers = new HashMap<SceneType, SceneController>();
		sceneChanger.registerObserver(this);
	}
	
	public KeyDispatcher(Map<SceneType, SceneController> sceneControllers,
			SceneController activeController) {
		this.activeController = activeController;
		this.sceneControllers = sceneControllers;
		sceneChanger.registerObserver(this);
	}
	/**
	 * Forwards a character to the active controller and executes the command.
	 * @param key
	 */
	public void useKey(Integer key) {
		activeController.useKey(key);
	}
	
	/**
	 * Changes the active controller based on the SceneType
	 * @param SceneType type
	 */
	public void update(SceneType type) {
		activeController = sceneControllers.get(type);
	}
}
