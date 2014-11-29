package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Bullet extends GameObject 
{
	public final int LEFT = -1, RIGHT = 1;
	
	protected Vector2 position;
	protected float speed = 3f;
	protected int direction;
	protected Texture graphic;
	
	public Bullet(Game game, int reqDirection)
	{
		super(game);
		direction = reqDirection;
		graphic = new Texture("graphics/weapons/bullets/bullet.png");
	}
	
	@Override
	public void update()
	{
		position.x += (speed * direction);
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		spriteBatch.draw(graphic, position.x, position.y);
	}

}
