package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class GameSound {

	public static Sound soundAK47= Gdx.audio.newSound(Gdx.files.internal("audio/AK47.wav"));
	public static Sound explosion= Gdx.audio.newSound(Gdx.files.internal("audio/explosion.wav"));
}