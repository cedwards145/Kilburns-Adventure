package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class TitleState extends GameState 
{
	private Texture titleImage, balloonImage;
	private Vector2 balloonPos;
	
	private Rectangle quitButton;
	private Texture quitImage;
	private Vector2 quitPos;
	
	
	public TitleState(Game game, StateManager manager)
	{
		super(game, manager);
		titleImage = new Texture("graphics/titlescreen.png");
		balloonImage = new Texture("graphics/ballon.png");
		balloonPos = new Vector2(600, 150);
		
		
		quitImage = new Texture("graphics/quitImage.jpg");
		quitPos = new Vector2(game.getWidth() / 2, game.getHeight() / 2);
		quitButton = new Rectangle(quitPos.x, quitPos.y, 126, 126);
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		super.draw(spriteBatch);
		spriteBatch.draw(titleImage, 0, 0);
		spriteBatch.draw(quitImage, quitPos.x, quitPos.y);
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
		
		//Quit Button action
		if (Gdx.input.isTouched())
		{
			Vector3 touchPoint = gameRef.getCamera().unproject( new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0) );
			if(quitButton.contains(touchPoint.x, touchPoint.y))
				Gdx.app.exit();
		}//if

	}
}
