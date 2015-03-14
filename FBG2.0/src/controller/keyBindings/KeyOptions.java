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
}
