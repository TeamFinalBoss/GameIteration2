package model.factories;

import java.util.ArrayList;

import model.map.pair.CoordinatePair;
import model.map.GameMap;
import model.gameObject.GameObject;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class examines a parsed XML file for all interactive item entries,
 * then uses their attributes to instantiate them.
 * 
 * @author Aidan Pace
 */
public class InteractiveFactory implements PlaceableObjectFactory{
	InteractiveFactory() {
	}
	
	/**
	 * Examine all nodes that are children of the input node,
	 * filter out Interactive elements, then use their attributes to instantiate them
	 * 
	 * @author Aidan Pace
	 * @param head the node to begin search at
	 * @return the list of Interactive items created by this method
	 * @see Interactive
	 */
	public ArrayList<GameObject> generate(Element head)
	{
		ArrayList<GameObject> items = new ArrayList<GameObject>();
		
		NodeList nodes = head.getElementsByTagName("interactive");
			
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Element item = (Element) nodes.item(i);
			GameObject it = null;
				
			switch(item.getAttribute("name")){
			case "whatever":
				it = new Interactive();
				break;
			}
				
			if(it == null) continue;
				
			GameMap.getInstance().addEntity(it, new CoordinatePair(Integer.parseInt(item.getAttribute("x")), Integer.parseInt(item.getAttribute("y"))));
				
			items.add(it);
		}
		
		return items;
	}
}
