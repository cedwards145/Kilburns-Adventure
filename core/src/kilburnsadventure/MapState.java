package kilburnsadventure;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapState extends GameState 
{
	private Texture map;
	private int mapID;
	private List<GameObject> objects = new ArrayList<GameObject>();
	private String[] mapBackgrounds;
	private Player player;
	
	public MapState(Game game, StateManager stateManager,int requiredMapID)
	{
		super(game, stateManager);
		// Store the given mapID.
		mapID = requiredMapID;
		
		mapBackgrounds = new String[10];
		mapBackgrounds[0] = "lvl1.jpg";
		mapBackgrounds[1] = "lvl2.jpg";
		map = new Texture("graphics/maps/" + mapBackgrounds[mapID]);
		player = new Player(game,0,0);
		objects.add(player);
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


