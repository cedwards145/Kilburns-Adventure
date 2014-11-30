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
	private Rectangle startButton;
	
	private double x;
	
	public TitleState(Game game, StateManager manager)
	{
		super(game, manager);
		titleImage = new Texture("graphics/titlescreen.png");
		balloonImage = new Texture("graphics/ballon.png");
		balloonPos = new Vector2(130, 180);
		
		startButton = new Rectangle(489, 231, 262, 42);
		quitButton = new Rectangle(489, 85, 262, 42);
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
		
		balloonPos.y = 125 + (float)(Math.sin(Math.toRadians(x)) * 50);
		x++;
		
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();
		else if (Gdx.input.isTouched())
		{
			Vector3 touchPoint = gameRef.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			if(quitButton.contains(touchPoint.x, touchPoint.y))
				Gdx.app.exit();
			else if(startButton.contains(touchPoint.x, touchPoint.y))
			{
				stateManager.removeState(this);
				stateManager.addState(new LevelSelectState(gameRef, stateManager));
			}
		}
		
		//Quit Button action
	//	if (Gdx.input.isTouched())
//		{
			//Vector3 touchPoint = gameRef.getCamera().unproject( new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0) );
		//	if(quitButton.contains(touchPoint.x, touchPoint.y))
	//			Gdx.app.exit();
//		}//if

	}
}
