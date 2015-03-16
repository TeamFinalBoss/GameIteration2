package model.ability;

/**
*
* @author Aaron Iglesias
*/
public class AngularAbility extends Ability
{
	private double degree;
	private double radius;

	/**
	* @author Aaron Iglesias
	* default constructor of Angular Ability
	* @param radius
	*/
	public AngularAbility()
	{
		super();
		degree = 90;
		radius = 1;
	}

	/**
	* @author Aaron Iglesias
	* constructor of Angular Ability
	* @param radius
	*/
	public AngularAbility(double radius)
	{
		super();
		this.radius = radius;
	}

	/**
	* @author Aaron Iglesias
	* gets the degree of the Angular Ability
	*/
	public double getDegree()
	{
		return degree;
	}

	/**
	* @author Aaron Iglesias
	* gets the radius of the Angular Ability
	*/
	public double getRadius()
	{
        return radius;
	}

	/**
	* @author Aaron Iglesias
	* sets the degree of the Angular Ability
	* @param degree
	*/
	public void setDegree(double degree)
	{
		// invalid degree
		if(degree < 0 || degree > 360)
			return;
		this.degree = degree;
	}

	/**
	* @author Aaron Iglesias
	* sets the radius of the Angular Ability
	* @param radius
	*/
	public void setRadius(double radius)
	{
		// invalid radius
		if(radius < 0)
			return;
		this.radius = radius;
	}
}