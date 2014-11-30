package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GameOverState extends GameState 
{
	protected Texture gameOverImage;
	protected Rectangle retryButton, quitButton;
	protected MapState map;
	
	public GameOverState(Game game, StateManager manager, MapState reqMap)
	{
		super(game, manager);
		
		gameOverImage = new Texture("graphics/gameover.png");
		retryButton = new Rectangle(271, 222, 261, 40);
		quitButton = new Rectangle(271, 159, 261, 40);
		
		map = reqMap;
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		Vector2 cameraOffset = new Vector2(gameRef.getCameraPosition().x - gameRef.getWidth() / 2, 
                gameRef.getCameraPosition().y - gameRef.getHeight() / 2);
		spriteBatch.draw(gameOverImage, cameraOffset.x, cameraOffset.y);
	}
	
	@Override
	public void update()
	{
		if (Gdx.input.isTouched())
		{
			Vector3 touchPoint = gameRef.getCamera().unproject( new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0) );
			
			touchPoint.x -= (gameRef.getCameraPosition().x - gameRef.getWidth() / 2);
			touchPoint.y -= (gameRef.getCameraPosition().y - gameRef.getHeight() / 2);
			
			if (retryButton.contains(touchPoint.x, touchPoint.y))
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
