package model.gameObject.entity;

import java.awt.Point;
import model.factory.SpriteFactory;
import model.gameObject.SpriteObject;
import model.map.Direction;
import model.stats.PlayerStats;

/**
 * The class Entity defines a common type for all entities (beings) in the game.
 *
 * @author Matthew Kroeze, Chris Moscoso
 * @version 1.0.0 2015-03-14
 */
public class Entity extends SpriteObject {

    protected Inventory myInventory;
    protected Occupation myOccupation;
    protected Direction myDirection;
    protected PlayerStats myStats;

    protected Point location;

    private static Entity player;
    private int isMovingY;
    private int isMovingX;

    public Entity() {
        myInventory = new Inventory(5);
        myOccupation = new Occupation();
        myDirection = Direction.South;
        location = new Point(1, 1);
        myStats = new PlayerStats();
    }

    public Direction getDirection() {
        return myDirection;
    }

    /**
     * Kill the entity in the game.
     */
    public void kill() {
    }

    /**
     * Sets the direction that the entity will face and move in.
     *
     * @param d the direction it will face.
     *
     * POSTCONDITION: Entity's myDirection will equal d. The spritePath will
     * reset to direction d.
     */
    public void setDirection(Direction d) {
        myDirection = d;
        switch (d) {
            case North:
                this.setSpritePath(SpriteFactory.PLAYER_NORTH);
                break;
            case East:
                this.setSpritePath(SpriteFactory.PLAYER_EAST);
                break;
            case South:
                this.setSpritePath(SpriteFactory.PLAYER_SOUTH);
                break;
            case West:
                this.setSpritePath(SpriteFactory.PLAYER_WEST);
                break;
        }
    }

    /**
     * This specifies if the player is moving in the x direction.
     *
     * @param b if true the player will move in the x direction
     *
     * POSTCONDITION: isMovingY will be equal to 0 or 1. If 1, the player will
     * move in the x direction. If 0, the player will not move in the x
     * direction.
     */
    public void isMovingX(Boolean b) {
        isMovingX = b ? 1 : 0;
    }

    /**
     * This specifies if the player is moving in the y direction.
     *
     * @param b if true the player will move in the Y direction
     *
     * POSTCONDITION: isMovingY will be equal to 0 or 1. If 1, the player will
     * move in the y direction. If 0, the player will not move in the y
     * direction.
     */
    public void isMovingY(Boolean b) {
        isMovingY = b ? 1 : 0;
    }

    /**
     * Get the location of the entity.
     *
     * @return the location of the entity as a point (unit is tiles)
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Sets the location of the entity on the map
     *
     * @param x the x coordinate of the location
     * @param y the y coordinate of the location
     */
    public void setLocation(int x, int y) {
        location = new Point(x, y);
    }

    /**
     * Changes the entity's location by translating the position by the
     * velocity.
     */
    public void move() {
        location.translate(isMovingX * myDirection.dx * myStats.getSpeed(), isMovingY * myDirection.dy * myStats.getSpeed());
    }
    
    /**
     * Regenerates a small portion of the entity's health and mana
     * TODO: make it based off of a stats object
     */
    public void regenerate(){
        myStats.modhpCurrent(1);
        myStats.modmpCurrent(1);
       
    }
}
