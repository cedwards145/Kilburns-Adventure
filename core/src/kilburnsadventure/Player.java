package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Player extends MapObject{

	//baloon image
	private Texture playerImage;
	protected Weapon weapon;
	private int score = 0;
	protected int movementTouchIndex, weaponTouchIndex;
	protected boolean leftMove = false, rightMove = false, upMove = false, downMove = false;
	protected double gunRotation = 0;
	
	//player constructor
	public Player(Game game, MapState containingMap, int xPosition, int yPosition)
	{
	    super(game, containingMap);
	    position = new Vector2(xPosition, yPosition);
	    playerImage = new Texture("graphics/ballon.png");
	    
	    currentHP = maxHP;
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
		//spriteBatch.draw(weapon.getImage(), position.x + 50, position.y + 25);
		Texture weaponImage = weapon.getImage();
		spriteBatch.draw(weaponImage, position.x + 50, position.y + 25, 0, 0, weaponImage.getWidth(), weaponImage.getHeight(),
				         1, 1, (float)Math.toDegrees(gunRotation), 0, 0, weaponImage.getWidth(), weaponImage.getHeight(), false, false);
	}
		
	public void updateMotion()
	{
		movementTouchIndex = -1;
		weaponTouchIndex = -1;
		
		if (Gdx.input.isTouched(0))
		{
			Vector3 touchPosition3 = gameRef.getCamera().unproject(new Vector3(Gdx.input.getX(0), Gdx.input.getY(0), 0));
			Vector2 touchPosition = new Vector2(touchPosition3.x, touchPosition3.y);
			
			if (ControlPanel.contains(touchPosition))
				movementTouchIndex = 0;
			else
				weaponTouchIndex = 0;
		}
		if (Gdx.input.isTouched(1))
		{
			Vector3 touchPosition3 = gameRef.getCamera().unproject(new Vector3(Gdx.input.getX(1), Gdx.input.getY(1), 0));
			Vector2 touchPosition = new Vector2(touchPosition3.x, touchPosition3.y);
			
			if (ControlPanel.contains(touchPosition))
				movementTouchIndex = 1;
			else
				weaponTouchIndex = 1;
		}
		
		if (movementTouchIndex != -1 && Gdx.input.isTouched(movementTouchIndex))
		{
			Vector3 touchPosition3 = gameRef.getCamera().unproject(new Vector3(Gdx.input.getX(movementTouchIndex), Gdx.input.getY(movementTouchIndex), 0));
			Vector2 touchPosition = new Vector2(touchPosition3.x, touchPosition3.y);
			
			//If key is pressed
			leftMove = Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)
					||ControlPanel.leftIsTouched(touchPosition);
			rightMove = Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)
					||ControlPanel.rightIsTouched(touchPosition);
			upMove = Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)
					||ControlPanel.upIsTouched(touchPosition);
			downMove = Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)
					||ControlPanel.downIsTouched(touchPosition);
		}
		else
		{
			leftMove = Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A);
			rightMove = Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D);
			upMove = Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W);
			downMove = Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S);
		}
		/*--------move the player---------*/
		
		//float xSpeed = 80/20;
		//float ySpeed = 48/20;
		float xSpeed = 1;
		float ySpeed = 1.5f;
		
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
		if (weaponTouchIndex != -1 && Gdx.input.isTouched(weaponTouchIndex) && weapon.canFire())
		{
			float gunLength = weapon.getImage().getWidth(); 
			
			Vector3 touchPosition3 = gameRef.getCamera().unproject(new Vector3(Gdx.input.getX(weaponTouchIndex), 
					                                                           Gdx.input.getY(weaponTouchIndex), 0));
			Vector2 touchPosition = new Vector2(touchPosition3.x, touchPosition3.y);
			
			Vector2 difference = new Vector2(position.x + 50 - touchPosition.x,
					                         position.y + 25 - touchPosition.y);
			
			Vector2 bulletOrigin = new Vector2(position.x + 50 + (float)Math.cos(gunRotation) * gunLength, 
					                           position.y + 25 + (float)Math.sin(gunRotation) * gunLength);
			
			double angle = Math.atan((double)(difference.y / difference.x));
			gunRotation = angle;
			
			Bullet bullet = new Bullet(gameRef, map, true, weapon.getDamage(), angle, bulletOrigin);
			WeaponSound.AK47.getWeaponSound().play();
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
	
	public int getmaxHP()
	{
		return maxHP;
	}
	public void setMaxHP()
	{
		currentHP = maxHP;
	}
	
	public void takeDamage(int damage)
	{
		currentHP -= damage;
		Gdx.input.vibrate(100);
	}
	
	//functions used in HPItem
	public void addHP(int reqHP)
	{
		switch (reqHP)
		{
		case 0: 
			{
				currentHP += 25;
				if (currentHP > maxHP)
					setMaxHP();
				break;
			}
		case 1: 
			{
				currentHP += 50;
				if (currentHP > maxHP)
					setMaxHP();
				break;
			}
		case 2: 
			{
				setMaxHP();
				break;
			}
		}
	}
	
	public boolean collides(Vector2 point)
	{
		Rectangle collisionBox = new Rectangle(position.x, position.y, playerImage.getWidth(), playerImage.getHeight());
		return collisionBox.contains(point);
	}
	
	public Rectangle getCollisionBox()
	{
		return new Rectangle(position.x, position.y, playerImage.getWidth(), playerImage.getHeight());
	}
	
	//Score-----------------------------------------------------------------------------------------------------
	public void addScore(int givenScore)
	{
	  score += givenScore;
	}//addScore
	
	public int getScore()
	{
		return score;
	}//getScore
	
	public Weapon getWeapon()
	{
		return weapon;
	}
	public boolean movingUp()
	{
		return upMove;
	}
	public boolean movingDown()
	{
		return downMove;
	}
	
	//Score-----------------------------------------------------------------------------------------------------
}
