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

public class FireballAbility extends ProjectileAbility
{
	private String name;
    private Effect effect;
    private CombatCoordinator myCC;
    private Effect cost;
    private double ms;
    private double rate;

	public FireballAbility()
	{
		this.name = "Fireball";
		this.effect = FireballEffect;
		this.myCC = CombatCoordinator.getInstance();
		this.ms = 2000;
	}

	public FireballAbility(String name, Effect effect, Effect cost, double ms, double rate)
	{
		super(name, effect, cost, ms, rate);
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
    	CoordinatePair coordinatePair = caster.getLocation();
    	double x = coordinatePair.getX();
    	double y = coordinatePair.getY();

    	PreciseCoordinatePair PCP = new PreciseCoordinatePair();
    	PCP.set(x,y);

    	// this.cost = ManaEffect;

    	Vector velocity = getVector(caster.getDirection());
    	int mana = caster.getCurrentMP();

    	if(mana > 1)
    	{
    		caster.setCurrentMP(--mana);
    		FireBall fb = new Fireball(ms, velocity, PCP, effect, caster);
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