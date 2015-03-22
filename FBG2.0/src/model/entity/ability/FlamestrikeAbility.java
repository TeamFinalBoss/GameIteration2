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

public class FlamestrikeAbility extends AngularAbility
{
	public FlamestrikeAbility()
	{
		name = "FlamestrikeAbility";
		degree = 90;
		radius = 4;
	}

	public FlamestrikeAbility(String name, Effect effect, CombatCoordinator myCC, Effect cost, int degree, double radius)
	{
		super(name, effect, myCC, cost);
		this.degree = degree;
		this.radius = radius;
	}

	@Override
	public boolean meetsStatRequirements(Entity caster)
	{
		if(caster.getIntellect() >= 10)
            return true;
        else
            return false;
	}

}