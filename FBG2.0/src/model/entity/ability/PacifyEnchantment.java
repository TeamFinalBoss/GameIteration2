package model.entity.ability;

import java.util.List;
import java.util.Random;

import model.director.ActiveMapManager;
import model.effect.Effect;
import model.entity.Entity;
import model.entity.NPC;

public class PacifyEnchantment extends LinearAbility {

	public PacifyEnchantment(){
		super();
		this.setName("PacifyEnchantment");
	}
	
	public PacifyEnchantment(String name, Effect effect, Effect cost, double range){
		super(name, effect, cost, range);
		this.setName("PacifyEnchantment");
	}
	
	@Override
	public boolean meetsStatRequirements(Entity entityToLearn) {
		return entityToLearn.getIntellect() >= 2 && entityToLearn.getLevel() >= 2;
	}

	@Override
	public boolean performAbility(Entity caster) {
		int mana = caster.getCurrentMP();
		if (mana >= 30){
			caster.modifyCurrentMP(-30);
			
			List<Entity> entities = ActiveMapManager.getInstance().getActiveMap().getEntities();
			for (Entity e : entities){
				if (caster != e && inRange(caster, e)){
					//Allow enchantment to randomly fail
					Random rand = new Random();
					if (rand.nextInt(100) <= caster.getLevel() + caster.getIntellect()){
						((NPC)e).setFriendly(false);
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
