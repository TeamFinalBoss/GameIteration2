package model.map.areaEffect;

import model.entity.Entity;

public class InstantDeathAreaEffect extends AreaEffect {
	
	public InstantDeathAreaEffect() {
		super();
		this.setName("instantDeath");
                this.setID("instantDeath");
	}

	@Override
	public void activate(Entity caller) {
		caller.die();
	}

}
