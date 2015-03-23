package model.entity.ability;

import java.util.List;
import java.util.Random;

import model.director.ActiveMapManager;
import model.effect.Effect;
import model.entity.Entity;
import model.entity.NPC;

public class SiphonHealthEnchantment extends LinearAbility {
	
	public SiphonHealthEnchantment(){
		super();
		this.setName("SiphonHealthEnchantment");
	}
	
	public SiphonHealthEnchantment(String name, Effect effect, Effect cost){
		super(name, effect, cost, 1);
		this.setName("SiphonHealthEnchantment");
	}

	@Override
	public boolean meetsStatRequirements(Entity entityToLearn) {
		return entityToLearn.getIntellect() >= 5 && entityToLearn.getLevel() >= 3;
	}

	@Override
	public boolean performAbility(Entity caster) {
		int mana = caster.getCurrentMP();
		if (mana >= 20){
			caster.modifyCurrentMP(-20);
			
			List<Entity> entities = ActiveMapManager.getInstance().getActiveMap().getEntities();
			for (Entity e : entities){
				if (caster != e && inRange(caster, e)){
					//Allow enchantment to randomly fail
					//TODO: add mechanism for castee to be able to 'resist' based on level
					Random rand = new Random();
					if (rand.nextInt(100) <= caster.getLevel() + caster.getIntellect()){
						caster.modifyCurrentHP(25);
						e.modifyCurrentHP(-25);
						return true;
					}
					else{
						((NPC)e).setFriendly(false);
						return false;
					}
				}
			}
		}
		return false;
	}

}
