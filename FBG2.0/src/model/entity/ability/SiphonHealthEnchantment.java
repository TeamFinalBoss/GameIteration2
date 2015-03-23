package model.entity.ability;

import java.util.List;
import java.util.Random;

import model.director.ActiveMapManager;
import model.effect.Effect;
import model.entity.Entity;

public class SiphonHealthEnchantment extends LinearAbility {
	
	public SiphonHealthEnchantment(){
		super();
	}
	
	public SiphonHealthEnchantment(String name, Effect effect, Effect cost){
		super(name, effect, cost, 1);
	}

	@Override
	public boolean meetsStatRequirements(Entity entityToLearn) {
		return entityToLearn.getIntellect() >= 5 && entityToLearn.getLevel() >= 3;
	}

	@Override
	public boolean performAbility(Entity summoner) {
		int mana = summoner.getCurrentMP();
		if (mana >= 20){
			summoner.modifyCurrentMP(-20);
			
			List<Entity> entities = ActiveMapManager.getInstance().getActiveMap().getEntities();
			for (Entity e : entities){
				if (inRange(summoner, e)){
					//Allow enchantment to randomly fail
					//TODO: add mechanism for castee to be able to 'resist' based on level
					Random rand = new Random();
					if (rand.nextInt(100) <= summoner.getLevel() + summoner.getIntellect()){
						summoner.modifyCurrentHP(25);
						e.modifyCurrentHP(-25);
						return true;
					}
					else{
						(NPC)summoner.setFriendly(false);
						return false;
					}
				}
			}
		}
		return false;
	}

}
