package model.factories;

import java.util.ArrayList;
import java.util.List;

import model.effect.Dispellable;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsOption;

/**
 * This class examines a parsed XML file for all effect entries,
 * then uses their attributes to instantiate them.
 * 
 * @author Aidan Pace
 */
public class BindingReader {
	public BindingReader() {
	}
	
	/**
	 * Examine all nodes that are children of the input node,
	 * filter out effect elements, then use their attributes to instantiate them
	 * 
	 * @author Aidan Pace
	 * @param head the node to begin search at
	 * @return the list of effects created by this method
	 * @see Dispellable
	 */
	public KeyBindings generate(Element head)
	{
		KeyBindings b = new KeyBindings();
		NodeList nodes = head.getElementsByTagName("binding");
			
		for(int i = 0; i < nodes.getLength(); i++)
		{				
			Element item = (Element) nodes.item(i);
			
			int key = Integer.parseInt(item.getAttribute("key"));
			String value = item.getAttribute("value");
			
			KeyBindingsOption o = KeyBindingsOption.fromString(value);
			b.addBinding(key, o);
		}
		
		return b;
	}
}
