package controller.mouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import view.MousePoint;
import view.MousePointClick;
import view.viewport.DirectionChanger;
import controller.KeyDispatcher;
import controller.keyBindings.KeyBindingsOption;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;
import controller.util.SceneObserver;

public class MouseParser extends MouseAdapter {
	private MouseDispatcher mouseDispatcher;
	
	public MouseParser(MouseDispatcher mouseDispatcher) {
		this.mouseDispatcher = mouseDispatcher;
	}
	
	
	public void mouseClicked(MouseEvent e) {
		mouseDispatcher.mouseClicked(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		mouseDispatcher.mouseMoved(e.getPoint());
	}
	public void mouseWheelMoved(MouseWheelEvent e) {
		mouseDispatcher.mouseWheelMoved(e);
	}

	public void setMousePoint(SceneType type, MousePoint point) {
		mouseDispatcher.addPoint(type,point);
	}
	
	public void setMousePointClick(SceneType type, MousePointClick point) {
		mouseDispatcher.addClickPoint(type, point);
	}
	
	public void setOptions(Map<KeyBindingsOption, Integer> bindingsReverse) {
		mouseDispatcher.setOptions(bindingsReverse);
	}


	public void setDirectionChanger(SceneType game, DirectionChanger mapVp) {
		mouseDispatcher.addDirectionChanger(game,mapVp);
		
	}	
}
