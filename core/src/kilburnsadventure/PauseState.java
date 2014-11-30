package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class PauseState extends GameState{

	private Texture pauseImage;
	
	private Rectangle resumeButton;
	private Rectangle restartButton;
	private Rectangle quitButton;
    private MapState map;
	
	
	public PauseState(Game game, StateManager manager, MapState reqMap)
	{
		super(game, manager);
		pauseImage = new Texture("graphics/pausegame.png");
		resumeButton = new Rectangle(271, 253, 261, 40);
		restartButton = new Rectangle(271, 201, 261, 40);
		quitButton = new Rectangle(271, 144, 261, 40);
		map = reqMap;
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		Vector2 cameraOffset = new Vector2(gameRef.getCameraPosition().x - gameRef.getWidth() / 2, 
                gameRef.getCameraPosition().y - gameRef.getHeight() / 2);
		spriteBatch.draw(pauseImage, cameraOffset.x, cameraOffset.y);
	}
	
	@Override
	public void update()
	{
		if (Gdx.input.isTouched())
		{
			Vector3 touchPoint = gameRef.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			if(resumeButton.contains(touchPoint.x, touchPoint.y))
				stateManager.removeState(this);
			else if (restartButton.contains(touchPoint.x, touchPoint.y))
			{
				gameRef.resetCamera();
				stateManager.removeState(this);
				stateManager.removeState(map);
				stateManager.addState(new MapState(gameRef, stateManager, map.getMapLvl()));
			}
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
