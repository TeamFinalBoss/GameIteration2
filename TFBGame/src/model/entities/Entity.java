package model.entities;

import src.model.entities.Direction;

/** The class Entity defines a common type for all entities (beings) in the game. 
 * @author Matthew Kroeze
 * @version 1.0.0 2015-03-14
 */
public abstract class Entity{
        private Direction myDirection;
	private Inventory myInventory;
	private Occupation myOccupation;
}
