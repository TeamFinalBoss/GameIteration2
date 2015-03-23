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
    private String name;
    private HealEffect effect;
    private CombatCoordinator myCC;
    private ActiveMapManager myMM;
    private Effect cost;
	private double degree;
	private double radius;
        private int heal;

	public HealAbility()
	{
		this.name = "Heal";
		this.degree = 360;
		this.radius = 0;
		this.myMM = ActiveMapManager.getInstance();
                this.heal = 10;
        this.effect = new HealEffect(this.heal);
	}

	public HealAbility(String name, Effect effect, Effect cost, int degree, double radius)
	{
		super(name, effect, cost, degree, radius);
		this.myCC = CombatCoordinator.getInstance();
		this.myMM = ActiveMapManager.getInstance();
	}

	@Override
	public boolean meetsStatRequirements(Entity summoner)
	{
		if(summoner.getIntellect() >= 5)
            return true;
        else
            return false;
	}

	@Override
    public boolean performAbility(Entity summoner) 
    {
    	GameMap map = myMM.getActiveMap();
    	int mana = summoner.getCurrentMP();
    	List<Entity> entities = map.getEntities();
        int manaCost = this.heal;

    	if(mana >= manaCost)
    	{
    		summoner.setCurrentMP(mana - manaCost);
            this.effect.applyEffect(summoner);
    		
    		return true;
    	}
    	else
    		return false;
    }
}