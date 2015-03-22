import model.effect.Effect;
import model.entity.Entity;

import model.map.Direction;
import model.director.CombatCoordinator;
import model.map.pair.CoordinatePair;
import java.util.ArrayList;
import model.entity.Entity;
import java.lang.Math.*;

/**
*
* @author Aaron Iglesias
*/

public class FireballAbility extends LinearAbility
{
	public FireballAbility()
	{
		name = "Fireball";
		range = 10;
	}

	public FireballAbility(String name, Effect effect, CombatCoordinator myCC, Effect cost, int degree, double radius)
	{
		super(name, effect, myCC, cost);
		this.degree = degree;
		this.range = range;
	}


	@Override
	public boolean meetsStatsRequirements(Entity caster)
	{
		if(caster.getIntellect() >= 15)
            return true;
        else
            return false;
	}

}