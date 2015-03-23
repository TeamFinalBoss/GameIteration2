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

public class FrostNovaAbility extends RadialAbility
{
    private String name;
    private Effect effect;
    private CombatCoordinator myCC;
    private ActiveMapManager myMM;
    private Effect cost;
	private double degree;
	private double radius;

	public FrostNovaAbility()
	{
		this.name = "Frost Nova";
		this.degree = 360;
		this.radius = 3;
		this.myMM = ActiveMapManager.getInstance();
	}

	public FrostNovaAbility(String name, Effect effect, Effect cost, int degree, double radius)
	{
		super(name, effect, cost, degree, radius);
		this.myCC = CombatCoordinator.getInstance();
		this.myMM = ActiveMapManager.getInstance();
	}

	@Override
	public boolean meetsStatRequirements(Entity caster)
	{
		if(caster.getIntellect() >= 20)
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
                SnareEffect snare;
    		caster.setCurrentMP(--mana);
    		for(int i = 0; i < entities.size(); ++i)
    		{
    			if(inRange(caster, entities.get(i)))
                            snare = new SnareEffect(entities.get(i), 4000);
    		}
    		return true;
    	}
    	else
    		return false;
    }
}