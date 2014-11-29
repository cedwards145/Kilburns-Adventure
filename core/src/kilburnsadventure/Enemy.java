package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends GameObject{

	//enemy positions coordinates
	protected Vector2 position;
	protected int framesSinceLastShot = 0, framesPerShot = 60;
	protected float speed = 2;
	protected Texture graphic;
	protected int maxHealth = 100, health = 100;
	protected MapState map;
	
	public Enemy(Game game, MapState containingMap, int xPosition, int yPosition)
	{
		super(game);

		position = new Vector2(xPosition, yPosition); 
		graphic = new Texture("graphics/enemies/dummy.png");
		
		map = containingMap;
	}
	
	@Override
	public void update()
	{
		position.x -= speed;
		
		if (framesSinceLastShot >= framesPerShot)
		{
			fire();
			framesSinceLastShot = 0;
		}
		else
			framesSinceLastShot++;
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		spriteBatch.draw(graphic,  position.x, position.y);
	}
	
	public void fire()
	{
		Bullet bullet = new Bullet(gameRef, map, false, 5, -1, position);
	}
	
	public void takeDamage(int damage)
	{
		health -= damage;
	}
	
	public boolean collides(Vector2 point)
	{
		Rectangle collisionBox = new Rectangle(position.x, position.y, graphic.getWidth(), graphic.getHeight());
		return collisionBox.contains(point);
	}
}
