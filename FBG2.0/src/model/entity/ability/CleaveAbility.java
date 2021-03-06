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

public class CleaveAbility extends AngularAbility
{
    
    private ActiveMapManager myMM;
    private int damage;

	public CleaveAbility()
	{
		super();
		this.setName("Cleave");
                this.damage = 10;
		this.setEffect(new DealDamageEffect(this.damage));
		this.setRadius(2);
                this.setDegree(90);
		this.myMM = ActiveMapManager.getInstance();
	}

	public CleaveAbility(String name, Effect effect, Effect cost, int degree, double radius)
	{
		super(name, effect, cost, degree, radius);
		this.myMM = ActiveMapManager.getInstance();
		this.setName("Cleave");
	}

	@Override
	public boolean meetsStatRequirements(Entity smasher)
	{
		/*
		if(smasher.getStrength() >= 15)
            return true;
        else
            return false;*/
		return true;
	}

	@Override
    public boolean performAbility(Entity smasher) 
    {
    	GameMap map = myMM.getActiveMap();
    	int mana = smasher.getCurrentMP();
    	List<Entity> entities = map.getEntities();
        CoordinatePair c1 = smasher.getLocation();
        CoordinatePair c2;
        int manaCost = this.damage;
        int distance;

    	if(mana >= manaCost)
    	{
    		smasher.setCurrentMP(mana - manaCost);
    		for(int i = 0; i < entities.size(); ++i)
    		{
    			if(inRange(smasher, entities.get(i)))
                        {
                            c2 = entities.get(i).getLocation();
                            if(c1 != c2)
                            {
                                distance = (int) c1.getDistance(c1,c2);
                                ((DealDamageEffect)this.getEffect()).applyEffect(entities.get(i),distance);
                            }
                        }
    		}
    		return true;
    	}
    	else
    		return false;
    }

}