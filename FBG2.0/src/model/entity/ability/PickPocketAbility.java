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
import java.util.Random;
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

public class PickPocketAbility extends RadialAbility
{
    private ActiveMapManager myMM;

	public PickPocketAbility()
	{
		super();
		this.setName("PickPocket");
		this.setRadius(1);
		this.myMM = ActiveMapManager.getInstance();
	}

	public PickPocketAbility(String name, Effect effect, Effect cost, int degree, double radius)
	{
		super(name, effect, cost, degree, radius);
		this.myMM = ActiveMapManager.getInstance();
		this.setName("PickPocket");
	}

	@Override
	public boolean meetsStatRequirements(Entity sneak)
	{
		/*
		if(sneak.getAgility() >= 10)
            return true;
        else
            return false;*/
		return true;
	}

	@Override
    public boolean performAbility(Entity sneak) 
    {
		
        Random random = new Random();
    	GameMap map = myMM.getActiveMap();
    	int mana = sneak.getCurrentMP();
    	List<Entity> entities = map.getEntities();

        int manaCost = 10;
        double agility = (double) sneak.getAgility();
        int gold = 0;
        double goldMax =  agility * 2;
        double probability = agility / 10;

        if(probability > .75)
            probability = .75;
        probability = (int) Math.round(probability * 1000) - 1;
        
        System.out.println(probability);
        
    	if(mana >= manaCost)
    	{
    		sneak.setCurrentMP(mana - manaCost);
    		for(int i = 0; i < entities.size(); ++i)
    		{
                if(inRange(sneak, entities.get(i)) && entities.get(i).getLocation() != sneak.getLocation())
                {
                    for(int j = 0; j < 999; ++j)
                    {
                        if(random.nextInt(1000) <= probability)
                            ++gold;
                    }
                    if(gold > (int) Math.round(goldMax))
                        gold = (int) Math.round(goldMax);
                    sneak.modifyCurrency(gold);
                }
    		}
    		return true;
    	}
    	else
    		return false;
    		
    	
    }
}