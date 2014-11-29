package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map extends GameObject
{
	// Initialise instance variables.
	private int level;
	private String[] mapBackgrounds;
	private Texture buildMap;
	
	public Map(Game game, int requiredLevel)
	{
		super(game);
		// Receive level from user.
		level = requiredLevel;
		// Intialise mapBackgrounds size
		mapBackgrounds = new String[10];
		// Store map files to array of mapBackgrounds.
		mapBackgrounds[0] = "lvl1.jpg";
		mapBackgrounds[1] = "lvl2.jpg";
		// build the map given the selected level.
		buildMap = new Texture("graphics/maps/" + mapBackgrounds[level]);
	}
	
	// Accessor method for texture
	public Texture getMap()
	{
		return buildMap;
	}
	
	// Accessor method for X coordinate
	public int getSizeOfMapX()
	{
		int width = buildMap.getWidth();
		
		return width;
	}
//Accessor method for Y coordinate
	public int getSizeOfMapY()
	{
		int height = buildMap.getHeight();
		
		return height;
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		
	}
	
	@Override
	public void update()
	{
		
	}
}
