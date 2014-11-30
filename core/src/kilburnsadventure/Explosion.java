package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Explosion extends MapObject 
{
	protected int frameNo = 0, numberFrames = 95;
	protected int textureWidth, textureHeight, frameWidth, frameHeight;
	protected Texture animation;
	
	public Explosion(Game game, MapState reqMap, Vector2 startPosition)
	{
		super(game, reqMap);
		animation = new Texture("graphics/explosion.png");
		textureWidth = animation.getWidth();
		textureHeight = animation.getHeight();
		frameWidth = textureWidth / 10;
		frameHeight = textureWidth / 10;
		position = startPosition;
	}
	
	@Override
	public void update()
	{
		frameNo++;
		if (frameNo > numberFrames)
			map.removeFromObjectList(this);
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		int x = frameNo % 10;
		int y = frameNo / 10;
		spriteBatch.draw(animation, position.x, position.y, x * frameWidth, y * frameHeight, frameWidth, frameHeight);
	}
	
}
