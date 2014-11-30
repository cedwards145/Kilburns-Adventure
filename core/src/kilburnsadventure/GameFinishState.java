package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameFinishState extends GameState{

	private Texture levelCompleteImage;
    private MapState map;
	
	
	public GameFinishState(Game game, StateManager manager, MapState reqMap)
	{
		super(game, manager);
		levelCompleteImage = new Texture("graphics/gamefinal.png");
		map = reqMap;
		map.enabled = false;
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
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
		{
			stateManager.removeState(this);
			stateManager.removeState(map);
			stateManager.addState(new TitleState(gameRef,  stateManager));
		}
		
		if (Gdx.input.isTouched())
		{
			stateManager.removeState(this);
			stateManager.removeState(map);
			stateManager.addState(new TitleState(gameRef,  stateManager));
		}
	}
}
