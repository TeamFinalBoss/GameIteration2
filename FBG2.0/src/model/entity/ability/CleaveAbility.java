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

public class CleaveAbility extends RadialAbility
{
    private String name;
    private DealDamageEffect effect;
    private CombatCoordinator myCC;
    private ActiveMapManager myMM;
    private Effect cost;
	private double degree;
	private double radius;
        private int distance;

	public CleaveAbility()
	{
		this.name = "Cleave";
                this.distance = 1;
		this.effect = new DealDamageEffect(50, this.distance);
		this.degree = 90;
		this.radius = 2;
		this.myCC = CombatCoordinator.getInstance();
		this.myMM = ActiveMapManager.getInstance();
	}

	public CleaveAbility(String name, Effect effect, Effect cost, int degree, double radius, int distance)
	{
		super(name, effect, cost, degree, radius);
		this.myCC = CombatCoordinator.getInstance();
		this.myMM = ActiveMapManager.getInstance();
                this.distance = distance;
	}

	@Override
	public boolean meetsStatRequirements(Entity smasher)
	{
		if(smasher.getStrength() >= 15)
            return true;
        else
            return false;
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

    	if(mana >= manaCost)
    	{
    		smasher.setCurrentMP(--manaCost);
    		for(int i = 0; i < entities.size(); ++i)
    		{
    			if(inRange(smasher, entities.get(i)))
                        {
                            c2 = entities.get(i).getLocation();
                            this.distance = (int) c1.getDistance(c1,c2);
                            this.effect.setDistance(this.distance);
                            this.effect.applyEffect(entities.get(i));
                        }
    		}
    		return true;
    	}
    	else
    		return false;
    }

}