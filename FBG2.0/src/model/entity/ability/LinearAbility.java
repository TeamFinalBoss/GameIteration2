package model.entity.ability;

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
public abstract class LinearAbility extends Ability
{
	private double range;

	/**
	* @author Aaron Iglesias
	* default constructor of LinearAbility
	*/
	public LinearAbility()
	{
		super();
		range = 1;
	}

	/**
	* @author Aaron Iglesias
	* constructor for LinearAbility
	* @param range
	*/
	public LinearAbility(String name, Effect effect, CombatCoordinator myCC, Effect cost, double range)
	{
		super(name, effect, myCC, cost);
		this.range = range;
	}

	/**
	* @author Aaron Iglesias
	* gets range of LinearAbility
	*/
	public double getRange()
	{
		return range;
	}

	/**
	* @author Aaron Iglesias
	* sets range of LinearAbility
	* @param range
	*/
	public void setRange(double range)
	{
		// invalid range
		if(range < 0)
			return;
		this.range = range;
	}

	public boolean inRange(Entity caster, Entity entity)
	{
		boolean inRange;
		int x1, y1, x2, y2;

		CoordinatePair casterCoordinatePair = caster.getLocation();
		CoordinatePair entityCoordinatePair = entity.getLocation();

		x1 = casterCoordinatePair.getX();
        y1 = casterCoordinatePair.getY();

        x2 = entityCoordinatePair.getX();
        y2 = entityCoordinatePair.getY();

        inRange = Math.sqrt(Math.pow(x1 - x2,2) + Math.pow(y1 - y2, 2)) <= range;

        if(inRange)
        	return true;
        else
        	return false;
	}
        
        public abstract boolean meetsStatRequirements(Entity entityToLearn);
}
