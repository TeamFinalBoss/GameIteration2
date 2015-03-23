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
    private String name;
    private Effect effect;
    private CombatCoordinator myCC;
    private ActiveMapManager myMM;
    private Effect cost;
	private double degree;
	private double radius;

	public PickPocketAbility()
	{
		this.name = "PickPocket";
		this.degree = 360;
		this.radius = 1;
		this.myMM = ActiveMapManager.getInstance();
	}

	public PickPocketAbility(String name, Effect effect, Effect cost, int degree, double radius)
	{
		super(name, effect, cost, degree, radius);
		this.myCC = CombatCoordinator.getInstance();
		this.myMM = ActiveMapManager.getInstance();
		this.name = "PickPocket"
	}

	@Override
	public boolean meetsStatRequirements(Entity sneak)
	{
		if(sneak.getAgility() >= 10)
            return true;
        else
            return false;
	}

	@Override
    public boolean performAbility(Entity sneak) 
    {
        Random random = new Random();
    	GameMap map = myMM.getActiveMap();
    	int mana = sneak.getCurrentMP();
    	List<Entity> entities = map.getEntities();

        int manaCost = sneak.getMaxMP() / 4;
        double agility = (double) sneak.getAgility();
        int gold = 0;
        int goldMax = (int) (agility / 4);
        double probability = agility / 500;

        if(probability > .75)
            probability = .75;
        probability = (int) probability * 1000 - 1;

    	if(mana >= manaCost)
    	{
    		sneak.setCurrentMP(mana - manaCost);
    		for(int i = 0; i < entities.size(); ++i)
    		{
                if(inRange(sneak, entities.get(i)))
                {
                    for(int j = 0; j < 999; ++j)
                    {
                        if(random.nextInt(1000) <= probability)
                            ++gold;
                    }
                    if(gold > goldMax)
                        gold = goldMax;
                    sneak.modifyCurrency(gold);
                }
    		}
    		return true;
    	}
    	else
    		return false;
    }
}