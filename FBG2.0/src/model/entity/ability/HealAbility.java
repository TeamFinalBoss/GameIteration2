package model.entity.ability;

import model.effect.Effect;
import model.entity.Entity;

import model.map.Direction;
import model.director.CombatCoordinator;
import model.map.pair.CoordinatePair;
import java.util.ArrayList;
import model.entity.Entity;
import java.lang.Math.*;
import java.util.List;
import model.director.ActiveMapManager;
import model.effect.DealDamageEffect;
import model.effect.HealEffect;
import model.effect.SnareEffect;
import model.entity.ability.ProjectileAbility;
import model.map.GameMap;
import model.map.Locations;
import model.map.Vector;
import model.map.pair.PreciseCoordinatePair;

/**
*
* @author Aaron Iglesias
*/

public class HealAbility extends RadialAbility
{

    private int heal;

	public HealAbility()
	{
		super();
		this.setName("Heal");
        this.heal = 10;
	}

	public HealAbility(String name, Effect effect, Effect cost, int healAmt, int degree, double radius)
	{
		super(name, effect, cost, degree, radius);
		this.setName("Heal");
		this.heal = healAmt;
	}

	@Override
	public boolean meetsStatRequirements(Entity summoner)
	{
		/*if(summoner.getIntellect() >= 5)
            return true;
        else
            return false;*/
		return true;
	}

	@Override
    public boolean performAbility(Entity summoner) 
    {
    	int mana = summoner.getCurrentMP();
        int manaCost = this.heal;
    	if(mana >= manaCost)
    	{
    		summoner.modifyCurrentMP(-manaCost);
    		summoner.modifyCurrentHP(heal);
    		return true;
    	}
    	else
    		return false;
    }
}