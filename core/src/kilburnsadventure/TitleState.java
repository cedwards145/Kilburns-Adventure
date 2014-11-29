package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TitleState extends GameState 
{
	private Texture titleImage, balloonImage;
	private Vector2 balloonPos;
	
	
	public TitleState(Game game, StateManager manager)
	{
		super(game, manager);
		titleImage = new Texture("graphics/titlescreen.png");
		balloonImage = new Texture("graphics/ballon.png");
		balloonPos = new Vector2(600, 150);
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		super.draw(spriteBatch);
		spriteBatch.draw(titleImage, 0, 0);
		spriteBatch.draw(balloonImage, balloonPos.x, balloonPos.y);		
	}
	
	@Override
	public void update()
	{
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();
		else if (Gdx.input.isKeyPressed(Input.Keys.ENTER) || 
				 Gdx.input.isTouched())
		{
			stateManager.removeState(this);
			stateManager.addState(new LevelSelectState(gameRef, stateManager));
		}

	}
}
