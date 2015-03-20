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

	public boolean inRange(Entity caster, Entity entity)
	{
		if(degree == 0)
			return false;

		CoordinatePair casterCoordinatePair = caster.getLocation();
		CoordinatePair entityCoordinatePair = entity.getLocation();

		// scale center of caster to coordinate (0,0)
		// scale location of entity accordingly
		double x = entityCoordinatePair.getX() - casterCoordinatePair.getX();
		double y = entityCoordinatePair.getY() - casterCoordinatePair.getY();

		boolean inCircle = Math.pow(x,2) + Math.pow(y,2) <= Math.pow(radius,2);

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

		if(degree == 180)
		{
			if(Math.sqrt(Math.pow(x,2) + Math.pow(y,2)) <= radius)
				return true;
			else
				return false;
		}

		// point on left line
		Lx = 1;
		Ly = - Math.tan(Math.PI / 2 - radian) * Lx;

		// point on right line
		Rx = 1;
		Ry = Math.tan(Math.PI / 2 - radian) * Rx;

		boolean rightOfLeftLine = ((0 - Lx) * (y - Ly) - (0 - Ly) * (x - Lx)) >= 0;
		boolean leftOfRightLine = ((0 - Rx) * (y - Ry) - (0 - Ry) * (x - Rx)) <= 0;

		if(degree < 180)
		{
			if(rightOfLeftLine && leftOfRightLine)
				return true;
			else
				return false;
		}

		else // if(degree > 180)
		{
			if(rightOfLeftLine || leftOfRightLine)
				return true;
			else
				return false;
		}

	}
        
}