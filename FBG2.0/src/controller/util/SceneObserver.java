package controller.util;

import controller.sceneControllers.SceneType;

/**
 * @author Kyle
 * Used to update Observers whenever a scene changes.
 *
 */
public interface SceneObserver {
	/**
	 * Pushes the type that is changed to.
	 * @param SceneType type
	 */
	public void update(SceneType type);
}
