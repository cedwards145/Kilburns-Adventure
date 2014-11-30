package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Weapon 
{
	public static Weapon AK47 = new Weapon("Ak-47", 10, 20, "ak47.png");

	protected Texture graphic;
	protected String name;
	protected int damage, rateOfFire, framesSinceShot = 0;
	
	public Weapon(String reqName, int reqDamage, int reqRateOfFire, String imageFileName)
	{
		name = reqName;
		damage = reqDamage;
		rateOfFire = reqRateOfFire;
		graphic = new Texture("graphics/weapons/" + imageFileName);
	}
	
	public static Sound sound47 = Gdx.audio.newSound(Gdx.files.internal("audio/AK47.wav"));
	
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
	public Texture getImage()
	{
		return graphic;
	}
	public void fireMultiple()
	{
		
	}
}
