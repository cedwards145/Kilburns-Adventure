package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Weapon 
{
	public static Weapon AK47 = new Weapon("Ak-47", 10, 20, "AK47.wav");

	
	protected String name;
	protected int damage, rateOfFire, framesSinceShot = 0;
	protected Sound weaponSound;
	
	public Weapon(String reqName, int reqDamage, int reqRateOfFire, String soundFileName)
	{
		name = reqName;
		damage = reqDamage;
		rateOfFire = reqRateOfFire;
		weaponSound = Gdx.audio.newSound(Gdx.files.internal("audio/" + soundFileName));
	}
	
	public Sound getWeaponSound()
	{
		return weaponSound;
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
	
	public void fireMultiple()
	{
		
	}
}
