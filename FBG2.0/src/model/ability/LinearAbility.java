package model.ability;

/**
*
* @author Aaron Iglesias
*/
public class LinearAbility extends Ability
{
	private double range;

	/**
	* @author Aaron Iglesias
	* default constructor of LinearAbility
	*/
	public LinearAbility()
	{
		super();
		range = 1;
	}

	/**
	* @author Aaron Iglesias
	* constructor for LinearAbility
	* @param range
	*/
	public LinearAbility(double range)
	{
		super();
		this.range = range;
	}

	/**
	* @author Aaron Iglesias
	* gets range of LinearAbility
	*/
	public double getRange()
	{
		return range;
	}

	/**
	* @author Aaron Iglesias
	* sets range of LinearAbility
	* @param range
	*/
	public void setRange(double range)
	{
		// invalid range
		if(range < 0)
			return;
		this.range = range;
	}
        
}
