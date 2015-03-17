package controller.keyBindings;

import java.util.HashMap;
import java.util.Map;

import controller.commands.Commandable;

/**
 * @author Kyle Kyrazis
 * 
 * Class for mapping Key presses to commands.
 *
 */
public class KeyOptions {
	Map<Integer,Commandable> keyOptions;
	
	public KeyOptions() {
		keyOptions = new HashMap<Integer,Commandable>();
	}
	
	public KeyOptions(Map<Integer,Commandable> keyOptions) {
		this.keyOptions = keyOptions;
	}
	
	public Map<Integer,Commandable> getKeyOptions() {
		return this.keyOptions;
	}
	
	public void useKey(Integer key) {
		if(keyOptions.containsKey(key)) {
			keyOptions.get(key).execute();
		}
	}
	
	public void update(KeyBindingsUpdate update) {
		Map<Integer, Commandable> newOptions = new HashMap<Integer,Commandable>();
		for(Map.Entry<Integer, Integer> entry : update.getBindingsUpdate().entrySet()) {
			newOptions.put(entry.getValue(), keyOptions.get(entry.getKey()));
		}
		
		for(Map.Entry<Integer, Commandable> entry : keyOptions.entrySet()) {
			if(!(update.getBindingsUpdate().containsKey(entry.getKey()))) {
				newOptions.put(entry.getKey(), entry.getValue());
			}
		}
		
		this.keyOptions = newOptions;
		
	}
}
