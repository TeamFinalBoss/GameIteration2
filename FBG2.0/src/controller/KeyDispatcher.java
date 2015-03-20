package controller;

import java.util.HashMap;
import java.util.Map;

import controller.keyBindings.KeyBindingsUpdate;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneController;
import controller.sceneControllers.SceneType;
import controller.util.SceneObserver;

/**
 * @author Kyle Kyrazis
 * 
 * Maintains all the Controllers and forwards key presses to the current controller.
 * Able to update itself whenever the controller needs to switch.
 *
 */
public class KeyDispatcher implements SceneObserver {
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
	
	public void updateKeyOptions(KeyBindingsUpdate update) {
		for(Map.Entry<SceneType,SceneController> entry : sceneControllers.entrySet()) {
			entry.getValue().updateKeyOptions(update);
		}
	}
	
	public SceneChanger getSceneChanger() {
		return this.sceneChanger;
	}

	public SceneController getActiveController() {
		return activeController;
	}

	public void setActiveController(SceneController activeController) {
		this.activeController = activeController;
	}
}
