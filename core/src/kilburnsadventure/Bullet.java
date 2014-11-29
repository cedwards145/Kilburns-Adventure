package kilburnsadventure;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Bullet extends GameObject 
{
	public final int LEFT = -1, RIGHT = 1;
	
	protected Vector2 position;
	protected float speed = 3f;
	protected int direction, damage;
	protected Texture graphic;
	protected MapState map;
	protected boolean firedByPlayer;
	
	public Bullet(Game game, MapState reqMap, boolean playerBullet, int reqDamage, int reqDirection, Vector2 startPosition)
	{
		super(game);
		direction = reqDirection;
		damage = reqDamage;
		graphic = new Texture("graphics/weapons/bullets/bullet.png");
		firedByPlayer = playerBullet;
		position = startPosition;
		map = reqMap;
		map.addToObjectList(this);
	}
	
	@Override
	public void update()
	{
		position.x += (speed * direction);
		
		List<GameObject> mapObjects = map.getObjectList();
		
		for (GameObject object : mapObjects)
		{
			if (object instanceof Player && !firedByPlayer)
			{
				Player player = (Player)object;
				player.takeDamage(damage);
				map.removeFromObjectList(this);
			}
			else if (object instanceof Enemy && firedByPlayer)
			{
				Enemy enemy = (Enemy)object;
				if (enemy.collides(position))
				{
					enemy.takeDamage(damage);
					map.removeFromObjectList(this);
				}
			}
		}
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		spriteBatch.draw(graphic, position.x, position.y);
	}

}
