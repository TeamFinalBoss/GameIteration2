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
    private int isMovingY;
    private int isMovingX;
    
    public Entity() {
        myInventory = new Inventory(5);
        myOccupation = new Occupation();
        speed = 1;
        myDirection = Direction.North;
        location = new Point(1,1);
    }
    
    public Direction getDirection() {
        return myDirection;
    }
    
    public void setDirection(Direction d){
        myDirection = d;
    }
    
    /**
     * This specifies if the player is moving in the x direction.
     * @param b if true the player will move in the x direction
     * 
     * POSTCONDITION: isMovingY will be equal to 0 or 1. 
     * If 1, the player will move in the x direction.
     * If 0, the player will not move in the x direction.
     */
    public void isMovingX(Boolean b){
        isMovingX = b ? 1 : 0;
    }
    
    /**
     * This specifies if the player is moving in the y direction.
     * @param b if true the player will move in the Y direction
     * 
     * POSTCONDITION: isMovingY will be equal to 0 or 1. 
     * If 1, the player will move in the y direction.
     * If 0, the player will not move in the y direction.
     */
    public void isMovingY(Boolean b){
        isMovingY = b ? 1 : 0;
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
        /*System.out.println("IsMovingX:" + isMovingX);
        System.out.println("Speed: " + speed);
        System.out.println("Dx:" + myDirection.dx);
        System.out.println("All:" + isMovingX * speed * myDirection.dx);*/
        location.translate(isMovingX * speed * myDirection.dx, isMovingY * speed * myDirection.dy);
    }    
}
