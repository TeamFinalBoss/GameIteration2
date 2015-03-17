package model.entity;

import java.awt.Point;
import model.map.Direction;

/**
 * The class Entity defines a common type for all entities (beings) in the game.
 *
 * @author Matthew Kroeze, Chris Moscoso
 * @version 1.0.0 2015-03-14
 */
public class Entity {

    protected Inventory myInventory;
    protected Occupation myOccupation;
    protected Direction myDirection;
    protected int speed;
    protected Point location;    
    
    private static Entity player;
    
    public Entity() {
        myInventory = new Inventory(5);
        myOccupation = new Occupation();
        speed = 0;
        myDirection = Direction.North;
        location = new Point(1,1);
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
    
    /**
     * Get the location of the entity on the map.
     * @return the location of the entity as a point (unit is tiles)
     */
    public Point getLocation(){
        return location;
    }
    
    /**
     * Sets the location of the entity on the map
     * @param x the x coordinate of the location
     * @param y the y coordinate of the location
     */
    public void setLocation(int x , int y){
        location = new Point(x, y);
    }
    
    public void move() {
        location.translate(speed * myDirection.dx, speed * myDirection.dy);
    }
    
}
