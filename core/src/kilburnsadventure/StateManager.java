package kilburnsadventure;

import java.util.List;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StateManager extends GameObject 
{
	private List<GameState> gameStates, toAdd, toRemove;
	
	public StateManager(Game game)
	{
		super(game);
		
		gameStates = new ArrayList<GameState>();
		toAdd = new ArrayList<GameState>();
		toRemove = new ArrayList<GameState>();
	}
	
	@Override 
	public void update()
	{
		for (GameState state : gameStates)
		{
			if (state.enabled)
				state.update();
		}
		
		for (GameState state : toAdd)
			gameStates.add(state);
		
		for (GameState state : toRemove)
			gameStates.remove(state);
		
		toAdd.clear();
		toRemove.clear();
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		for (GameState state : gameStates)
		{
			if (state.visible)
				state.draw(spriteBatch);
		}
	}
	
	public void addState(GameState state)
	{
		toAdd.add(state);
	}
	
	public void removeState(GameState state)
	{
		toRemove.add(state);
	}
}
