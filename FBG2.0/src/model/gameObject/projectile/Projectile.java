
package model.gameObject.projectile;

import java.awt.Point;
import model.gameObject.SpriteObject;
import model.map.Direction;

/**
 *
 * @author ChrisMoscoso
 */
public class Projectile extends SpriteObject{

    Point location;
    Direction myDirection;
    int speed = 2; //Temporary. TODO: This should come from a stats object.
    
    /**
     * The default constructor should never really be called.
     */
    public Projectile(){
        this(1,1, Direction.East);//default
        
    }
    
    public Projectile(int x, int y, Direction direction) {
        this.setLocation(x, y);
        myDirection = direction;
    }

    public Direction getDirection() {
        return myDirection;
    }

    
    
    /**
     * Get the location of the projectile.
     *
     * @return the location of the projectile as a point (unit is tiles)
     */
    @Override
    public Point getLocation() {
        return location;
    }

    /**
     * Sets the location of the projectile on the map
     *
     * @param x the x coordinate of the location
     * @param y the y coordinate of the location
     */
    @Override
    public void setLocation(int x, int y) {
        location = new Point(x, y);
    }

    /**
     * Changes the projectiles location by translating the position by the
     * velocity.
     */
    public void move() {
        location.translate(myDirection.dx * speed, myDirection.dy * speed);
    }
    
}
