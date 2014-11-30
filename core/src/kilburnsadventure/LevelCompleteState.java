package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class LevelCompleteState extends GameState{

	private Texture levelCompleteImage;
	private Rectangle continueButton;
	private Rectangle quitButton;
    private MapState map;
	
	public LevelCompleteState(Game game, StateManager manager, MapState reqMap)
	{
		super(game, manager);
		levelCompleteImage = new Texture("graphics/nextgame.png");
		continueButton = new Rectangle(268, 222, 262, 42);
		quitButton = new Rectangle(268, 159, 262, 42);
		map = reqMap;
		map.enabled = false;
		game.completeLevel(map.getMapLvl());
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		Vector2 cameraOffset = new Vector2(gameRef.getCameraPosition().x - gameRef.getWidth() / 2, 
                gameRef.getCameraPosition().y - gameRef.getHeight() / 2);
		spriteBatch.draw(levelCompleteImage, cameraOffset.x, cameraOffset.y);
	}
	
	@Override
	public void update()
	{
		if (Gdx.input.isTouched())
		{
				
			Vector3 touchPoint = gameRef.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			touchPoint.x -= (gameRef.getCameraPosition().x - gameRef.getWidth() / 2);
			touchPoint.y -= (gameRef.getCameraPosition().y - gameRef.getHeight() / 2);
			
			//continue to next level
			if(continueButton.contains(touchPoint.x, touchPoint.y))
			{
				gameRef.resetCamera();
				stateManager.removeState(this);
				stateManager.removeState(map);
				stateManager.addState(new MapState(gameRef,  stateManager, map.getMapLvl() + 1));
			}
			//quit
			else if (quitButton.contains(touchPoint.x, touchPoint.y))
			{
				gameRef.resetCamera();
				stateManager.removeState(this);
				stateManager.removeState(map);
				stateManager.addState(new TitleState(gameRef,  stateManager));
			}
		}
	}
}
