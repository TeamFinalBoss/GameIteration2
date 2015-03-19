package model.factories;

import java.util.ArrayList;
import java.util.List;

import model.map.pair.CoordinatePair;
import model.director.ActiveMapManager;
import model.entity.Entity;
import model.factories.PlaceableObjectFactory;
import model.gameObject.MapObject;
import model.item.Item;
import model.item.Takeable;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class examines a parsed XML file for all takeable entries,
 * then uses their attributes to instantiate them.
 * 
 * @author Aidan Pace
 */
public class TakeableFactory implements PlaceableObjectFactory{
	TakeableFactory() {
	}
	
	/**
	 * Examine all nodes that are children of the input node,
	 * filter out Takeable elements, then use their attributes to instantiate them
	 * 
	 * @author Aidan Pace
	 * @param head the node to begin search at
	 * @return the list of Takeable items created by this method
	 * @see Takeable
	 */
	public List<MapObject> generate(Element head)
	{
		ArrayList<MapObject> items = new ArrayList<MapObject>();
		
		if(head.getTagName().equals("sack") || head.getTagName().equals("armory")) {
			NodeList nodes = head.getElementsByTagName("takeableItem");
			
			for(int i = 0; i < nodes.getLength(); i++)
			{
				Element item = (Element) nodes.item(i);
				Item it = null;
				
				switch(item.getAttribute("name")){
				case "whatever":
					//it = new Takeable("whatever", "it does nothing", Integer.parseInt(item.getAttribute("durability")));
					break;
				}
				
				if(it == null) continue;
				
				items.add(it);
			}
			
		} else {
			NodeList nodes = head.getElementsByTagName("takeable");
			
			for(int i = 0; i < nodes.getLength(); i++)
			{
				Element item = (Element) nodes.item(i);
				Item it = null;
				
				switch(item.getAttribute("name")){
				case "whatever":
					//it = new Takeable("whatever", "it does nothing", Integer.parseInt(item.getAttribute("durability")), new CoordinatePair(Integer.parseInt(item.getAttribute("x")), Integer.parseInt(item.getAttribute("y"))));
					break;
				}
				
				if(it == null) continue;
				
				ActiveMapManager.getInstance().addItemToActiveMap(it, new CoordinatePair(Integer.parseInt(item.getAttribute("x")), Integer.parseInt(item.getAttribute("y"))));
				
				items.add(it);
			}
		}
		
		return items;
	}
}
