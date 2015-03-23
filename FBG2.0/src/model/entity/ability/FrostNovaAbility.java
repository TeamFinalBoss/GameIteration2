package model.entity.ability;

import model.effect.Effect;
import model.entity.Entity;

import model.map.Direction;
import model.director.CombatCoordinator;
import model.map.pair.CoordinatePair;
import java.util.ArrayList;
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
    private ActiveMapManager myMM;

	public FrostNovaAbility()
	{
		super();
		this.setName("FrostNova");
		this.setRadius(3);
		this.myMM = ActiveMapManager.getInstance();
	}

	public FrostNovaAbility(String name, Effect effect, Effect cost, int degree, double radius)
	{
		super(name, effect, cost, degree, radius);
		this.myMM = ActiveMapManager.getInstance();
		this.setName("FrostNova");
	}

	@Override
	public boolean meetsStatRequirements(Entity summoner)
	{
		/*if(summoner.getIntellect() >= 20)
            return true;
        else
            return false;*/
		return true;
	}

	@Override
    public boolean performAbility(Entity summoner) 
    {
    	GameMap map = myMM.getActiveMap();
    	int mana = summoner.getCurrentMP();
    	List<Entity> entities = map.getEntities();

    	if(mana >= 1)
    	{
    		summoner.setCurrentMP(--mana);
    		for(int i = 0; i < entities.size(); ++i)
    		{    			
    			if(inRange(summoner, entities.get(i)))
    				new SnareEffect(entities.get(i), 4000);
    		}
    		return true;
    	}
    	else
    		return false;
    }
}