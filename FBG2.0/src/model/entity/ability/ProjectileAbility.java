package model.entity.ability;

import model.map.Direction;
import model.director.CombatCoordinator;
import model.effect.Effect;
import model.entity.Entity;
import model.map.pair.CoordinatePair;

import java.util.ArrayList;

import static java.lang.Math.pow;
import model.map.Vector;

public abstract class ProjectileAbility extends Ability
{
    private String name;
    private Effect effect;
    private CombatCoordinator myCC;
    private Effect cost;
    private double ms;
    private double rate;

	public ProjectileAbility()
	{
		super();
		this.ms = 2000;
		this.myCC = CombatCoordinator.getInstance();
	}

	public ProjectileAbility(String name, Effect effect, Effect cost, double ms, double rate){
        this.name = name;
        this.effect = effect;
        this.myCC = CombatCoordinator.getInstance();
        this.cost = cost;
        this.ms = ms;
        this.rate = rate;
    }

    public double getMs()
    {
    	return ms;
    }

    public void setMs(double ms)
    {
    	this.ms = ms;
    }

	public Vector getVector(Direction direction)
	{
		Vector vector = new Vector();

		switch(direction)
		{
			case North:
				vector.setX(0);
				vector.setY(1);
				break;
			case NorthWest:
				vector.setX(-1 / Math.sqrt(2));
				vector.setY(1 / Math.sqrt(2));
				break;
			case West:
				vector.setX(-1);
				vector.setY(0);
				break;
			case SouthWest:
				vector.setX(-1 / Math.sqrt(2));
				vector.setY(-1 / Math.sqrt(2));
				break;
			case South:
				vector.setX(0);
				vector.setY(-1);				
				break;
			case SouthEast:
				vector.setX(1 / Math.sqrt(2));
				vector.setY(-1 / Math.sqrt(2));
				break;
			case East:
				vector.setX(1);
				vector.setY(0);
				break;
			case NorthEast:
				vector.setX(1 / Math.sqrt(2));
				vector.setY(1 / Math.sqrt(2));
				break;
		}

		return vector;
	}

    public abstract boolean meetsStatRequirements(Entity caster);
    public abstract boolean performAbility(Entity entity);
}