package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map extends GameObject
{
	private int level;
	private String[] mapBackgrounds;
	private Texture buildMap;
	
	public Map(Game game, int requiredLevel)
	{
		super(game);
		level = requiredLevel;
		mapBackgrounds = new String[10];
		mapBackgrounds[0] = "lvl1.jpg";
		mapBackgrounds[1] = "lvl2.jpg";
		buildMap = new Texture("graphics/maps/" + mapBackgrounds[level]);
	}
	
	public Texture getMap()
	{
		return buildMap;
	}
	
	public int getSizeOfMapX()
	{
		int width = buildMap.getWidth();
		
		return width;
	}
	
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
