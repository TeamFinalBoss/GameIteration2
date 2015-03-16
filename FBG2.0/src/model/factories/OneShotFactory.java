package model.factories;

import java.util.ArrayList;

import model.map.pair.CoordinatePair;
import model.util.GameObject;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class examines a parsed XML file for all one shot entries,
 * then uses their attributes to instantiate them.
 * 
 * @author Aidan Pace
 */
public class OneShotFactory implements PlaceableObjectFactory{
	OneShotFactory() {
	}
	
	/**
	 * Examine all nodes that are children of the input node,
	 * filter out OneShot elements, then use their attributes to instantiate them
	 * 
	 * @author Aidan Pace
	 * @param head the node to begin search at
	 * @return the list of One Shot items created by this method
	 * @see OneShot
	 */
	public ArrayList<GameObject> generate(Element head)
	{
		ArrayList<GameObject> items = new ArrayList<GameObject>();
		
		NodeList nodes = head.getElementsByTagName("oneshot");
			
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Element item = (Element) nodes.item(i);
			GameObject it = null;
				
			switch(item.getAttribute("name")){
			case "whatever":
				it = new OneShot();
				break;
			}
				
			if(it == null) continue;
				
			GameMap.getInstance().addEntity(it, new CoordinatePair(Integer.parseInt(item.getAttribute("x")), Integer.parseInt(item.getAttribute("y"))));
				
			items.add(it);
		}
		
		return items;
	}
}
