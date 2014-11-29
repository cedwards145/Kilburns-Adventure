package kilburnsadventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class MapState extends GameState 
{
	private Texture initialiseMap;
	private int mapID;
	private List<MapObject> objects = new ArrayList<MapObject>();
	private List<MapObject> toAdd = new ArrayList<MapObject>();
	private List<MapObject> toRemove = new ArrayList<MapObject>();
	private Player player;
	float xPlayerPosX, xPlayerPosY;
	private Game game;
	private int frames = 0, noOfFramesBetween = 0;
	
	public MapState(Game requiredGame, StateManager stateManager,int requiredMapID)
	{
		super(requiredGame, stateManager);
		// Store the given mapID.
		mapID = requiredMapID;
		game = requiredGame;
		Map map = new Map(game,mapID);
		initialiseMap = new Texture("graphics/maps/" + map.getMap());
		player = new Player(game,this, 0,0);
		objects.add(player);
		noOfFramesBetween = 300;
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
		if (xPlayerPosX > 300)
		{
			int different = 0;
			for(int i = 0; i < noOfEnemies; i++)
			{
				different += 200;
				Enemy pilots = new Enemy(game, this, different + xPlayerPosX, MathUtils.random(50,400));
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


