
package model.factories;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.gameObject.MapObject;
import model.map.pair.CoordinatePair;
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
    public Tile[][] generate(Element head)
    {
    	Tile[][] tiles;
    	
    	int width , height;
    	width = Integer.parseInt(head.getAttribute("width"));
    	height = Integer.parseInt(head.getAttribute("height"));
    	
    	tiles = new Tile[height][width];
   
    	
    	NodeList nodes = head.getElementsByTagName("tile"); 
    	int count = 0;
    	System.out.println("Length: " + nodes.getLength());
    	for(int i = 0; i < height; ++i)
    	{
    		for(int j = 0; j < width; ++j)
    		{
    			String id = nodes.item(count++).getAttributes().item(0).getTextContent();
    			//id = nodes.item(count++).getAttributes().item(0).getAttributes().toString();
    			//System.out.println("count:" + count +  "id:"  + id);
    			//TODO: change when id's have a meaning corresponding to a Terrain
    			//id = "1";
    			tiles[i][j] = new Tile(new Terrain());
    			tiles[i][j].setID(id);
    			tiles[i][j].setLocation(new CoordinatePair(i , j));
    			
    		}
    	}
    	
    	return tiles;
   
    	
    }
}
