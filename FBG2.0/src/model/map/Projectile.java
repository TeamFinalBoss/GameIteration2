package model.map;

import java.util.TimerTask;

import model.effect.Effect;
import model.entity.Entity;
import model.map.pair.PreciseCoordinatePair;
import model.util.GameTimer;

/**
 * Projectiles effectively change everything internal themselves but their properties
 * (location) are used by Combat Coordinator. run() is used by GameTimer.
 * @author Jason Owens
 */
public class Projectile extends TimerTask{
    private PreciseCoordinatePair location; // should a projectile know it's own location?
    
    private long lifetime; //this may change based on how we implement time
    private int refreshRate;
    
    private GameTimer myTimer;
    
    private boolean isActive;
    
    private Vector velocity;
    private Effect effects; //would this be Effect effect under matt's UML?
    
    /*--------------Constructors---------------*/
    /**
    * gives 0 value to all private attributes
    * @author Jason Owens
    */
    Projectile(){
        throw new UnsupportedOperationException("Do not use default Projectile constructor.");
    }
    Projectile(long initialLifetime, Vector velocity, PreciseCoordinatePair initialLocation, Effect effects){
        lifetime = initialLifetime;
        this.velocity = velocity;
        location = initialLocation;
        this.effects = effects;
        myTimer = GameTimer.getInstance();
        myTimer.addEvent(this, 0); //immediately calls run
        refreshRate = 10; //projectiles refresh every 10 milliseconds (20 times a second)
    }
    
    /*
    * run() should update a projectile's location and lifetime every period
    * of game time (alternatively, it could multiply location by deltaTime and 
    * velocity, if we do time that way)
    * NOTE: vectors use time of 1s per movement
    * @author Jason Owens
    */
    @Override
    public void run(){
        location.addX(velocity.getX()/(double)refreshRate);
        location.addY(velocity.getY()/(double)refreshRate);
        lifetime -= refreshRate;    
        if(isActive){ 
            myTimer.addEvent(this, refreshRate);
        }
    }
    public void onHit(Entity e){
        isActive = false; //change this if we ever implement piercing projectiles
        //affect Entity here, may move this to Combat Coordinator
    }
}
