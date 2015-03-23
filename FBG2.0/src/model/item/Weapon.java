/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import java.util.ArrayList;
import model.director.CombatCoordinator;
import model.entity.Entity;
import model.map.pair.CoordinatePair;
import model.effect.Effect;
import model.effect.DealDamageEffect;


/**
 * ID=17
 * @author ashishag
 */
public abstract class Weapon extends Equipable{
    
    protected Effect E1;
    
    
    public Weapon(){
		super("Weapon", "Generic description", new CoordinatePair(), 50, 1, 
                        EquipSlot.HEAD);
		
		this.id = "17";
		this.className = "Weapon";
                this.E1= new DealDamageEffect(10); 
             
                
		
		//Other properties set here
	}
	
	public Weapon(String objectName, String description, CoordinatePair 
                location, int value, int durability, EquipSlot slot, Effect E1){
		super(objectName, description, location, value, durability, slot);
		
		this.id = "17";
		this.className = "Weapon";	
                this.E1= new DealDamageEffect(10);
                this.durability=durability;
		//Other properties set here
	} 
        
        
        public void attack(Entity e){
            CoordinatePair relative = new CoordinatePair(0,0);
            switch(e.getDirection()){
                case North: relative.setY(1);
                            break;
                case NorthEast: relative.setY(1);
                                relative.setX(1);
                                break;
                case NorthWest: relative.setY(1);
                                relative.setX(-1);
                                break;
                case South:     relative.setY(-1);
                                break;
                case West:      relative.setX(-1);
                                break;
                case East:      relative.setX(1);
                                break;
                case SouthEast: relative.setX(1);
                                relative.setY(-1);
                                   break;
                case SouthWest: relative.setY(-1);
                                relative.setX(-1);
                                break;
                
            }
            relative.add(e.getLocation());
            ArrayList<CoordinatePair> finalloc = new ArrayList<>();
            finalloc.add(relative);
            CombatCoordinator.getInstance().attemptAffectEntities(finalloc,E1);   
        }
        
        
          public Effect getEffect(){
            return E1; 
        }
        
        public void setEffect(Effect E1){
            this.E1= E1;
        }
           
           
           
           
               
           
    
}
