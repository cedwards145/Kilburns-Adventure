package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends GameObject{

	//coordinates of Player
	private int x, y;
	
	//player constructor
	public Player(Game game, int xPosition, int yPosition)
	{
	    super(game);
	    x = xPosition;
	    y = yPosition;
	}
	
	@Override
	public void update()
	{
		updateMotion();
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		
	}
	
	
	private void updateMotion(){
		boolean leftMove, rightMove, upMove, downMove;
		//If key is pressed
		leftMove = Gdx.input.isKeyPressed(Keys.LEFT);
		rightMove = Gdx.input.isKeyPressed(Keys.RIGHT);
		upMove = Gdx.input.isKeyPressed(Keys.UP);
		downMove = Gdx.input.isKeyPressed(Keys.DOWN);
		
		/*--------move the player---------*/
		if (leftMove)
			x -= 80;
		
		if (rightMove)
			x += 80;
		
		if (upMove)
			y += 48;
		
		if (downMove)
			y -= 48;
		/*---------------------------------*/
		
		/*-------------make sure player does not run out of bound-----------*/
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		if (x > 800)
		    x = 800;
		if (y > 480)
			y = 480;
		/*-------------------------------------------------------------------*/
		
	}
}
