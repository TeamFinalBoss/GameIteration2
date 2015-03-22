package controller.mouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;
import java.util.Map;

import view.MousePoint;
import view.MousePointClick;
import controller.KeyDispatcher;
import controller.keyBindings.KeyBindingsOption;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;
import controller.util.SceneObserver;

public class MouseDispatcher implements SceneObserver{
	private Map<KeyBindingsOption, Integer> options;
	private KeyDispatcher dispatcher;
	private SceneType currentType;
	private Map<SceneType, MousePoint> observers;
	private Map<SceneType, MousePointClick> clickObservers;
	
	public MouseDispatcher() {
		this.options = new HashMap<>();
		this.dispatcher = new KeyDispatcher();
		observers = new HashMap<>();
		clickObservers = new HashMap<>();
		SceneChanger.getInstance().registerObserver(this);
	}
	
	public MouseDispatcher(Map<KeyBindingsOption, Integer> map, KeyDispatcher dispatcher) {
		this.options = map;
		this.dispatcher = dispatcher;
		observers = new HashMap<>();
		clickObservers = new HashMap<>();
		SceneChanger.getInstance().registerObserver(this);
	}
	
	public void setOptions(Map<KeyBindingsOption, Integer> map) {
		this.options = map;
	}

	//TODO fix issue with attempting to drop items
	public void mouseClicked(MouseEvent e) {
		if(observers.containsKey(currentType)) {
			int value = observers.get(currentType).getActiveLocation(e.getPoint());
			if(value >= 0) {
				Integer key = options.get(KeyBindingsOption.CONFIRM);
				dispatcher.useKey(key);
			}
		}
		if(clickObservers.containsKey(currentType)) {
			clickObservers.get(currentType).verifyChange(e.getPoint());
		}
	}

	public void mouseMoved(Point point) {
		if(observers.containsKey(currentType)) {
 			observers.get(currentType).getActiveLocation(point);
		}
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		KeyBindingsOption option;
		int count = e.getWheelRotation();
		if(count < 0) {
			option = KeyBindingsOption.UP;
			count *=-1;
		} else {
			option = KeyBindingsOption.DOWN;
		}
		
		for(int i = 0; i < count; i++) {
			Integer value = options.get(option);
			dispatcher.useKey(value);
		}
		
	}
	
	@Override
	public void update(SceneType type) {
		this.currentType = type;
	}

	public void addPoint(SceneType type, MousePoint point) {
		observers.put(type,point);
	}
	
	public void addClickPoint(SceneType type, MousePointClick point) {
		clickObservers.put(type, point);
	}
	
}
