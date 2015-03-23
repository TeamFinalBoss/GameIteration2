package model.entity.ability;

import java.util.List;
import java.util.Random;

import model.director.ActiveMapManager;
import model.effect.Effect;
import model.entity.Entity;

public class PacifyEnchantment extends LinearAbility {

	public PacifyEnchantment(){
		super();
	}
	
	public PacifyEnchantment(String name, Effect effect, Effect cost, double range){
		super(name, effect, cost, range);
	}
	
	@Override
	public boolean meetsStatRequirements(Entity entityToLearn) {
		return entityToLearn.getIntellect() >= 2 && entityToLearn.getLevel() >= 2;
	}

	@Override
	public boolean performAbility(Entity summoner) {
		int mana = summoner.getCurrentMP();
		if (mana >= 30){
			summoner.modifyCurrentMP(-30);
			
			List<Entity> entities = ActiveMapManager.getInstance().getActiveMap().getEntities();
			for (Entity e : entities){
				if (inRange(summoner, e)){
					//Allow enchantment to randomly fail
					Random rand = new Random();
					if (rand.nextInt(100) <= summoner.getLevel() + summoner.getIntellect()){
						(NPC)summoner.setFriendly(true);
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
