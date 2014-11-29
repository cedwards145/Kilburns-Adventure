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
	    playerPos = new Vector2(xPosition, yPosition);
	    playerImage = new Texture("graphics/ballon.png");
	}
	
	//get position vector of player
	public Vector2 getPlayerPos()
	{
		return playerPos;
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
		leftMove = Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A);
		rightMove = Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D);
		upMove = Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W);
		downMove = Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S);
		
		/*--------move the player---------*/
		
		float xSpeed = 80/20;
		float ySpeed = 48/20;
		if (leftMove)
			playerPos.x -= xSpeed;
		
		if (rightMove)
			playerPos.x += xSpeed;
		
		if (upMove)
			playerPos.y += ySpeed;
		
		if (downMove)
			playerPos.y -= ySpeed;
		/*---------------------------------*/
		
		/*-------------make sure player does not run out of bound-----------*/
		if (playerPos.x < 0)
			playerPos.x = 0;
		if (playerPos.y < 0)
			playerPos.y = 0;
		if (playerPos.x > 650)
			playerPos.x = 650;
		if (playerPos.y > 200)
			playerPos.y = 200;
		/*-------------------------------------------------------------------*/
		
	}
}
