package controller.sceneControllers;

import java.util.ArrayList;
import java.util.List;

import controller.util.Observer;

/**
 * @author Kyle Kyrazis
 * 
 * Exact same thing as old CoordinatorScheduler, but I like this name better...
 *
 */
public class SceneChanger {
	private List<Observer> observers;
	
	private static SceneChanger sceneChanger = null;
	
	private SceneChanger() {
		observers = new ArrayList<Observer>();
	}
	
	public static SceneChanger getInstance() {
		if(sceneChanger == null) {
			sceneChanger = new SceneChanger();
		}
		return sceneChanger;
	}
	
	public void registerObserver(Observer obs) {
		observers.add(obs);
	}
	
	public void unregisterObserver(Observer obs) {
		observers.remove(obs);
	}
	
	public void changeScene(SceneType type) {
		for(Observer obs : observers) {
			obs.update(type);
		}
	}
}
