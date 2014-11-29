package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class WeaponSound {
	
	private Sound weaponSound;
	public WeaponSound(String name, String filePath)
	{
		name = name;
		weaponSound = Gdx.audio.newSound(Gdx.files.internal("audio/" + filePath));
	}

	public static WeaponSound AK47 = new WeaponSound("AK47", "AK47.wav");
	
	public Sound getWeaponSound()
	{
		return weaponSound;
	}
}

