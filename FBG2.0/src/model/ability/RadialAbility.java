package model.ability;

/**
*
* @author Aaron Iglesias
*/
public class RadialAbility extends Ability
{
	private double degree;
	private double radius;

	/**
	* @author Aaron Iglesias
	* default constructor for RadialAbility
	*/
	public RadialAbility()
	{
		super();
		degree = 360;
		radius = 1;
	}

	/**
	* @author Aaron Iglesias
	* constructor for RadialAbility
	* @param radius
	*/
	public RadialAbility(double radius)
	{
		super("RadialAbility");
		this.radius = radius;
	}

	/**
	* @author Aaron Iglesias
	* gets degree of RadialAbility
	*/
	public double getDegree()
	{
		return degree;
	}

	/**
	* @author Aaron Iglesias
	* gets radius of RadialAbility
	*/
	public double getRadius()
	{
		return radius;
	}

	/**
	* @author Aaron Iglesias
	* sets degree of RadialAbility
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
	* sets radius of RadialAbility
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