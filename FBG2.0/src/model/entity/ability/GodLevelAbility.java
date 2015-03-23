package model.entity.ability;

import model.effect.GodLevelEffect;
import model.entity.Entity;

public class GodLevelAbility extends Ability {
	
	public GodLevelAbility(){
		super();
		setName("GodLevel");
	}
	
	
	@Override
	public boolean meetsStatRequirements(Entity entityToLearn) {
		return true;
	}

	@Override
	public boolean performAbility(Entity caster) {
		if (caster.getCurrentMP() >= 75){
			new GodLevelEffect(caster).applyEffect(caster);
		}
		return false;
	}

}
