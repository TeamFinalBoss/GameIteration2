package controller.sceneControllers;

import java.util.ArrayList;
import java.util.List;

import controller.util.SceneObserver;

/**
 * @author Kyle Kyrazis
 * 
 * Exact same thing as old CoordinatorScheduler, but I like this name better...
 *
 */
public class SceneChanger {
	private List<SceneObserver> observers;
	
	private static SceneChanger sceneChanger = null;
	
	private SceneChanger() {
		observers = new ArrayList<SceneObserver>();
	}
	
	public static SceneChanger getInstance() {
		if(sceneChanger == null) {
			sceneChanger = new SceneChanger();
		}
		return sceneChanger;
	}
	
	public void registerObserver(SceneObserver obs) {
		observers.add(obs);
	}
	
	public void unregisterObserver(SceneObserver obs) {
		observers.remove(obs);
	}
	
	public void changeScene(SceneType type) {
		for(SceneObserver obs : observers) {
			obs.update(type);
		}
	}
	
	public List<SceneObserver> getObservers() {
		return observers;
	}
	
	public void clearObservers() {
		this.observers = new ArrayList<>();
	}
}
