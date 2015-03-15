package model.entity;

import java.awt.Point;
import model.map.Direction;

/** 
 * The class Entity defines a common type for all entities (beings) in the game. 
 * @author Matthew Kroeze, Chris Moscoso
 * @version 1.0.0 2015-03-14
 */
public class Entity{
	private Inventory myInventory;
	private Occupation myOccupation;
        private Direction myDirection;
        private int speed;
        private Point location; 
        
        private static Entity player;
        
    public Entity(){
        myInventory = new Inventory(5);
        myOccupation = new Occupation();
    }
    
    public Direction getDirection() {
        return myDirection;
    }

    public void setDirection(Direction direction) {
        myDirection = direction;
    }

    public void setSpeed(int s) {
        speed = s;
    }
    
    public static Entity getPlayer(){
        if(player == null){
            player = new Entity();
        }
        return player;
    }
    
    
}
