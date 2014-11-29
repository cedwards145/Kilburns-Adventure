package kilburnsadventure;

import com.badlogic.gdx.math.Vector2;

public class Building extends GameObject 
{
	protected Vector2 position;
	protected int width, height;
	
	public Building(Game game, Vector2 reqPosition)
	{
		super(game);
		
		position = reqPosition;
		width = 50;
		height = 50;
	}
}
