// Aaron Iglesias

public class LinearSkill extends Ability
{
	private double radius;

	public LinearSkill()
	{
		super();
		radius = null;
	}

	public AngularSkill(double radius)
	{
		super();
		this.radius = radius;
	}

	public double getRadius
	{
		return radius;
	}

	public void setRadius(double radius)
	{
		// invalid radius
		if(radius < 0)
			return;
		this.radius = radius;
	}