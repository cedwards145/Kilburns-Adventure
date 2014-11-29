package kilburnsadventure;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapState extends GameState 
{
	Texture map;
	int mapID;
	List<GameObject> objects = new ArrayList<GameObject>();
	
	public MapState(Game game, StateManager stateManager,int requiredMapID)
	{
		super(game, stateManager);	
		map = new Texture("assets/graphics/maps/lvl1.jpg");
		mapID = requiredMapID;
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


