package kilburnsadventure;

public class Weapon 
{
	public static Weapon AK47 = new Weapon(10, 20);
	
	protected String name;
	protected int damage, rateOfFire, framesSinceShot = 0;
	
	public Weapon(int reqDamage, int reqRateOfFire)
	{
		damage = reqDamage;
		rateOfFire = reqRateOfFire;
	}
	
	public boolean canFire()
	{
		if (framesSinceShot > rateOfFire)
		{
			framesSinceShot = 0;
			return true;
		}
		return false;
	}
	
	public void update()
	{
		framesSinceShot++;
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
