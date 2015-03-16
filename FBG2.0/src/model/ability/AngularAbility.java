// Aaron Iglesias
package model.ability;

public class AngularAbility extends Ability
{
	private double degree;
	private double radius;

	public AngularAbility()
	{
		super();
		degree = 90;
		radius = 1;
	}

	public AngularAbility(double radius)
	{
		super();
		this.radius = radius;
	}

	public double getDegree()
	{
		return degree;
	}

	public double getRadius()
	{
            return radius;
	}

	public void setDegree(double degree)
	{
		// invalid degree
		if(degree < 0 || degree > 360)
			return;
		this.degree = degree;
	}

	public void setRadius(double radius)
	{
		// invalid radius
		if(radius < 0)
			return;
		this.radius = radius;
	}
}