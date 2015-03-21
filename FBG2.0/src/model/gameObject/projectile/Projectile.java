
package model.gameObject.projectile;

import java.awt.Point;
import model.gameObject.GameObject;
import model.map.Direction;
import model.stats.PlayerStats;
import model.util.gameTimer.GameTimer;
import model.util.gameTimer.GameTimerListener;

/**
 *
 * @author ChrisMoscoso
 */
public class Projectile extends GameObject{

    private Point location;
    private Direction myDirection;
    private int speed = 1; //Temporary. TODO: This should come from the projectile's stats object.
    private PlayerStats s;
    private Boolean canMove = true;
    
    /**
     * The default constructor should never really be called.
     */
    public Projectile(){
        this(1,1, Direction.East, new PlayerStats());//default
        
    }
    
    public Projectile(int x, int y, Direction direction, PlayerStats s) {
        this.setLocation(x, y);
        myDirection = direction;
        this.s = s;
    }

    public Direction getDirection() {
        return myDirection;
    }

    public PlayerStats getPlayerStats() {
        return s;
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
        if(canMove){
            location.translate(myDirection.dx, myDirection.dy);
            canMove = false;
            GameTimer x = new GameTimer(speed);
            x.setGameTimerListener(new GameTimerListener() {

                @Override
                public void trigger() {
                    canMove = true;
                }

            });
            x.start();
        }
        
    }
    
}
