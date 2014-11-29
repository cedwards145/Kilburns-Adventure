package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends MapObject{

	//baloon image
	private Texture playerImage;
	protected Weapon weapon;
	
	//player constructor
	public Player(Game game, MapState containingMap, int xPosition, int yPosition)
	{
	    super(game, containingMap);
	    position = new Vector2(xPosition, yPosition);
	    playerImage = new Texture("graphics/ballon.png");
	    
	    weapon = Weapon.AK47;
	}	
	
	@Override
	public void update()
	{
		updateMotion();
		weapon.update();
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		super.draw(spriteBatch);
		spriteBatch.draw(playerImage, position.x, position.y);	
	}
	
	
	public void updateMotion(){
		//Moving options
		boolean leftMove, rightMove, upMove, downMove;
		//If key is pressed
		leftMove = Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)
				||ControlPanel.leftIsTouched();
		rightMove = Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)
				||ControlPanel.rightIsTouched();
		upMove = Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)
				||ControlPanel.upIsTouched();
		downMove = Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)
				|ControlPanel.downIsTouched();
		
		/*--------move the player---------*/
		
		//float xSpeed = 80/20;
		//float ySpeed = 48/20;
		float xSpeed = 1;
		float ySpeed = 1;
		
		//if (leftMove)
		//	position.x -= xSpeed;
		
		//if (rightMove)
			position.x += xSpeed;
		
		if (upMove)
			position.y += ySpeed;
		
		if (downMove)
			position.y -= ySpeed;
		/*---------------------------------*/
		
		/*-------------make sure player does not run out of bound-----------*/
		if (position.x < 0)
			position.x = 0;
		if (position.y < 0)
			position.y = 0;
		//if (playerPos.x > 650)
			//playerPos.x = 650;
		if (position.y > gameRef.getHeight() - playerImage.getHeight())
			position.y = gameRef.getHeight() - playerImage.getHeight();
		
	
		// Fire bullet
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && weapon.canFire())
		{
			Bullet bullet = new Bullet(gameRef, map, true, weapon.getDamage(), Bullet.RIGHT, position);
		}
		
	}
	/*-------------------------------------------------------------------*/
	
	/*------------Player HP----------------------------*/
	private static final int maxHP = 100;
	private int currentHP;
	
	public int getCurrentHP()
	{
		return currentHP;
	}
	
	public void setMaxHP()
	{
		currentHP = maxHP;
	}
	
	public void takeDamage(int damage)
	{
		currentHP -= damage;
	}
	
	public boolean collides(Vector2 point)
	{
		Rectangle collisionBox = new Rectangle(position.x, position.y, playerImage.getWidth(), playerImage.getHeight());
		return collisionBox.contains(point);
	}
	
}
