/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.map.tile.trap;

import model.effect.DealDamageEffect;
import model.entity.Entity;
import model.map.pair.CoordinatePair;

/**
 *
 * @author Owner
 */
public class DealDamageTrap extends Trap {
	
	private int damage;
	
	public DealDamageTrap(){
		super();
		
		this.setName("Generic Deal Damage Trap");
		this.setID("14");
		this.setClassName("Deal Damage Trap");
		
		this.damage = 40;
	}
	
	public DealDamageTrap(String objectName, String description, CoordinatePair location,
			int difficulty, int damageToDeal){
		super(objectName, description, location, difficulty);
		
		this.setID("14");
		this.setClassName("Deal Damage Trap");
		
		this.damage = damageToDeal;
	}

    @Override
    public void Activate(Entity caller) {
        DealDamageEffect effect = new DealDamageEffect(damage);
        effect.applyEffect(caller);
    }
    
}
