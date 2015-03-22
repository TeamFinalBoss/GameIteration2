package model.factories;

import java.util.ArrayList;
import java.util.List;

import model.director.ActiveMapManager;
import model.gameObject.MapObject;
import model.item.Item;
import model.item.RecoveryHeart;
import model.map.pair.CoordinatePair;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class examines a parsed XML file for all one shot entries,
 * then uses their attributes to instantiate them.
 * 
 * @author Aidan Pace
 */
public class OneShotFactory implements PlaceableObjectFactory{
	public OneShotFactory() {
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
	public List<MapObject> generate(Element head)
	{
		List<MapObject> items = new ArrayList<MapObject>();
		
		NodeList nodes = head.getElementsByTagName("oneshot");
			
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Element item = (Element) nodes.item(i);
			Item it = null;
				
			switch(item.getAttribute("name")){
			case "recoveryHeart":
				it = new RecoveryHeart();
				break;
			}
				
			if(it == null) continue;
				
			it.setLocation(new CoordinatePair(Integer.parseInt(item.getAttribute("x")), Integer.parseInt(item.getAttribute("y"))));
			
			items.add(it);
		}
		
		return items;
	}
}
