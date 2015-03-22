import model.effect.Effect;
import model.entity.Entity;

import model.map.Direction;
import model.director.CombatCoordinator;
import model.map.pair.CoordinatePair;
import java.util.ArrayList;
import model.entity.Entity;
import java.lang.Math.*;
import model.entity.ability.RadialAbility;

/**
*
* @author Aaron Iglesias
*/

public class FrostNovaAbility extends RadialAbility
{
	public FrostNovaAbility()
	{
		name = "Frost Nova";
		degree = 360;
		radius = 4;
	}

	public FrostNovaAbility(String name, Effect effect, CombatCoordinator myCC, Effect cost, int degree, double radius)
	{
		super(name, effect, myCC, cost);
		this.degree = degree;
		this.radius = radius;
        }
        
    @Override
	public boolean meetsStatRequirements(Entity caster)
	{
		if(caster.getIntellect() >= 20)
            return true;
        else
            return false;
	}

}