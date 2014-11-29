package kilburnsadventure;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapState extends GameState 
{
	Texture map;
	int mapID;
	int noOfMaps;
	List<GameObject> objects = new ArrayList<GameObject>();
	String[] mapBackgrounds;
	
	public MapState(Game game, StateManager stateManager,int requiredMapID)
	{
		super(game, stateManager);
		// Store the given mapID.
		mapID = requiredMapID;
		
		mapBackgrounds = new String[10];
		mapBackgrounds[0] = "lvl1.jpg";
		map = new Texture("graphics/maps/lvl1.jpg");
	}
	
	@Override
	public void update()
	{
		super.update();
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		super.draw(spriteBatch);
		spriteBatch.draw(map, 0, 0);
	}
}


