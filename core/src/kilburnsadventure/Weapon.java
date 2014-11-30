package kilburnsadventure;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Weapon 
{
	public enum FireMode { single, spread, missile };
	
	public static Weapon AK47 = new Weapon("Ak-47", FireMode.single, 10, 20, "ak47.png", GameSound.soundAK47);
	public static Weapon Shotgun = new Weapon("Shotgun", FireMode.spread, 4, 40, "ak47.png", GameSound.soundAK47);
	public static Weapon MissileLauncher = new Weapon("Missile Launcher", FireMode.missile, 1000, 180, "ak47.png", GameSound.soundAK47);

	protected Texture graphic;
	protected String name;
	protected int damage, rateOfFire, framesSinceShot = 0;
	protected Sound weaponSound;
	protected FireMode fireMode;
	protected int ammo;
	
	public Weapon(String reqName, FireMode mode, int reqDamage, int reqRateOfFire, String imageFileName, Sound reqSound)
	{
		name = reqName;
		damage = reqDamage;
		rateOfFire = reqRateOfFire;
		graphic = new Texture("graphics/weapons/" + imageFileName);
		weaponSound = reqSound;
		fireMode = mode;
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
	
	public void fireBullet(double rotation, Vector2 position, Game gameRef, MapState targetMap)
	{
		if (fireMode == FireMode.single)
		{
			Bullet bullet = new Bullet(gameRef, targetMap, true, damage, rotation, position);
		}
		else if (fireMode == FireMode.spread)
		{
			for (int bulletNo = -2; bulletNo < 3; bulletNo++)
			{
				Bullet bullet = new Bullet(gameRef, targetMap, true, damage, rotation + Math.toRadians(bulletNo * 7), position);
			}
		}
		else if (fireMode == FireMode.missile)
		{
			List<MapObject> objects = targetMap.getObjectList();
		}
		weaponSound.play();
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
	
	public void ammoDec()
	{
		ammo --;
	}
}
