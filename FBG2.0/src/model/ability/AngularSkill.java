// Aaron Iglesias

public class AngularSkill extends Ability
{
	private double degree;
	private double radius;

	public AngularSkill()
	{
		super();
		degree = 90;
		radius = null;
	}

	public AngularSkill(double radius)
	{
		super();
		this.radius = radius;
	}

	public double getDegree()
	{
		return degree;
	}

	public double getRadius
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