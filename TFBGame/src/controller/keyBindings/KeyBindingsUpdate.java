package controller.keyBindings;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyle Kyrazis
 * 
 * This class is used to map old keys to the new keys so that the controller can
 * be updated correctly.
 *
 */
public class KeyBindingsUpdate {
	private Map<Integer, Integer> updateOptions;
	
	public KeyBindingsUpdate() {
		this.updateOptions = new HashMap<Integer, Integer>();
	}
	
	public KeyBindingsUpdate(Map<Integer, Integer> updateOptions) {
		this.updateOptions = updateOptions;
	}
	
	//TODO do I need to verify key mappings don't overlap here?
	public void addUpdate(Integer key, Integer value) {
		updateOptions.put(key, value);
	}
	
	public Map<Integer, Integer> getBindingsUpdate() {
		return this.updateOptions;
	}
}
