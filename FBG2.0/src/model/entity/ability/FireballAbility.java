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
import model.entity.SummonerEntity;
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
    private int damage;

	public FireballAbility()
	{
		super();
		this.setName("FireBall");
                this.damage = 10;
		this.setEffect(new DealDamageEffect(this.damage));
	}

	public FireballAbility(String name, Effect effect, Effect cost, double ms, double rate)
	{
		super(name, effect, cost, ms, rate);
		this.setName("FireBall");
	}

	@Override
	public boolean meetsStatRequirements(Entity summoner)
	{
		/*if(summoner.getIntellect() >= 15)
            return true;
        else
            return false;*/
		return true;
	}

	@Override
    public boolean performAbility(Entity summoner) 
    {
        
        this.damage *= summoner.getIntellect() / 10;
        this.damage *= ((SummonerEntity)summoner).getBane() / 10;
        
    	CoordinatePair coordinatePair = summoner.getLocation();
    	double x = coordinatePair.getX();
    	double y = coordinatePair.getY();
        int manaCost = 1;

    	PreciseCoordinatePair PCP = new PreciseCoordinatePair();
    	PCP.set(x,y);

    	// this.cost = ManaEffect;

    	Vector velocity = getVector(summoner.getDirection());
        velocity.multiply(3);
    	int mana = summoner.getCurrentMP();

    	if(mana >= manaCost)
    	{
    		summoner.modifyCurrentMP(-manaCost);
    		Fireball fb = new Fireball((long)getMs(), velocity, PCP, getEffect(), summoner);
                return true;
    	}
    	else
    		return false;
    }

}