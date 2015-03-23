package model.map.tile;

import model.entity.MotionType;

/**
 * This class represents the basic display unit in-game. Tiles contain a terrain and may contain a decal, 
 * area effect, and a trap.
 * 
 * 
 * @author Michael Cohen
 *
 */
public class Tile {

	Terrain terrain;
	
	
        /**
         * By default return an empty tile of default terrain
         */
	public Tile(){
            this(new Terrain());
	}
	
        /**
         * 
         */
	public Tile(Terrain t){
		terrain = t;
	
	}
	
        /**
	 * Returns this tile's MotionType requirement
	 * 
	 * @author Jason Owens
	 */
	public MotionType getMotionType(){
		return terrain.getMotiontype();
	}
        
	/**
	 * Adds a terrain to this tile
	 * 
	 * @author Michael Cohen
	 * @param t the terrain to add to the tile
	 */
	public void addTerrain(Terrain t){
		if (terrain != null)
			throw new RuntimeException("This tile already has a terriain");
		else
			terrain = t;
	}
	
	/**
	 * Removes the terrain from this tile
	 * 
	 * @author Michael Cohen
	 */
	public void removeTerrain(){
		terrain = null;
	}


	
	
	
}
