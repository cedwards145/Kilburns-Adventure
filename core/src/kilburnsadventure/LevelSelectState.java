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
	
	private Rectangle backButton;
	private Texture backImage;
	private Vector2 backPos;

	public LevelSelectState(Game game, StateManager manager)
	{
		super(game, manager);
		levels = new Texture("graphics/selectLevel.jpg");
		
		buttons = new Rectangle[5];
		
		for (int index = 0; index < 5; index++)
		{
			buttons[index] = new Rectangle(26 + 150 * index, 14, 126, 126);
		}
		
		
		backImage = new Texture("graphics/quitImage.jpg");
		backPos = new Vector2(0, game.getHeight() - 100);
		backButton = new Rectangle(backPos.x, backPos.y, 126, 126);
	}
	
	@Override 
	public void update()
	{
		if (Gdx.input.isTouched())
		{
			//Check is the quite button is pressed
			Vector3 touchPoint = gameRef.getCamera().unproject( new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0) );
			if(backButton.contains(touchPoint.x, touchPoint.y))
			{
				stateManager.removeState(this);
				stateManager.addState(new TitleState(gameRef, stateManager));
			}//if
			else
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
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		spriteBatch.draw(levels, 0, 0);
		spriteBatch.draw(backImage, backPos.x, backPos.y);
	}
}
