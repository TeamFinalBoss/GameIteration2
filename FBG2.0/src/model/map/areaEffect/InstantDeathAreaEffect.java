package model.map.areaEffect;

import model.entity.Entity;

public class InstantDeathAreaEffect extends AreaEffect {

	@Override
	public void activate(Entity caller) {
		caller.die();
	}

}
