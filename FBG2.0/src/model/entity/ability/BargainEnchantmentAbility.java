package model.entity.ability;

import java.util.List;
import java.util.Random;

import model.director.ActiveMapManager;
import model.effect.BargainEffect;
import model.effect.Effect;
import model.entity.Entity;
import model.entity.NPC;

public class BargainEnchantmentAbility extends LinearAbility {
	
	public BargainEnchantmentAbility(){
		super();
		this.setName("BargainEnchantment");
	}
	
	public BargainEnchantmentAbility(String name, Effect effect, Effect cost){
		super(name, effect, cost, 1);
		this.setName("BargainEnchantment");
	}
	
	@Override
	public boolean meetsStatRequirements(Entity entityToLearn) {
		//return entityToLearn.getIntellect() >= 2;
		return true;
	}

	@Override
	public boolean performAbility(Entity summoner) {
		
		int mana = summoner.getCurrentMP();
		
		if (mana >= 10){
			summoner.modifyCurrentMP(-10);
			
			List<Entity> entities = ActiveMapManager.getInstance().getActiveMap().getEntities();
			for (Entity e : entities){
				if (summoner != e && inRange(summoner, e)){
					//Allow enchantment to randomly fail
					Random rand = new Random();
					if (rand.nextInt(100) <= summoner.getLevel() + summoner.getIntellect()){
						new BargainEffect(e);
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