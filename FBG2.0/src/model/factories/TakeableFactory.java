package model.factories;

import java.util.ArrayList;
import java.util.List;

import model.map.pair.CoordinatePair;
import model.director.ActiveMapManager;
import model.factories.PlaceableObjectFactory;
import model.gameObject.MapObject;
import model.item.Gun;
import model.item.Halo;
import model.item.HealthPotion;
import model.item.HermesBoots;
import model.item.Item;
import model.item.JesusBoots;
import model.item.Mace;
import model.item.RustyKnife;
import model.item.Stick;
import model.item.Sword;
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
	public TakeableFactory() {
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
				Item it = switchName(item);
				
				if(it == null) continue;
				
				items.add(it);
			}
			
		} else {
			NodeList nodes = head.getElementsByTagName("takeable");
			
			for(int i = 0; i < nodes.getLength(); i++)
			{
				Element item = (Element) nodes.item(i);
				Item it = switchName(item);
				
				if(it == null) continue;
				it.setLocation(new CoordinatePair(Integer.parseInt(item.getAttribute("x")), Integer.parseInt(item.getAttribute("y"))));
				
				items.add(it);
			}
		}
		
		return items;
	}
	
	private Item switchName(Element item){
		Item it = null;
		
		switch(item.getAttribute("name")){
		case "gun":
			it = new Gun();
			break;
			
		case "healthpotion":
			it = new HealthPotion();
			break;
			
		case "halo":
			it = new Halo();
			break;
			
		case "hermesboots":
			it = new HermesBoots();
			break;
			
		case "jesusboots":
			it = new JesusBoots();
			break;
		
		case "rustyknife":
			it = new RustyKnife();
			break;
			
		case "mace":
			it = new Mace();
			break;
			
		case "stick":
			it = new Stick();
			break;
			
		case "sword":
			it = new Sword();
			break;
		}
		
		((Takeable) it).setDurability(Integer.parseInt(item.getAttribute("durability")));
		
		return it;
	}
}
