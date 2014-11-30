package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends MapObject{

	protected int framesSinceLastShot = 0, framesPerShot = 60;
	protected float speed = 1;
	protected Texture graphic;
	protected int maxHealth = 20, health = 20;
	protected int scoreOfThisEnemy = 1;
	protected boolean falling = false;
	protected float rotation = 0f;
	
	public Enemy(Game game, MapState containingMap, float xPosition, float yPosition)
	{
		super(game, containingMap);
		position = new Vector2(xPosition, yPosition);

		graphic = new Texture("graphics/enemies/f22.png");
	}
	
	@Override
	public void update()
	{
        position.x -= speed;
		
		if (falling)
		{
			if (rotation < 90)
				rotation += 0.5f;
			position.y -= speed * 3;
			if (position.y < -50)
				map.removeFromObjectList(this);
		}
		else
		{
			float playerY = map.getPlayer().getPosition().y;
			
			if (position.x < gameRef.getCameraPosition().x + (gameRef.getWidth() / 2)
			      && (playerY < position.y + 50 && playerY > position.y - 150))
			{
				if (framesSinceLastShot >= framesPerShot)
				{
					fire();
					framesSinceLastShot = 0;
				}
				else
					framesSinceLastShot++;
			}
		}
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		spriteBatch.draw(graphic, position.x, position.y, 0, 0, graphic.getWidth(), graphic.getHeight(),
		         1, 1, rotation, 0, 0, graphic.getWidth(), graphic.getHeight(), false, false);
	}
	
	public void fire()
	{
		Bullet bullet = new Bullet(gameRef, map, false, 5, Bullet.LEFT, position);
		WeaponSound.AK47.getWeaponSound().play();
	}
	
	public void takeDamage(int damage)
	{
		health -= damage;
		
		if (health <= 0)
		{
			//falling = true;
			map.removeFromObjectList(this);
			map.addToObjectList(new Explosion(gameRef, map, position));
			
			map.getPlayer().addScore(scoreOfThisEnemy);
		}
	}
	
	public boolean collides(Vector2 point)
	{
		Rectangle collisionBox = new Rectangle(position.x, position.y, graphic.getWidth(), graphic.getHeight());
		return !falling && collisionBox.contains(point);
	}
	
	public Rectangle getCollisionBox()
	{
		return new Rectangle(position.x, position.y, graphic.getWidth(), graphic.getHeight());
	}
	
}
