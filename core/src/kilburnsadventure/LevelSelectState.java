package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class LevelSelectState extends GameState 
{
	private Texture levels;
	
	private Rectangle[] buttons;
	
	private Rectangle quitButton;
	private Texture quitImage;
	private Vector2 quitPos;

	public LevelSelectState(Game game, StateManager manager)
	{
		super(game, manager);
		levels = new Texture("graphics/selectLevel.jpg");
		
		buttons = new Rectangle[5];
		
		for (int index = 0; index < 5; index++)
		{
			buttons[index] = new Rectangle(26 + 150 * index, 14, 126, 126);
		}
		
		
		quitImage = new Texture("graphics/quitImage.jpg");
		quitPos = new Vector2(game.getWidth() / 2, game.getHeight() / 2);
		quitButton = new Rectangle(quitPos.x, quitPos.y, 126, 126);
	}
	
	@Override 
	public void update()
	{
		if (Gdx.input.isTouched())
		{
			//Check is the quite button is pressed
			Vector3 touchPoint = gameRef.getCamera().unproject( new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0) );
			if(quitButton.contains(touchPoint.x, touchPoint.y))
				Gdx.app.exit();
			
			
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
		spriteBatch.draw(quitImage, quitPos.x, quitPos.y);
	}
}
