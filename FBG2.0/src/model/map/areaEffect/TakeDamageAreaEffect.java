package model.map.areaEffect;

import model.entity.Entity;

public class TakeDamageAreaEffect extends AreaEffect{

	@Override
	public void activate(Entity caller) {
		//TODO: add functionality for variable amounts of damage dealt
		caller.dealDamage(10);
		
	}
	
}
