package kilburnsadventure;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameState extends GameObject 
{

	protected StateManager stateManager;
	
	public GameState(Game game, StateManager manager)
	{
		super(game);
		
		stateManager = manager;
	}
	
	@Override
	public void update()
	{
		super.update();
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		super.draw(spriteBatch);
	}
}
