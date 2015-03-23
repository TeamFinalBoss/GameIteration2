
package model.factories;


import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.entity.MotionType;
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

    	for(int i = 0; i < height; ++i)
    	{
    		for(int j = 0; j < width; ++j)
    		{
    			
    			
    			String id = nodes.item(count++).getAttributes().item(0).getTextContent();
    			if(id.equals("1"))
    			{
    	
    				tiles[i][j] = new Tile(new Terrain());
        			tiles[i][j].setID(id);
        			tiles[i][j].setLocation(new CoordinatePair(i , j));
        			tiles[i][j].setTerrain(new Terrain());
    			}
    			
    			else if(id.equals("2"))
    			{
    				tiles[i][j] = new Tile(new Terrain());
        			tiles[i][j].setID(id);
        			tiles[i][j].setLocation(new CoordinatePair(i , j));
        			tiles[i][j].setTerrain(new Terrain("Water", "blah" , MotionType.WATER));
    			}
    			
    			else if(id.equals("3"))
    			{
    				tiles[i][j] = new Tile(new Terrain());
        			tiles[i][j].setID(id);
        			tiles[i][j].setLocation(new CoordinatePair(i , j));
        			tiles[i][j].setTerrain(new Terrain("Mountain", "blah" , MotionType.UNATTAINABLE));
    			}
    			
    			
    			else if(id.equals("4"))
    			{
    				tiles[i][j] = new Tile(new Terrain());
        			tiles[i][j].setID(id);
        			tiles[i][j].setLocation(new CoordinatePair(i , j));
        			tiles[i][j].setTerrain(new Terrain());
    			}
    			
    			else if(id.equals("5"))
    			{
    				tiles[i][j] = new Tile(new Terrain());
        			tiles[i][j].setID(id);
        			tiles[i][j].setLocation(new CoordinatePair(i , j));
        			tiles[i][j].setTerrain(new Terrain("Water", "blah" , MotionType.WATER));
    			}
    			
    			
    			else if(id.equals("5"))
    			{
    				tiles[i][j] = new Tile(new Terrain());
        			tiles[i][j].setID(id);
        			tiles[i][j].setLocation(new CoordinatePair(i , j));
        			tiles[i][j].setTerrain(new Terrain("Mountain", "blah" , MotionType.UNATTAINABLE));
    			}
    			
    			
    		}
    	}
    	
    	return tiles;
   
    	
    }
}
