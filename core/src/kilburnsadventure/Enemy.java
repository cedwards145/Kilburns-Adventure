package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends GameObject{

	//enemy positions coordinates
	protected Vector2 position;
	protected int framesSinceLastShot = 0, framesPerShot = 60;
	protected float speed = 3;
	protected Texture graphic;
	protected int maxHealth = 100, health = 100;
	
	public Enemy(Game game, int xPosition, int yPosition)
	{
		super(game);

		position = new Vector2(xPosition, yPosition); 
		graphic = new Texture("graphics/enemies/dummy.png");
	}
	
	@Override
	public void update()
	{
		position.x -= speed;
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		spriteBatch.draw(graphic,  position.x, position.y);
	}
	
	public void fire()
	{
		
	}
	
	public void takeDamage(int damage)
	{
		health -= damage;
	}
}
