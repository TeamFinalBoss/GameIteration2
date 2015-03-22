
package model.gameObject.projectile;

import java.awt.Point;
import model.director.GameDirector;
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
    private int speed; 
    private int range;
    private int distanceTraveled = 0;
    private PlayerStats s;
    private boolean canMove = true;
    
    /**
     * The default constructor should never really be called.
     */
    public Projectile(){
        this(1,1, Direction.East, 5, 1, new PlayerStats());//default
        
    }
    
    public Projectile(int x, int y, Direction direction, int range, int speed,  PlayerStats s) {
        this.setLocation(x, y);
        myDirection = direction;
        this.range = range;
        this.speed = speed;
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
            distanceTraveled++;
            if(endOfRangeReached()){
                this.disintegrate();
            }
        }
    }
    
    public boolean endOfRangeReached(){
        return distanceTraveled > range; 
    }
    
    public void disintegrate(){
        GameDirector.getActiveMap().removeProjectile(this);
    }
    
    
    
}
