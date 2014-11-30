package kilburnsadventure;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;


public class MapState extends GameState 
{
	// Initialise instance variables
	private List<MapObject> objects = new ArrayList<MapObject>();
	private List<MapObject> toAdd = new ArrayList<MapObject>();
	private List<MapObject> toRemove = new ArrayList<MapObject>();
	private Player player;
	private Texture initialiseMap;
	private Map map;
	private Game game;
	private int mapLvl;
	private int frames = 0, noOfFramesBetween = 0;
	float xPlayerPosX, xPlayerPosY;
	private String displayScore;
	private String displayHealth;
	private BitmapFont font;
	private Texture pbTexture;
	private Texture pbUpdate;
	private Texture smallBaloon;
	
	public MapState(Game requiredGame, StateManager reqStateManager,
			            int requiredMapLvl)
	{
		super(requiredGame, reqStateManager);
		// Get the values from constructor.
		mapLvl = requiredMapLvl;
		game = requiredGame;
		// Used in spawn method.
		noOfFramesBetween = 300;
		// Get the map with the chosen level from
		// the user.
		map = new Map(game,mapLvl);
		pbTexture = map.getProgressBar();
		pbUpdate = map.getProgressBarUpdate();
		// Get the map texture.
		initialiseMap = map.getMap();
		smallBaloon = map.getSmallBaloon();
		// Create the player.
		player = new Player(game,this, 0,0);
		// Add player to the list of objects.
		objects.add(player);
		font = new BitmapFont();
		displayHealth = "";
		displayScore = "";
	}
	
	// Accessor Method.
	public List<MapObject> getObjectList()
	{
		return objects;
	}
	
	// Add object to toAdd list.
	public boolean addToObjectList(MapObject object)
	{
		return toAdd.add(object);
	}
	
	// Remove from toRemove list.
	public boolean removeFromObjectList(MapObject object)
	{
		return toRemove.add(object);
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	// Spawn enemies on map
	public void spawnEnemies(int noOfEnemies)
	{
		if (xPlayerPosX > 150)
		{
			// Intialise xoffset
			int xOffset = 0;
			// Iterate through the number of enemies
			for(int i = 0; i < noOfEnemies; i++)
			{
				// Set xOffset
				xOffset = MathUtils.random(600,1500);
				// Create enemies
				Enemy pilots = new Enemy(game, this, xOffset + xPlayerPosX, 
						                     MathUtils.random(50,400));
				addToObjectList(pilots);
			}
		}
	}
	
	@Override
	public void update()
	{
		super.update();
		// Increment frames
		frames++;
		// Get the players position.
		xPlayerPosX = player.getPosition().x;
		xPlayerPosY = player.getPosition().y;
		
		// Iterate through list and update.
		for(int index = 0; index < objects.size(); index++)
		{
			objects.get(index).update();
		}
		// Set camera to follow player's position.
		gameRef.cameraLookAt(player.getPosition());
		
		displayScore = "Score :" + player.getScore();
		displayHealth = "Health :" + player.getCurrentHP();
		
		// Spawn enemies.
		if(frames > noOfFramesBetween)
		{
			spawnEnemies(5);
			frames = 0;
		}
		
		// Iterate through the toAdd list.
		for(int index = 0; index < toAdd.size(); index++)
		{
			// Add the items from the toAdd list to the object
			// list.
			objects.add(toAdd.get(index));
		}
		
		// Iterate trough the objects list to remove
		// objects from Map after the player has passed
		for(int index = 0; index < objects.size(); index++)
		{
			if(objects.get(index).getPosition().x < xPlayerPosX - 600)
				removeFromObjectList(objects.get(index));
		}
		
		// Iterate through the remove list and remove objects.
		for(int index = 0; index < toRemove.size(); index++)
		{
			objects.remove(toRemove.get(index));
		}
		
		// Clear lists
		toAdd.clear();
		toRemove.clear();
		
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		super.draw(spriteBatch);
		// Draw map texture
		spriteBatch.draw(initialiseMap,0,0);
		
		// Draw the progress bar image
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
		// Iterate through the list of objects and
		// draw them.
		for(int object = 0; object < objects.size(); object++)
		{
			objects.get(object).draw(spriteBatch);
		}
		
		font.draw(spriteBatch, displayScore, 	gameRef.getCameraPosition().x - 400,
				      gameRef.getCameraPosition().y + 230);
		font.draw(spriteBatch, displayHealth, gameRef.getCameraPosition().x + 300,
							gameRef.getCameraPosition().y + 230);
	}
}


