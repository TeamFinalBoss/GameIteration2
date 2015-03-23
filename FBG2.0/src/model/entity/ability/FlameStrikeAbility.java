package model.entity.ability;

import model.effect.Effect;
import model.entity.Entity;

import model.map.Direction;
import model.director.CombatCoordinator;
import model.map.pair.CoordinatePair;
import java.util.ArrayList;
import model.entity.Entity;
import java.lang.Math.*;
import model.entity.ability.ProjectileAbility;
import model.map.Vector;
import model.map.pair.PreciseCoordinatePair;

/**
*
* @author Aaron Iglesias
*/

public class FlameStrikeAbility extends AngularAbility
{
    private String name;
    private Effect effect;
    private CombatCoordinator myCC;
    private Effect cost;
	private double degree;
	private double radius;

	public FlameStrikeAbility()
	{
		this.name = "Flame Strike";
		this.effect = FlameStrikeEffect;
		this.myCC = CombatCoordinator.getInstance();
		this.degree = 90;
		this.radius = 4;
	}

	public FlameStrikeAbility(String name, Effect effect, Effect cost, int degree, double radius)
	{
		super(name, effect, cost, degree, radius);
		this.myCC = CombatCoordinator.getInstance();
	}

	@Override
	public boolean meetsStatRequirements(Entity caster)
	{
		if(caster.getIntellect() >= 15)
            return true;
        else
            return false;
	}

	@Override
    public void performAbility(Entity caster) 
    {
    	int mana = caster.getCurrentMP();

    	if(mana >= 1)
    	{
    		caster.setCurrentMP(--mana);
    		// for(iterate through entities)
    		{
    			if(inRange(caster, entity))
    				// deal damage to entity
    		}
    	}
    	else
    		return;
    }

	@Override
	public void applyEffect(Effect effect)
	{
		if(inRange)
			// initial lifetime
			// initial coordinate
			// intial vector
	}

}