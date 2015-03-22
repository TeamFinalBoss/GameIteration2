
package model.factories;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.gameObject.MapObject;
import model.map.tile.*;

/**
 *
 * This class parses a map file for all the tiles and instantiates them
 *
 * @author Hanif 
 * 
 * This class 
 */
public class TileFactory {
    public List<Tile> generate(Element head)
    {
    	List<Tile> items = new ArrayList<Tile>();
    	
    	NodeList nodes = head.getElementsByTagName("tile");
    	
    	
    	
		return null;
    	
    }
}
