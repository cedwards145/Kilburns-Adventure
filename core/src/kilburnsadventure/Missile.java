package kilburnsadventure;

import com.badlogic.gdx.math.Vector2;

public class Missile extends Bullet 
{
	protected Enemy following;
	
	public Missile(Game game, MapState reqMap, boolean playerBullet, int reqDamage, double reqRotation, Vector2 startPosition, Enemy toFollow)
	{
		super(game, reqMap, playerBullet, reqDamage, reqRotation, startPosition);
		following = toFollow;
	}
	
	@Override
	public void update()
	{
		Vector2 enemyPosition = following.getPosition();
		Vector2 difference = new Vector2(position.x - enemyPosition.x - 10,
                						 position.y - enemyPosition.y - 10);
		
		rotation = Math.atan((double)(difference.y / difference.x));
		
		super.update();
	}
}
