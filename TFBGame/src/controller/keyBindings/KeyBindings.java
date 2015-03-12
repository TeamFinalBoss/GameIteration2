package controller.keyBindings;

import java.util.HashMap;
import java.util.Map;

import model.util.Saveable;

public class KeyBindings implements Saveable {

	private Map<Integer, KeyBindingsOption> keyBindings; 
	
	public KeyBindings() {
		keyBindings = new HashMap<Integer, KeyBindingsOption>();
	}
	
	public void addBinding(Integer key, KeyBindingsOption value) {
		
	}
	
	public Map<Integer, KeyBindingsOption> getBindings() {
		return this.keyBindings;
	}
	
	
	@Override
	public String toXML() {
		StringBuilder strBuilder = new StringBuilder();
		
		strBuilder.append("<keyBindings>\n");
		
		for(Map.Entry<Integer, KeyBindingsOption> entry: keyBindings.entrySet()) {
			strBuilder.append("<binding ");
			strBuilder.append("key=\"" + entry.getKey() + "\" ");
			strBuilder.append("value=\"" + entry.getValue() + "\"");
			strBuilder.append("\\>");
		}
		
		strBuilder.append("</keyBindings>\n");
		
		return strBuilder.toString();
	}
	
	public KeyBindings updateBindings(KeyBindingsUpdate update) {
		//New key to old key
		KeyBindings updatedBindings = new KeyBindings();
		
		for(Map.Entry<Integer,Integer> entry : update.getBindingsUpdate().entrySet() ) {
			updatedBindings.addBinding(entry.getKey(), this.keyBindings.get(entry.getValue()));
		}
		
		return updatedBindings;
	}

}
