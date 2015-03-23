package model.entity.ability;

import java.util.List;

import model.director.ActiveMapManager;
import model.effect.BargainEffect;
import model.effect.Effect;
import model.entity.Entity;

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
					new BargainEffect(e);
					return true;
				}
			}
		}
		return false;
	}

}
