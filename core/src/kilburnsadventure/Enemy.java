package kilburnsadventure;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy extends GameObject{

	//enemy positions coordinates
	private int x, y;
	public Enemy(Game game, int xPosition, int yPosition)
	{
		super(game);
		x = xPosition;
		y = yPosition;
	}
	
	@Override
	public void update()
	{
		
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		
	}
	
	public void fire()
	{
		
	}
}
