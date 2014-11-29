package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends MapObject{

	protected int framesSinceLastShot = 0, framesPerShot = 60;
	protected float speed = 2;
	protected Texture graphic;
	protected int maxHealth = 100, health = 100;
	
	public Enemy(Game game, MapState containingMap, float xPosition, float yPosition)
	{
		super(game, containingMap);
		position = new Vector2(xPosition, yPosition);

		graphic = new Texture("graphics/enemies/dummy.png");
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
		Bullet bullet = new Bullet(gameRef, map, false, 5, Bullet.LEFT, position);
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
	
	public Rectangle getCollisionBox()
	{
		return new Rectangle(position.x, position.y, graphic.getWidth(), graphic.getHeight());
	}
	
}
