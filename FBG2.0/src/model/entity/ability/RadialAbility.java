package model.entity.ability;

import model.effect.Effect;
import model.entity.Entity;

import model.map.Direction;
import model.director.CombatCoordinator;
import model.map.pair.CoordinatePair;
import java.util.ArrayList;
import model.entity.Entity;
import java.lang.Math.*;

/**
*
* @author Aaron Iglesias, Jason Owens
*/
public abstract class RadialAbility extends Ability
{
    private String name;
    private Effect effect;
    private CombatCoordinator myCC;
    private Effect cost;
    private double degree;
    private double radius;

	/**
	* @author Aaron Iglesias, Jason Owens
	* default constructor for AngularAbility
	* @param radius
	*/
	public RadialAbility()
	{
		super();
		degree = 360;
		radius = 1;
	}

	/**
	* @author Aaron Iglesias, Jason Owens
	* constructor for AngularAbility
	* @param radius
	*/
	public RadialAbility(String name, Effect effect, CombatCoordinator myCC, Effect cost, int degree, double radius)
	{
		super(name, effect, cost);
		this.myCC = CombatCoordinator.getInstance();
		this.degree = degree;
		this.radius = radius;
	}

	/**
	* @author Aaron Iglesias, Jason Owens
	* gets the degree of AngularAbility
	*/
	public double getDegree()
	{
		return degree;
	}

	/**
	* @author Aaron Iglesias, Jason Owens
	* gets the radius of AngularAbility
	*/
	public double getRadius()
	{
        return radius;
	}

	/**
	* @author Aaron Iglesias, Jason Owens
	* sets the radius of AngularAbility
	* @param radius
	*/
	public void setRadius(double radius)
	{
		// invalid radius
		if(radius < 0)
			return;
		this.radius = radius;
	}

	/**
	* @author Aaron Iglesias
	* checks if entity falls within the range of an ability
	* @param Entity entity, Entity caster
	* @return boolean
	*/
	public boolean inRange(Entity caster, Entity entity)
	{
		if(radius == 0)
			return false;
		
		CoordinatePair casterCoordinatePair = caster.getLocation();
		CoordinatePair entityCoordinatePair = entity.getLocation();

		// scale location of entity relative to caster's location
		double x = entityCoordinatePair.getX() - casterCoordinatePair.getX();
		double y = entityCoordinatePair.getY() - casterCoordinatePair.getY();

		boolean inCircle = Math.pow(x,2) + Math.pow(y,2) <= Math.pow(radius,2);

		if(inCircle)
			return true;
		else
			return false;
	}

	/*
	xRot = xCenter + cos(Angle) * (x - xCenter) - sin(Angle) * (y - yCenter)
	yRot = yCenter + sin(Angle) * (x - xCenter) + cos(Angle) * (y - yCenter)
	*/

	public abstract boolean meetsStatRequirements(Entity entityToLearn);
}