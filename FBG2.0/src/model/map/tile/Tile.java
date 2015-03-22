package model.map.tile;

import model.map.areaEffect.AreaEffect;
import model.map.tile.trap.Trap;
import model.entity.Entity;
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
	AreaEffect effect;
	Trap tileTrap;
	Decal decal;
	
        /**
         * By default return an empty tile of default terrain
         */
	public Tile(){
            this(new Terrain(), null, null, null );
	}
	
        /**
         * 
         */
	public Tile(Terrain t, AreaEffect e, Trap tr, Decal d){
		terrain = t;
		effect = e;
		tileTrap = tr;
		decal = d;
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
	
	/**
	 * Adds a decal to this tile
	 * 
	 * @author Michael Cohen
	 * @param d the decal to add to the Tile
	 */
	public void addDecal(Decal d){
		if (decal != null)
			throw new RuntimeException("This tile already has a decal");
		else 
			decal = d;
	}
	
	/**
	 * Removes the trap from this tile
	 * 
	 * @author Michael Cohen
	 */
	public void removeDecal(){
		decal = null;
	}
	
	/**
	 * Adds a trap to this tile
	 * 
	 * @author Michael Cohen
	 * @param t the trap to add to the tile
	 */
	public void addTrap(Trap t){
		if (tileTrap != null)
			throw new RuntimeException("This tile already has a Trap");
		else
			tileTrap = t;
	}
	
	/**
	 * Removes the trap from this tile
	 * 
	 * @author Michael Cohen
	 */
	public void removeTrap(){
		tileTrap = null;
	}
	
	/**
	 * Attempts to activate Trap. Returns true if successful
	 * 
	 * @author Michael Cohen
	 * @param caller Entity to apply the Trap on
	 * @return true if Tile has an Trap to activate, otherwise false
	 */
	public boolean activateTrap(Entity caller){
		if (tileTrap != null){
			tileTrap.activate(caller);
			return true;
		}
		return false;
	}
	
	/**
	 * Adds an AreaEffect to this Tile
	 * 
	 * @author Michael Cohen
	 * @param e the AreaEffect to add to the Tile
	 */
	public void addAreaEffect(AreaEffect e){
		if (effect != null)
			throw new RuntimeException("This tile already has an AreaEffect");
		else
			effect = e;
	}
	
	/**
	 * Removes the trap from this tile
	 * 
	 * @author Michael Cohen
	 */
	public void removeAreaEffect(){
		effect = null;
	}
	
	/**
	 * Attempts to activate AreaEffect. Returns true if successful
	 * 
	 * @author Michael Cohen
	 * @param caller Entity to apply the effect on
	 * @return true if Tile has an AreaEffect to activate, otherwise false
	 */
	public boolean activateEffect(Entity caller){
		if (effect != null){
			effect.activate(caller);
			return true;
		}
		return false;
	}
	
	
	
	
}
