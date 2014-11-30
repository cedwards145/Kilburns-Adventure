package kilburnsadventure;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class MapState extends GameState 
{
	// Initialise instance variables
	private List<MapObject> objects = new ArrayList<MapObject>();
	private List<MapObject> toAdd = new ArrayList<MapObject>();
	private List<MapObject> toRemove = new ArrayList<MapObject>();
	private Player player;
	private Map map;
	private Game game;
	private int mapLvl;
	private int frames = 0, noOfFramesBetween = 0;
	float xPlayerPosX, xPlayerPosY;
	private String displayScore = "", displayHealth = "";
	private BitmapFont font;
	private Weapon weapon;
	private Texture initialiseMap, pbTexture,
									pbUpdate, smallBaloon;
	private boolean pushedGameOver = false;
	private ItemDrop drops;
	private Integer[][] array;
	
	public MapState(Game requiredGame, StateManager reqStateManager,
			            int requiredMapLvl)
	{
		super(requiredGame, reqStateManager);
		mapLvl = requiredMapLvl;
		game = requiredGame;
		map = new Map(game,mapLvl);
		player = new Player(game,this, 0,0);
		font = new BitmapFont();
		array = map.getArray();
		noOfFramesBetween = array[mapLvl][4];
		pbTexture = map.getProgressBar();
		pbUpdate = map.getProgressBarUpdate();
		weapon = player.getWeapon();
		initialiseMap = map.getMap();
		smallBaloon = map.getSmallBaloon();
		// Add player to the list of objects.
		objects.add(player);
	}
	
	public List<MapObject> getObjectList()
	{
		return objects;
	}
	
	public boolean addToObjectList(MapObject object)
	{
		return toAdd.add(object);
	}
	
	public boolean removeFromObjectList(MapObject object)
	{
		return toRemove.add(object);
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public int getMapLvl()
	{
		return mapLvl;
	}
	
	public void spawnEnemies(int noOfEnemies)
	{
		if (xPlayerPosX > 150)
		{
			int xOffset = 0;

			for(int i = 0; i < noOfEnemies; i++)
			{
				xOffset = MathUtils.random(600,1500);
				Enemy pilots = new Enemy(game, this, xOffset + xPlayerPosX, 
						                     MathUtils.random(120,400));
				addToObjectList(pilots);
			}
		}
	}
	
	public void spawnHealth()
	{
		Vector2 position = new Vector2(xPlayerPosX + 400, 480);
		drops = new HPItem(game, this, position, array[mapLvl][0]);
		addToObjectList(drops);
	}
	public void spawnShield()
	{
		Vector2 position = new Vector2(xPlayerPosX + 400, 480);
		drops = new ShieldDrop(game, this, position);
		addToObjectList(drops);
	}
	public void spawnAmmo()
	{
		Vector2 position = new Vector2(xPlayerPosX + 400, 480);
		drops = new AmmoDrop(game, this, position, Weapon.Shotgun, array[mapLvl][5]);
		addToObjectList(drops);
	}
	
	@Override
	public void update()
	{
		super.update();
		frames++;
		
		// Get the players position.
		xPlayerPosX = player.getPosition().x;
		xPlayerPosY = player.getPosition().y;
		
		for(int index = 0; index < objects.size(); index++)
		{
			objects.get(index).update();
		}

		gameRef.cameraLookAt(player.getPosition());
		
		// Display
		displayScore = "Score: " + player.getScore();
		displayHealth = "Health: " + player.getCurrentHP();
		
		// Spawn enemies.
		if(frames > noOfFramesBetween)
		{
			spawnEnemies(array[mapLvl][6]);
			frames = 0;
		}
		
		// Spawn health drop.
		int chanceOfDrop = MathUtils.random(1,array[mapLvl][1]);
		if (chanceOfDrop == 1)
		{
			spawnHealth();
		}
		
		// Spawn shield drop
		chanceOfDrop = MathUtils.random(1, array[mapLvl][2]);
		if (chanceOfDrop == 1)
		{
			spawnShield();
		}
		
		// Spawn shield drop
		chanceOfDrop = MathUtils.random(1, array[mapLvl][3]);
		if (chanceOfDrop == 1)
		{
			spawnAmmo();
		}
		
		if(player.getCurrentHP() <=  0 && !pushedGameOver)
		{
			pushedGameOver = true;
			stateManager.addState(new GameOverState(gameRef, stateManager, this));
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
			stateManager.addState(new PauseState(gameRef, stateManager, this));
		
		for(int index = 0; index < toAdd.size(); index++)
		{
			objects.add(toAdd.get(index));
		}
		
		for(int index = 0; index < objects.size(); index++)
		{
			if(objects.get(index) != null && 
				 objects.get(index).getPosition().x < xPlayerPosX - 600)
				 removeFromObjectList(objects.get(index));
		}
		
		for(int index = 0; index < toRemove.size(); index++)
		{
			objects.remove(toRemove.get(index));
		}
		
		toAdd.clear();
		toRemove.clear();
		
		if(player.getPosition().x == map.getSizeOfMapX())
		{
			stateManager.addState(new LevelCompleteState(gameRef, stateManager,this));
		}
		
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		super.draw(spriteBatch);
		spriteBatch.draw(initialiseMap, 0, 0);
		
		// Progress Bar
		spriteBatch.draw(pbTexture, gameRef.getCameraPosition().x - 200, 
				 gameRef.getCameraPosition().y - 220);
		
		
		// Calculate the stretch for the green update on the progress
		// bar.
		float calcStretch = player.getPosition().x / map.getSizeOfMapX() * 400;
		
		// Draw the green update on the progress bar.
		spriteBatch.draw(pbUpdate, gameRef.getCameraPosition().x - 200, 
										 gameRef.getCameraPosition().y - 220, calcStretch, 10);
		
		// Draw small baloon on progress bar
		float calSBBeforeCamera = 180 + calcStretch;
		float calSBWhileCamera = player.getPosition().x + calcStretch + 80;
		if (player.getPosition().x < 100)
		{
			spriteBatch.draw(smallBaloon,  calSBBeforeCamera,
											 gameRef.getCameraPosition().y - 215);
		}
		else
		{
			spriteBatch.draw(smallBaloon, calSBWhileCamera ,
											 gameRef.getCameraPosition().y - 215);
		}

		for(int object = 0; object < objects.size(); object++)
		{
			objects.get(object).draw(spriteBatch);
		}
		
		// Draw scores and health status
		font.draw(spriteBatch, displayScore, 	gameRef.getCameraPosition().x - 370,
				      gameRef.getCameraPosition().y + 230);
		font.draw(spriteBatch, displayHealth, gameRef.getCameraPosition().x + 300,
							gameRef.getCameraPosition().y + 230);
		
		// Draw Joystick
		ControlPanel.drawJoystick(spriteBatch, player.movingUp(), player.movingDown());
		

		drawWeapons(spriteBatch);
	}
	
	
	private void drawWeapons(SpriteBatch spriteBatch)
	{
		//spriteBatch.draw()
	}
}


