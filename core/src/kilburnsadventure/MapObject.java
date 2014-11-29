package kilburnsadventure;

import com.badlogic.gdx.math.Vector2;

public class MapObject extends GameObject 
{
	protected MapState map;
	protected Vector2 position;
	
	public MapObject(Game game, MapState containingMap)
	{
		super(game);
	}
	
	public Vector2 getPosition()
	{
		return position;
	}
}
