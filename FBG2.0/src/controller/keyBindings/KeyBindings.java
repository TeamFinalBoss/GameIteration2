package controller.keyBindings;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import model.util.Saveable;

/**
 * @author Kyle Kyrazis
 * 
 * This class is used to map Key Presses to the potential options.
 *
 */
public class KeyBindings implements Saveable{

	private Map<Integer, KeyBindingsOption> keyBindings; 
	
	public KeyBindings() {
		keyBindings = new HashMap<Integer, KeyBindingsOption>();
	}
	
	public KeyBindings(Map<Integer, KeyBindingsOption> map) {
		this.keyBindings = map;
	}
	
	public void addBinding(Integer key, KeyBindingsOption value) {
		keyBindings.put(key, value);
	}
	
	public Map<Integer, KeyBindingsOption> getBindings() {
		return this.keyBindings;
	}
	
	public Map<KeyBindingsOption, Integer> getBindingsReverse() {
		Map<KeyBindingsOption, Integer> reverseMap = new HashMap<>();
		for(Entry<Integer, KeyBindingsOption> entry : keyBindings.entrySet()) {
			reverseMap.put(entry.getValue(), entry.getKey());
		}
		return reverseMap;
	}
	
	
	@Override
	public String toXML() {
		StringBuilder strBuilder = new StringBuilder();
		
		strBuilder.append("<keyBindings>\n");
		
		for(Map.Entry<Integer, KeyBindingsOption> entry: keyBindings.entrySet()) {
			strBuilder.append("<binding ");
			strBuilder.append("key=\"" + entry.getKey() + "\" ");
			strBuilder.append("value=\"" + entry.getValue() + "\"");
			strBuilder.append("\\>\n");
		}
		strBuilder.append("</keyBindings>\n");
		
		return strBuilder.toString();
	}
	
	public KeyBindings updateBindings(KeyBindingsUpdate update) {
		//New key to old key
		KeyBindings updatedBindings = new KeyBindings();
		
		for(Map.Entry<Integer,Integer> entry : update.getBindingsUpdate().entrySet() ) {
			if(keyBindings.containsKey(entry.getValue())) {
				updatedBindings.addBinding(entry.getKey(), this.keyBindings.get(entry.getValue()));
			}
		}
		
		return updatedBindings;
	}

	public String[] getDescription() {
		String[] strArray = new String[keyBindings.size()];
		
		int i = 0;
		for(Map.Entry<Integer, KeyBindingsOption> entry : keyBindings.entrySet()) {
			strArray[i] = (entry.getValue().toString() + " " + KeyEvent.getKeyText(entry.getKey()));
			++i;
		}
		return strArray;
	}

	

}
