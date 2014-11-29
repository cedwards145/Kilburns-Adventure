package kilburnsadventure;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

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
		// Get the map texture.
		initialiseMap = map.getMap();
		// Create the player.
		player = new Player(game,this, 0,0);
		// Add player to the list of objects.
		objects.add(player);
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
			int xOffset = 0;
			// Iterate through the number of enemies
			for(int i = 0; i < noOfEnemies; i++)
			{
				xOffset = MathUtils.random(600,1500);
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
		frames++;
		xPlayerPosX = player.getPosition().x;
		xPlayerPosY = player.getPosition().y;
		
		for(int object = 0; object < objects.size(); object++)
		{
			objects.get(object).update();
		}
		gameRef.cameraLookAt(player.getPosition());
		if(frames > noOfFramesBetween)
		{
			spawnEnemies(5);
			frames = 0;
		}
		
		for(int object = 0; object < toAdd.size(); object++)
		{
			objects.add(toAdd.get(object));
		}
		
		for(int i = 0; i < objects.size(); i++)
		{
			if(objects.get(i).getPosition().x < xPlayerPosX - 600)
			removeFromObjectList(objects.get(i));
		}
		
		for(int object = 0; object < toRemove.size(); object++)
		{
			objects.remove(toRemove.get(object));
		}
		
		toAdd.clear();
		toRemove.clear();
		
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		super.draw(spriteBatch);
		spriteBatch.draw(initialiseMap, 0, 0);
		for(int object = 0; object < objects.size(); object++)
		{
			objects.get(object).draw(spriteBatch);
		}
	}
}


