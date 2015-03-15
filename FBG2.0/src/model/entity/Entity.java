package model.entity;

import model.map.Direction;

/** The class Entity defines a common type for all entities (beings) in the game. 
 * @author Matthew Kroeze
 * @version 1.0.0 2015-03-14
 */
public abstract class Entity{
	private Inventory myInventory;
	private Occupation myOccupation;

    public Direction getDirection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
