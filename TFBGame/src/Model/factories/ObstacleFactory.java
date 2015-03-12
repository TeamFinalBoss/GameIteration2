package model.factories;

import java.util.ArrayList;

import model.map.CoordinatePair;
import model.util.GameObject;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class examines a parsed XML file for all obstacle entries,
 * then uses their attributes to instantiate them.
 * 
 * @author Aidan Pace
 */
public class ObstacleFactory implements PlaceableObjectFactory{
	ObstacleFactory() {
	}
	
	/**
	 * Examine all nodes that are children of the input node,
	 * filter out Obstacle elements, then use their attributes to instantiate them
	 * 
	 * @author Aidan Pace
	 * @param head the node to begin search at
	 * @return the list of Obstacles created by this method
	 * @see Obstacle
	 */
	public ArrayList<GameObject> generate(Element head)
	{
		ArrayList<GameObject> items = new ArrayList<GameObject>();
		
		NodeList nodes = head.getElementsByTagName("obstacle");
			
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Element item = (Element) nodes.item(i);
			GameObject it = null;
				
			switch(item.getAttribute("name")){
			case "whatever":
				it = new Obstacle();
				break;
			}
				
			if(it == null) continue;
				
			GameMap.getInstance().addEntity(it, new CoordinatePair(Integer.parseInt(item.getAttribute("x")), Integer.parseInt(item.getAttribute("y"))));
				
			items.add(it);
		}
		
		return items;
	}
}
