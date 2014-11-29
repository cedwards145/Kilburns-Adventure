package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class LevelSelectState extends GameState 
{
	private Texture levels;
	
	private Rectangle[] buttons;

	public LevelSelectState(Game game, StateManager manager)
	{
		super(game, manager);
		levels = new Texture("graphics/selectLevel.jpg");
		
		buttons = new Rectangle[5];
		
		for (int index = 0; index < 5; index++)
		{
			buttons[index] = new Rectangle(26 + 150 * index, 14, 126, 126);
		}
	}
	
	@Override 
	public void update()
	{
		if (Gdx.input.isTouched())
		{
			int x = Gdx.input.getX();
			int y = gameRef.getHeight() - Gdx.input.getY();
			
			for (int levelIndex = 0; levelIndex < buttons.length; levelIndex++)
			{
				if (buttons[levelIndex].contains(x, y))
				{
					stateManager.removeState(this);
					stateManager.addState(new MapState(gameRef, stateManager, levelIndex));
				}
			}
					
		}
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		spriteBatch.draw(levels, 0, 0);
	}
}
