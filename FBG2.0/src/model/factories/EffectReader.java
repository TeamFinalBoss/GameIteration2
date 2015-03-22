package model.factories;

import java.util.ArrayList;
import java.util.List;
import model.effect.Dispellable;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class examines a parsed XML file for all effect entries,
 * then uses their attributes to instantiate them.
 * 
 * @author Aidan Pace
 */
public class EffectReader {
	public EffectReader() {
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
	public List<Dispellable> generate(Element head)
	{
		ArrayList<Dispellable> items = new ArrayList<Dispellable>();
		NodeList nodes = head.getElementsByTagName("effect");
			
		for(int i = 0; i < nodes.getLength(); i++)
		{				
			Element item = (Element) nodes.item(i);
			Dispellable e = null;
			
			switch(item.getAttribute("name")) {
			case "waiting...":
				//e = new ...
				break;
			}
				
			if(e == null) continue;
				
			items.add(e);
		}
		
		return items;
	}
}
