/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.ability;

import java.util.List;
import java.util.Random;
import model.director.ActiveMapManager;
import model.effect.BargainEffect;
import model.effect.DealDamageEffect;
import model.effect.Effect;
import model.entity.Entity;
import model.entity.NPC;

/**
 *
 * @author Owner
 */
public class Brawl extends LinearAbility{
    public Brawl(){
		super();
		this.setName("BargainEnchantment");
	}
	
	public Brawl(String name, Effect effect, Effect cost){
		super(name, effect, cost, 10);
		this.setName("BargainEnchantment");
	}
	
	@Override
	public boolean meetsStatRequirements(Entity entityToLearn) {
		return entityToLearn.getIntellect() >= 2;
	}

	@Override
	public boolean performAbility(Entity summoner) {
		
		int mana = summoner.getCurrentMP();
		
		if (mana >= 0){
			//summoner.modifyCurrentMP(-10);
			
			List<Entity> entities = ActiveMapManager.getInstance().getActiveMap().getEntities();
			for (Entity e : entities){
				if (summoner != e && inRange(summoner, e)){
					//Allow enchantment to randomly fail
					Random rand = new Random();
					if (true){
                                                System.out.println("Test");
						DealDamageEffect dde = new DealDamageEffect(10);
                                                dde.applyEffect(e);
						return true;
					}
					else {
						((NPC)e).setFriendly(false);
						return false;
					}
				}
			}
		}
		return false;
	}
}
