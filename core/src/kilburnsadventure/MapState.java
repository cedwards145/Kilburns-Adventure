package kilburnsadventure;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class MapState extends GameState 
{
	private Texture map;
	private int mapID;
	private List<GameObject> objects = new ArrayList<GameObject>();
	private List<GameObject> toAdd = new ArrayList<GameObject>();
	private List<GameObject> toRemove = new ArrayList<GameObject>();
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
		player = new Player(game,0,0);
		objects.add(player);
	}
	
	public List<GameObject> getObjectList()
	{
		return objects;
	}
	
	public boolean addToObjectList(GameObject object)
	{
		return toAdd.add(object);
	}
	
	public boolean removeFromObjectList(GameObject object)
	{
		return toRemove.add(object);
	}
	
	public void spawnEnemies(int noOfEnemies, int waves)
	{
		float xPlayerPos = player.getPlayerPos().x;
		//float yPlayerPos = player.getPlayerPos().y;
		
		if (xPlayerPos > 300)
		{
			int different = 0;
			for(int i = 0; i < noOfEnemies; i++)
			{
				different += 50;
				pilots = new Enemy(game, this, 800,different);
				addToObjectList(pilots);
			}
		}
		
		lastSpawnTime = TimeUtils.nanoTime();
	}
	
	@Override
	public void update()
	{
		super.update();
		for(int object = 0; object < objects.size(); object++)
		{
			objects.get(object).update();
		}
		gameRef.cameraLookAt(player.getPlayerPos());
		if(TimeUtils.nanoTime() - lastSpawnTime > 1000000000)
			spawnEnemies(5, 1);
		
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


