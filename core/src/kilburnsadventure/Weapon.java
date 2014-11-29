package kilburnsadventure;

public class Weapon 
{
	protected String name;
	protected int damage, rateOfFire;
	
	public Weapon()
	{
		
	}
	
	
	// ACCESSOR METHODS 
	
	public String getName()
	{
		return name;
	}
	public int getDamage()
	{
		return damage;
	}
	public int getRateOfFire()
	{
		return rateOfFire;
	}
}
