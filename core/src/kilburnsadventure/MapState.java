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
		return objects.add(object);
	}
	
	public boolean removeFromObjectList(GameObject object)
	{
		return objects.remove(object);
	}
	
	public void spawnEnemies(int noOfEnemies, int waves)
	{
		float xPlayerPos = player.getPlayerPos().x;
		//float yPlayerPos = player.getPlayerPos().y;
		
		if (xPlayerPos > 300)
		{
			for(int i = 0; i < noOfEnemies; i++)
			{
				int different = 0;
				different += i + 50;
				pilots = new Enemy(game,800,different);
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
			spawnEnemies(10, 1);
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


