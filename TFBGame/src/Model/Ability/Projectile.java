/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Ability;

import map.PreciseCoordinatePair;
import map.Vector;
/**
 *
 * @author Jason Owens
 */
public class Projectile {
    PreciseCoordinatePair location; // should a projectile know it's own location?
    int lifetime; //this may change based on how we implement time
    Vector velocity;
    PlayerStats effects; //would this be Effect effect under matt's UML?
    
    /*--------------Constructors---------------*/
    Projectile(){
        throw new UnsupportedOperationException("Do not use default Projectile constructor.");
    }
    Projectile(int initialLifetime, Vector velocity, CoordinatePair initialLocation, PlayerStats effects){
        lifetime = initialLifetime;
        this.velocity = velocity;
        location = initialLocation;
        this.effects = effects;
    }
    
    /*
    * update() should update a projectile's location and lifetime every period
    * of game time (alternatively, it could multiply location by deltaTime and 
    * velocity, if we do time that way)
    * @author Jason Owens
    */
    public void update(){
       location.addX(velocity.getX());
       location.addY(velocity.getY());
    }
    public void onHit(Entity){
        //affect Entity here, may move this to Combat Coordinator
    }
}
