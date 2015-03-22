package model.entity.ability;

import model.effect.Effect;
import model.entity.Entity;

import model.map.Direction;
import model.director.CombatCoordinator;
import model.map.pair.CoordinatePair;
import java.util.ArrayList;
import model.entity.Entity;
import java.lang.Math.*;
import model.entity.ability.Ability;

/**
*
* @author Aaron Iglesias, Jason Owens
*/
public abstract class AngularAbility extends Ability
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
	public AngularAbility(String name, Effect effect, CombatCoordinator myCC, Effect cost, int degree, double radius)
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
	* sets the degree of AngularAbility
	* @param degree
	*/
	public void setDegree(int degree)
	{
		// invalid degree
		if(degree < 0 || degree > 360)
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

	/**
	* @author Aaron Iglesias
	* checks if entity falls within the range of an ability
	* @param Entity entity, Entity caster
	* @return boolean
	*/
	public boolean inRange(Entity caster, Entity entity)
	{
		if(degree == 0 || radius == 0)
			return false;

		CoordinatePair casterCoordinatePair = caster.getLocation();
		CoordinatePair entityCoordinatePair = entity.getLocation();

		// scale location of entity relative to caster's location
		double x = entityCoordinatePair.getX() - casterCoordinatePair.getX();
		double y = entityCoordinatePair.getY() - casterCoordinatePair.getY();

		boolean inCircle, rightOfLeftLine, leftOfRightLine;

		inCircle = Math.pow(x,2) + Math.pow(y,2) <= Math.pow(radius,2);

		if(!inCircle)
			return false;

		if(degree == 360)
		{
			if(inCircle)
				return true;
			else
				return false;
		}

		double radian = Math.toRadians(degree);
		double rotate = 0;
		double Lx, Ly, Rx, Ry;

		Direction direction = caster.getDirection();

		// rotate the coordinate grid depending on which way
		// the caster is facing
		switch(direction)
		{
			case North:
				rotate = 0;
				break;
			case NorthWest:
				rotate = Math.PI / 4;
				break;
			case West:
				rotate = Math.PI / 2;
				break;
			case SouthWest:
				rotate = 3 * Math.PI / 4;
				break;
			case South:
				rotate = Math.PI;
				break;
			case SouthEast:
				rotate = 5 * Math.PI / 4;
				break;
			case East:
				rotate = 3 * Math.PI / 2;
				break;
			case NorthEast:
				rotate = 7 * Math.PI / 4;
				break;
		}

		// location of entity after grid rotation
		x = Math.cos(rotate) * x - Math.sin(rotate) * y;
		y = Math.sin(rotate) * x + Math.cos(rotate) * y;

		// check if entity is within semicircle
		if(degree == 180)
		{
			if(Math.sqrt(Math.pow(x,2) + Math.pow(y,2)) <= radius)
				return true;
			else
				return false;
		}

		// first point on left line
		// the second point on left line is (0,0)
		Lx = 1;
		Ly = - Math.tan(Math.PI / 2 - radian / 2) * Lx;

		// point on right line
		// the second point on right line is (0,0)
		Rx = 1;
		Ry = Math.tan(Math.PI / 2 - radian / 2) * Rx;

		if(degree < 180)
		{
			rightOfLeftLine = ((0 - Lx) * (y - Ly) - (0 - Ly) * (x - Lx)) <= 0;
			leftOfRightLine = ((0 - Rx) * (y - Ry) - (0 - Ry) * (x - Rx)) <= 0;

			if(rightOfLeftLine && leftOfRightLine)
				return true;
			else
				return false;
		}

		else // if(degree > 180)
		{
			rightOfLeftLine = ((0 - Lx) * (y - Ly) - (0 - Ly) * (x - Lx)) > 0;
			leftOfRightLine = ((0 - Rx) * (y - Ry) - (0 - Ry) * (x - Rx)) > 0;

			if(!(rightOfLeftLine && leftOfRightLine))
				return true;
			else
				return false;
		}
	}

	/*
	xRot = xCenter + cos(Angle) * (x - xCenter) - sin(Angle) * (y - yCenter)
	yRot = yCenter + sin(Angle) * (x - xCenter) + cos(Angle) * (y - yCenter)
	*/

	public abstract boolean meetsStatRequirements(Entity entityToLearn);
}