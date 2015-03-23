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
	private String name;
    private Effect effect;
    private CombatCoordinator myCC;
    private Effect cost;
    private double ms;
    private double rate;
    private int damage;

	public NinjaStarAbility()
	{
		this.name = "NinjaStar";
        this.damage = 10;
		this.effect = new DealDamageEffect(this.damage);
		this.myCC = CombatCoordinator.getInstance();
		this.ms = 2000;
	}

	public NinjaStarAbility(String name, Effect effect, Effect cost, double ms, double rate)
	{
		super(name, effect, cost, ms, rate);
		this.myCC = CombatCoordinator.getInstance();
		this.name = "NinjaStar";
	}

	@Override
	public boolean meetsStatRequirements(Entity sneak)
	{
		if(sneak.getAgility() >= 15)
            return true;
        else
            return false;
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
    		NinjaStar ns = new NinjaStar((long) ms, velocity, PCP, effect, sneak);
                return true;
    	}
    	else
    		return false;
    }

}