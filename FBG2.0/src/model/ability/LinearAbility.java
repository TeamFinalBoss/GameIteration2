
package model.ability;



public class LinearAbility extends Ability
{
	private double range;

	public LinearAbility()
	{
		super();
		range = 1;
	}

	public LinearAbility(double range)
	{
		super();
		this.range = range;
	}

	public double getRadius()
	{
		return range;
	}

	public void setRadius(double range)
	{
		// invalid range
		if(range < 0)
			return;
		this.range = range;
	}
        
}