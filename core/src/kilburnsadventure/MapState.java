package kilburnsadventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class MapState extends GameState 
{
	private Texture map;
	private int mapID;
	private List<MapObject> objects = new ArrayList<MapObject>();
	private List<MapObject> toAdd = new ArrayList<MapObject>();
	private List<MapObject> toRemove = new ArrayList<MapObject>();
	private String[] mapBackgrounds;
	private Player player;
	private Enemy pilots;
	private Game game;
	private float lastSpawnTime;
	
	public MapState(Game requiredGame, StateManager stateManager,int requiredMapID)
	{
		super(requiredGame, stateManager);
		// Store the given mapID.
		mapID = requiredMapID;
		game = requiredGame;
		mapBackgrounds = new String[10];
		mapBackgrounds[0] = "lvl1.jpg";
		mapBackgrounds[1] = "lvl2.jpg";
		map = new Texture("graphics/maps/" + mapBackgrounds[mapID]);
		player = new Player(game,this, 0,0);
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
	
	public void spawnEnemies(int noOfEnemies)
	{
		float xPlayerPos = player.getPosition().x;
		if (xPlayerPos > 300)
		{
			int different = 0;
			for(int i = 0; i < noOfEnemies; i++)
			{
				different += 200;
				pilots = new Enemy(game, this, different, randInt(50,500));
				addToObjectList(pilots);
			}
		}
		
		lastSpawnTime = TimeUtils.nanoTime();
	}
	
	//
	public static int randInt(int min, int max) 
	{
    Random rand = new Random();
    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
	}
	
	@Override
	public void update()
	{
		super.update();
		for(int object = 0; object < objects.size(); object++)
		{
			objects.get(object).update();
		}
		gameRef.cameraLookAt(player.getPosition());
		if(TimeUtils.nanoTime() - lastSpawnTime > 1000000000)
			spawnEnemies(5);
		
		for(int object = 0; object < toAdd.size(); object++)
		{
			objects.add(toAdd.get(object));
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
		spriteBatch.draw(map, 0, 0);
		for(int object = 0; object < objects.size(); object++)
		{
			objects.get(object).draw(spriteBatch);
		}
	}
}


