package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject{

	//baloon image
	private Texture playerImage;
	//coordinates of Player
	private Vector2 playerPos;
	
	//player constructor
	public Player(Game game, int xPosition, int yPosition)
	{
	    super(game);
	    playerPos.x = xPosition;
	    playerPos.y = yPosition;
	    playerImage = new Texture("graphics/ballon.png");
	}
	
	@Override
	public void update()
	{
		updateMotion();
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		super.draw(spriteBatch);
		spriteBatch.draw(playerImage, playerPos.x, playerPos.y);	
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
			playerPos.x -= 80;
		
		if (rightMove)
			playerPos.x += 80;
		
		if (upMove)
			playerPos.y += 48;
		
		if (downMove)
			playerPos.y-= 48;
		/*---------------------------------*/
		
		/*-------------make sure player does not run out of bound-----------*/
		if (playerPos.x < 0)
			playerPos.x = 0;
		if (playerPos.y < 0)
			playerPos.y = 0;
		if (playerPos.x > 800)
			playerPos.x = 800;
		if (playerPos.y > 480)
			playerPos.y = 480;
		/*-------------------------------------------------------------------*/
		
	}
}
