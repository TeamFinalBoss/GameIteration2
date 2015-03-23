package model.effect;

import model.entity.Entity;

public class GodLevelEffect extends TemporaryEffect {
	
	public GodLevelEffect(Entity entityToAffect){
		super(entityToAffect);
	}

	@Override
	protected void applyEffect() {
		myEntity.modifyAgility(50);
		myEntity.modifyBargain(50);
		myEntity.modifyHardiness(50);
		myEntity.modifyIntellect(50);
		myEntity.modifyObservation(50);
		myEntity.modifyStrength(50);
	}

	@Override
	protected void applyInverseEffect() {
		myEntity.modifyAgility(-50);
		myEntity.modifyBargain(-50);
		myEntity.modifyHardiness(-50);
		myEntity.modifyIntellect(-50);
		myEntity.modifyObservation(-50);
		myEntity.modifyStrength(-50);
	}

	@Override
	public void applyEffect(Entity entityToAffect) {
		myEntity = entityToAffect;
		applyEffect();

	}

}
