package model.factories;

import java.util.ArrayList;
import model.util.GameObject;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import entity.Occupation;
import model.stats.PlayerStats;
import model.stats.StatMaster;
import model.stats.Stats;

/**
 * This class examines a parsed XML file for all entity entries,
 * then uses their attributes to instantiate them.
 * 
 * @author Aidan Pace
 */
public class EntityFactory implements PlaceableObjectFactory{
	
	TakeableFactory inventoryFactory;

	EntityFactory()
	{
		inventoryFactory = new TakeableFactory();
	}
	
	/**
	 * Examine all nodes that are children of the input node,
	 * filter out Entity elements, then use their attributes to instantiate them,
	 * along with relevant items and stats.
	 * 
	 * @author Aidan Pace
	 * @param head the node to begin search at
	 * @return the list of Entities created by this method
	 * @see Entity
	 */
	public ArrayList<GameObject> generate(Element head)
	{
		ArrayList<GameObject> entities = new ArrayList<GameObject>();
		NodeList nodes = head.getElementsByTagName("entity");
		
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Element e = (Element) nodes.item(i);
			
			Element sackHead = (Element) e.getElementsByTagName("sack").item(0);
			Element armoryHead = (Element) e.getElementsByTagName("armory").item(0);
			
			ArrayList<GameObject> sackContents = inventoryFactory.generate(sackHead);
			ArrayList<GameObject> armoryContents = inventoryFactory.generate(armoryHead);
			
			Element s = (Element) e.getElementsByTagName("stats").item(0);
			
			PlayerStats stats = new PlayerStats(
					Integer.parseInt(s.getAttribute("level")),
					Integer.parseInt(s.getAttribute("livesleft")),
					Integer.parseInt(s.getAttribute("strength")),
					Integer.parseInt(s.getAttribute("agility")),
					Integer.parseInt(s.getAttribute("intellect")),
					Integer.parseInt(s.getAttribute("hardiness")),
					Integer.parseInt(s.getAttribute("experience")),
					Integer.parseInt(s.getAttribute("movement")),
					Integer.parseInt(s.getAttribute("hpcurrent")),
					Integer.parseInt(s.getAttribute("mpcurrent")),
					Integer.parseInt(s.getAttribute("hpmax")),
					Integer.parseInt(s.getAttribute("mpmax")),
					Integer.parseInt(s.getAttribute("defense")),
					Integer.parseInt(s.getAttribute("offense")));
			
			Inventory inv = new Inventory(new Sack(), new Armory());
			StatMaster sm = new StatMaster(stats, new Stats());
			
			Entity en = null;
			
			switch(e.getAttribute("occupation")) {
			
			case "smasher":
				Occupation o = new Occupation("Smasher");
				en = new Entity(inv, o, sm);
				break;
				
			case "sneak":
				Occupation o = new Occupation("Sneak");
				en = new Entity(inv, o, sm);
				break;
				
			case "summoner":
				Occupation o = new Occupation("Summoner");
				en = new Entity(inv, o, sm);
				break;
			}
			
			if(en == null) continue;
			
			for(GameObject item : sackContents) {
				en.addItem((Takeable) item);
			}
			
			for(GameObject item : armoryContents) {
				en.addItem((Takeable) item);
				en.equipItem((Equipable) item);
			}
			
			GameMap.getInstance().addEntity(en, new CoordinatePair(Integer.parseInt(e.getAttribute("x")), Integer.parseInt(e.getAttribute("y"))));
			
			entities.add(en);
		}
		
		return entities;
	}
}