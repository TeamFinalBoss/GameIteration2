package controller.sceneControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import controller.keyBindings.KeyOptions;

/**
 * @author Kyle Kyrazis
 * 
 * Controllers for executing expected commands from the user.
 * TODO add methods for updating KeyOptions.
 */
public class SceneController {
	private KeyOptions keyOptions;

	public void useKey(Integer key) {
		keyOptions.useKey(key);
	}
	
	public SceneController() {
		keyOptions = new KeyOptions();
	}
	
	public SceneController(KeyOptions keyOptions) {
		this.keyOptions = keyOptions;
	}
	
	
}
