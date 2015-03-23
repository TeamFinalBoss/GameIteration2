package model.effect;

import model.entity.Entity;

public class BargainEffect extends TemporaryEffect {
	
	public BargainEffect(Entity entityToEffect){
		super(entityToEffect);
	}

	@Override
	protected void applyEffect() {
		this.myEntity.modifyBargain(-50);
	}

	@Override
	protected void applyInverseEffect() {
		this.myEntity.modifyBargain(50);

	}

	@Override
	public void applyEffect(Entity entityToAffect) {
		entityToAffect.modifyBargain(-50);

	}

}
