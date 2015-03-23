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
    private Effect effect;
    private CombatCoordinator myCC;
    private ActiveMapManager myMM;
    private Effect cost;
	private double degree;
	private double radius;

	public CleaveAbility()
	{
		this.name = "Cleave";
		this.effect = new DealDamageEffect(20);
		this.degree = 90;
		this.radius = 2;
		this.myCC = CombatCoordinator.getInstance();
		this.myMM = ActiveMapManager.getInstance();
	}

	public CleaveAbility(String name, Effect effect, Effect cost, int degree, double radius)
	{
		super(name, effect, cost, degree, radius);
		this.myCC = CombatCoordinator.getInstance();
		this.myMM = ActiveMapManager.getInstance();
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
    public boolean performAbility(Entity caster) 
    {
    	GameMap map = myMM.getActiveMap();
    	int mana = caster.getCurrentMP();
    	List<Entity> entities = map.getEntities();

    	if(mana >= 1)
    	{
    		caster.setCurrentMP(--mana);
    		for(int i = 0; i < entities.size(); ++i)
    		{
    			if(inRange(caster, entities.get(i)))
    				this.effect.applyEffect(entities.get(i));
    		}
    		return true;
    	}
    	else
    		return false;
    }

}