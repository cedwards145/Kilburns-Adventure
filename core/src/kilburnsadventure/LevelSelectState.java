package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class LevelSelectState extends GameState 
{
	private Rectangle backButton;
	private Rectangle[] levelButtons;
	private Texture[] levelImages;
	private Texture background;
	private int levelsUnlocked;

	public LevelSelectState(Game game, StateManager manager)
	{
		super(game, manager);
		
		levelsUnlocked = game.getLevelsUnlocked();

		backButton = new Rectangle(268, 100, 265, 41);
		levelButtons = new Rectangle[5];
		levelImages = new Texture[5];
		
		for (int index = 0; index < levelButtons.length; index++)
		{
			levelButtons[index] = new Rectangle(132 + index * 110, 190, 95, 95);
			levelImages[index] = new Texture("graphics/lvlButtons/lvl" + (index + 1) +
					                           (index <= levelsUnlocked ? "" : "lock") + ".png");
		}
		
		background = new Texture("graphics/levelscreen.png");
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
			    for (int levelIndex = 0; levelIndex < levelButtons.length; levelIndex++)
			    {
			     	if (levelButtons[levelIndex].contains(touchPoint.x, touchPoint.y) && levelIndex <= levelsUnlocked)
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
		spriteBatch.draw(background, 0, 0);
		
		for (int levelIndex = 0; levelIndex < levelButtons.length; levelIndex++)
		{
			spriteBatch.draw(levelImages[levelIndex], levelButtons[levelIndex].x, levelButtons[levelIndex].y);
		}
	}
}
