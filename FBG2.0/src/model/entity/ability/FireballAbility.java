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
    private int damage;

	public FireballAbility()
	{
		this.name = "Fireball";
        this.damage = 10;
		this.effect = new DealDamageEffect(this.damage);
		this.myCC = CombatCoordinator.getInstance();
		this.ms = 2000;
	}

	public FireballAbility(String name, Effect effect, Effect cost, double ms, double rate)
	{
		super(name, effect, cost, ms, rate);
		this.myCC = CombatCoordinator.getInstance();
	}

	@Override
	public boolean meetsStatRequirements(Entity summoner)
	{
		if(summoner.getIntellect() >= 15)
            return true;
        else
            return false;
	}

	@Override
    public boolean performAbility(Entity summoner) 
    {
    	CoordinatePair coordinatePair = summoner.getLocation();
    	double x = coordinatePair.getX();
    	double y = coordinatePair.getY();
        int manaCost = this.damage;

    	PreciseCoordinatePair PCP = new PreciseCoordinatePair();
    	PCP.set(x,y);

    	// this.cost = ManaEffect;

    	Vector velocity = getVector(summoner.getDirection());
    	int mana = summoner.getCurrentMP();

    	if(mana >= manaCost)
    	{
    		summoner.modifyCurrentMP(-manaCost);
    		Fireball fb = new Fireball((long) ms, velocity, PCP, effect, summoner);
                return true;
    	}
    	else
    		return false;
    }

}