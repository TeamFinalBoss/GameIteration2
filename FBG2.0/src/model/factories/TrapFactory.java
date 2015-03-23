package model.factories;

import java.util.ArrayList;
import java.util.List;

import model.director.ActiveMapManager;
import model.gameObject.MapObject;
import model.item.Item;
import model.item.RecoveryHeart;
import model.map.pair.CoordinatePair;
import model.map.tile.trap.DealDamageTrap;
import model.map.tile.trap.Trap;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class examines a parsed XML file for all one shot entries,
 * then uses their attributes to instantiate them.
 * 
 * @author Aidan Pace
 */
public class TrapFactory implements PlaceableObjectFactory{
	public TrapFactory() {
	}
	
	/**
	 * Examine all nodes that are children of the input node,
	 * filter out trap elements, then use their attributes to instantiate them
	 * 
	 * @author Aidan Pace
	 * @param head the node to begin search at
	 * @return the list of trap items created by this method
	 * @see Trap
	 */
	public List<MapObject> generate(Element head)
	{
		List<MapObject> items = new ArrayList<MapObject>();
		
		NodeList nodes = head.getElementsByTagName("trap");
			
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Element item = (Element) nodes.item(i);
			Trap it = null;
				
			switch(item.getAttribute("name")){
			case "dealDamageTrap":
				it = new DealDamageTrap();
				it.setID("trap");
				break;
			}
				
			if(it == null) continue;
				
			it.setLocation(new CoordinatePair(Integer.parseInt(item.getAttribute("x")), Integer.parseInt(item.getAttribute("y"))));
			
			items.add(it);
		}
		
		return items;
	}
}
