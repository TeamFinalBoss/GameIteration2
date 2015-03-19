package controller.keyBindings;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	
	//TODO OLD to NEW
	public void addUpdate(Integer key, Integer value) {
		if(!(newBindings.containsKey(value))) {
			Integer newKey = key;
			if(updateOptions.containsKey(key)) {
				newKey = updateOptions.remove(key);
			}
			if(updateOptions.containsValue(value)) {
				throw new IllegalArgumentException("Unable to map multiple keys to the same option.");
			}
			updateOptions.put(key, value);
			newBindings.updateBindingsKeyValue(newKey, value);
		} else {
			throw new IllegalArgumentException("Unable to map multiple keys to the same option.");
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
	
	public Set<Map.Entry<Integer, Integer>> getSet() {
		return this.updateOptions.entrySet();
	}
	
	public void clear() {
		updateOptions.clear();
		newBindings = currentBindings.clone();
	}
	
}
