package model.entity.ability;

import model.effect.Effect;
import model.entity.Entity;

import model.map.Direction;
import model.director.CombatCoordinator;
import model.map.pair.CoordinatePair;
import java.util.ArrayList;
import model.entity.Entity;
import java.lang.Math.*;
import model.effect.DealDamageEffect;
import model.entity.ability.ProjectileAbility;
import model.map.Vector;
import model.map.pair.PreciseCoordinatePair;
import model.map.projectiles.Fireball;
import model.map.projectiles.NinjaStar;

/**
*
* @author Aaron Iglesias
*/

public class NinjaStarAbility extends ProjectileAbility
{
    private int damage;

	public NinjaStarAbility()
	{
		super();
		this.setName("NinjaStar");
		this.damage = 10;
		this.setEffect(new DealDamageEffect(this.damage));
		this.setMs(2000);
	}

	public NinjaStarAbility(String name, Effect effect, Effect cost, double ms, double rate)
	{
		super(name, effect, cost, ms, rate);
		this.setName("NinjaStar");
	}

	@Override
	public boolean meetsStatRequirements(Entity sneak)
	{
		/*
		if(sneak.getAgility() >= 15)
            return true;
        else
            return false;*/
		return true;
	}

	@Override
    public boolean performAbility(Entity sneak) 
    {
    	CoordinatePair coordinatePair = sneak.getLocation();
    	double x = coordinatePair.getX();
    	double y = coordinatePair.getY();
        int manaCost = this.damage;

    	PreciseCoordinatePair PCP = new PreciseCoordinatePair();
    	PCP.set(x,y);

    	// this.cost = ManaEffect;

    	Vector velocity = getVector(sneak.getDirection());
    	int mana = sneak.getCurrentMP();

    	if(mana >= manaCost)
    	{
    		sneak.setCurrentMP(--manaCost);
    		new NinjaStar((long) getMs(), velocity, PCP, getEffect(), sneak);
            return true;
    	}
    	else
    		return false;
    }

}