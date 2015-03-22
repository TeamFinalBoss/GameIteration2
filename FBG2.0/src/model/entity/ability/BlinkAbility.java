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

public class Blink extends Ability
{
	private String name;
    private Effect effect;
    private CombatCoordinator myCC;
    private Effect cost;
    private int range;

	public BlinkAbility()
	{
		this.name = "Blink";
		this.myCC = CombatCoordinator.getInstance();
		this.range = 5;
	}

	public BlinkAbility(String name, Effect effect, Effect cost, int range)
	{
		super(name, effect, cost, ms, rate);
		this.range = range;
		this.myCC = CombatCoordinator.getInstance();
	}

	@Override
	public boolean meetsStatRequirements(Entity caster)
	{
		if(caster.getIntellect() >= 20)
            return true;
        else
            return false;
	}

	@Override
    public void performAbility(Entity caster) 
    {
    	CoordinatePair coordinatePair = caster.getLocation();
    	double x = coordinatePair.getX();
    	double y = coordinatePair.getY();

    	int mana = caster.getCurrentMP();

    	if(mana >= 1)
    	{
    		caster.setCurrentMP(--mana);
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