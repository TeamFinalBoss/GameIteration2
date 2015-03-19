package model.ability;

/**
*
* @author Aaron Iglesias, Jason Owens
*/
public class RadialAbility extends Ability
{
	private double degree;
	private double radius;

	/**
	* @author Aaron Iglesias, Jason Owens
	* default constructor for RadialAbility
	*/
	public RadialAbility()
	{
		super();
		degree = 360;
		radius = 1;
	}

	/**
	* @author Aaron Iglesias, Jason Owens
	* constructor for RadialAbility
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
	* gets degree of RadialAbility
	*/
	public double getDegree()
	{
		return degree;
	}

	/**
	* @author Aaron Iglesias, Jason Owens
	* gets radius of RadialAbility
	*/
	public double getRadius()
	{
		return radius;
	}

	/**
	* @author Aaron Iglesias, Jason Owens
	* sets degree of RadialAbility
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