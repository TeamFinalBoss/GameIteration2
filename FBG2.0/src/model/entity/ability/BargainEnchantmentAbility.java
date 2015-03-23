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
	}
	
	public BargainEnchantmentAbility(String name, Effect effect, Effect cost){
		super(name, effect, cost, 1);
	}
	
	@Override
	public boolean meetsStatRequirements(Entity entityToLearn) {
		return entityToLearn.getIntellect() >= 2;
	}

	@Override
	public boolean performAbility(Entity caster) {
		
		int mana = caster.getCurrentMP();
		
		if (mana >= 10){
			caster.modifyCurrentMP(-10);
			
			List<Entity> entities = ActiveMapManager.getInstance().getActiveMap().getEntities();
			for (Entity e : entities){
				if (inRange(caster, e)){
					//Allow enchantment to randomly fail
					Random rand = new Random();
					if (rand.nextInt(100) <= caster.getLevel() + caster.getIntellect()){
						new BargainEffect(e);
						return true;
					}
					else {
						(NPC)caster.setFriendly(false);
						return false;
					}
				}
			}
		}
		return false;
	}

}
