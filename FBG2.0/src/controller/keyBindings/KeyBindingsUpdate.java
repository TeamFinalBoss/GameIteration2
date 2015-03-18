package controller.keyBindings;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import controller.Controller;

/**
 * @author Kyle Kyrazis
 * 
 * This class is used to map old keys to the new keys so that the controller can
 * be updated correctly.
 *
 */
public class KeyBindingsUpdate {
	
	// OLD TO NEW IS THE MAPPING!
	private Map<Integer, Integer> updateOptions;
	private KeyBindings currentBindings;
	private KeyBindings newBindings;
	
	public KeyBindingsUpdate() {
		this.updateOptions = new HashMap<Integer, Integer>();
	}
	
	public KeyBindingsUpdate(Map<Integer, Integer> updateOptions, KeyBindings currentBindings) {
		this.updateOptions = updateOptions;
		this.currentBindings = currentBindings;
		this.newBindings = currentBindings.clone();
	}
	
	//TODO do I need to verify key mappings don't overlap here?
	public void addUpdate(Integer key, Integer value) {
		if(!(newBindings.getBindings().containsKey(value))) {
			if(updateOptions.containsKey(key)) {
				updateOptions.remove(key);
			}
			if(updateOptions.containsValue(value)) {
				throw new IllegalArgumentException("Sorry but you can't have multiple options controlled by the same key");
			}
			updateOptions.put(key, value);
			newBindings.updateBindingsKeyValue(key, value);
		} else {
			throw new IllegalArgumentException("Sorry but you can't multiple options to the same key");
		}
	}
	
	public Map<Integer, Integer> getBindingsUpdate() {
		return this.updateOptions;
	}
	
	public String getValue(Integer key) {
		return this.updateOptions.get(key) == null ? null : KeyEvent.getKeyText(this.updateOptions.get(key));
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Map.Entry<Integer, Integer> entry : updateOptions.entrySet()) {
			builder.append(KeyEvent.getKeyText(entry.getKey()));
			builder.append("\t");
			builder.append(KeyEvent.getKeyText(entry.getValue()));
			builder.append("\n");
		}
		return builder.toString();
	}

	
	//TODO might be alot of errors
	public void clear() {
		updateOptions.clear();
		newBindings.clear();
		newBindings = currentBindings.clone();
	}
	
}
