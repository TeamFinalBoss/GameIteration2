package model.factories;

import java.util.ArrayList;
import java.util.List;

import model.map.pair.CoordinatePair;
import model.director.ActiveMapManager;
import model.gameObject.MapObject;
import model.item.DoorOpener;
import model.item.Item;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class examines a parsed XML file for all interactive item entries,
 * then uses their attributes to instantiate them.
 * 
 * @author Aidan Pace
 */
public class InteractiveFactory implements PlaceableObjectFactory{
	public InteractiveFactory() {
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
	public List<MapObject> generate(Element head)
	{
		List<MapObject> items = new ArrayList<MapObject>();
		
		NodeList nodes = head.getElementsByTagName("interactive");
			
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Element item = (Element) nodes.item(i);
			Item it = null;
				
			switch(item.getAttribute("name")){
			case "doorOpener":
				it = new DoorOpener();
				((DoorOpener) it).setLink(Integer.parseInt(item.getAttribute("link")));
				break;
			
			}
				
			if(it == null) continue;
				
			it.setLocation(new CoordinatePair(Integer.parseInt(item.getAttribute("x")), Integer.parseInt(item.getAttribute("y"))));
				
			items.add(it);
		}
		
		return items;
	}
}
