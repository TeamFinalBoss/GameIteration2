package controller.mouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;
import java.util.Map;

import view.MousePoint;
import controller.KeyDispatcher;
import controller.keyBindings.KeyBindingsOption;

public class MouseParser extends MouseAdapter {
	private Map<KeyBindingsOption, Integer> options;
	private KeyDispatcher dispatcher;
	//TODO migrate this to a observer
	private MousePoint point;
	
	public MouseParser() {
		this.options = new HashMap<>();
		this.dispatcher = new KeyDispatcher();
	}
	
	public MouseParser(Map<KeyBindingsOption, Integer> map, KeyDispatcher dispatcher) {
		this.options = map;
		this.dispatcher = dispatcher;
	}
	
	public void setOptions(Map<KeyBindingsOption, Integer> map) {
		this.options = map;
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		int value = point.getActiveLocation(e.getPoint());
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

	public void setMousePoint(MousePoint menuVP) {
		this.point = menuVP;
	}
}
