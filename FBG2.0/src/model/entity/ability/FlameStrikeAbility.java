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
import model.entity.ability.ProjectileAbility;
import model.map.GameMap;
import model.map.Locations;
import model.map.Vector;
import model.map.pair.PreciseCoordinatePair;

/**
*
* @author Aaron Iglesias
*/

public class FlameStrikeAbility extends AngularAbility
{
   
    private ActiveMapManager myMM;
    private int distance;
    private int damage;

	public FlameStrikeAbility()
	{
		super();
		this.setName("FlameStrike");
        this.distance = 1;
        this.damage = 10;
		this.setEffect(new DealDamageEffect(this.damage));
		this.setRadius(2);
		this.myMM = ActiveMapManager.getInstance();
	}

	public FlameStrikeAbility(String name, Effect effect, Effect cost, int degree, double radius, int distance)
	{
		super(name, effect, cost, degree, radius);
		this.myMM = ActiveMapManager.getInstance();
        this.distance = distance;
        this.setName("FlameStrike");
	}

	@Override
	public boolean meetsStatRequirements(Entity caster)
	{
		/*
		if(caster.getStrength() >= 15)
            return true;
        else
            return false;*/
		return true;
	}

	@Override
    public boolean performAbility(Entity caster) 
    {
    	GameMap map = myMM.getActiveMap();
    	int mana = caster.getCurrentMP();
    	List<Entity> entities = map.getEntities();
        CoordinatePair c1 = caster.getLocation();
        CoordinatePair c2;
        int manaCost = this.damage;

    	if(mana >= manaCost)
    	{
    		caster.setCurrentMP(mana - manaCost);
    		for(int i = 0; i < entities.size(); ++i)
    		{
    			if(inRange(caster, entities.get(i)))
                        {
                            c2 = entities.get(i).getLocation();
                            this.distance = (int) c1.getDistance(c1,c2);
                            ((DealDamageEffect)this.getEffect()).applyEffect(entities.get(i),distance);
                        }
    		}
    		return true;
    	}
    	else
    		return false;
    }

}