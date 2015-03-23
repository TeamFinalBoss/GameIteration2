package model.entity.ability;

import java.util.Random;

import model.director.ActiveMapManager;
import model.effect.Effect;
import model.entity.Entity;
import model.map.tile.trap.Trap;

public class DisableTrapAbility extends RadialAbility {

	public DisableTrapAbility(){
		super();
		this.setName("DisableTrap");
	}
	
	public DisableTrapAbility(String name, Effect effect, Effect cost, double radius){
		super(name, effect, cost, 360, radius);
		this.setName("DisableTrap");
	}
	
	@Override
	public boolean meetsStatRequirements(Entity entityToLearn) {
		return entityToLearn.getAgility() >= 2 && entityToLearn.getLevel() >= 2;
	}

	@Override
	public boolean performAbility(Entity entity) {
		int mana = entity.getCurrentMP();
		if (mana >= 15){
			Random rand = new Random();
			if (rand.nextInt(100) <= entity.getLevel() + entity.getAgility()){
				for (Trap t : entity.getVisibleTraps()){
					entity.modifyCurrentMP(-15);
					ActiveMapManager.getInstance().removeTrapFromActiveMap(t);
				}
			}
			
		}
		
		return false;
	}

}
