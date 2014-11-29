package kilburnsadventure;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObject 
{
	// A reference to the Game object
	protected Game gameRef;
	
	// If an object is enabled, it will update.
	// If an object is visible, it will draw.
	protected boolean enabled, visible;
	
	public GameObject(Game game)
	{
		gameRef = game;
	}
	
	public void draw(SpriteBatch spriteBatch)
	{}
	
	public void update()
	{}
}
