package model.entity.ability;

import model.ability.Effect;
import model.entity.Entity;

/**
*
* @author Aaron Iglesias, Jason Owens
*/
public class AngularAbility extends Ability
{
	private double degree;
	private double radius;

	/**
	* @author Aaron Iglesias, Jason Owens
	* default constructor for AngularAbility
	* @param radius
	*/
	public AngularAbility()
	{
		super();
		degree = 90;
		radius = 1;
	}

	/**
	* @author Aaron Iglesias, Jason Owens
	* constructor for AngularAbility
	* @param radius
	*/
	public AngularAbility(String name, Effect effect, CombatCoordinator myCC, Effect cost, double degree, double radius)
	{
		super(name, effect, myCC, cost);
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
	* sets the degree of AngularAbility
	* @param degree
	*/
	public void setDegree(double degree)
	{
		// invalid degree
		if(degree < -360 || degree > 360)
			return;
		this.degree = degree;
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

	@Override
	protected abstract ArrayList<CoordinatePair> getAffectedTiles(Entity entity)
	{
		Direction direction = entity.getDirection();
		CoordinatePair coordinatePair = entity.getLocation();
		

		return;
	}
}