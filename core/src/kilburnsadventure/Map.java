package kilburnsadventure;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map extends GameObject
{
	private int level;
	private String[] mapBackgrounds;
	
	public Map(Game game, int requiredLevel)
	{
		super(game);
		level = requiredLevel;
		mapBackgrounds = new String[10];
		mapBackgrounds[0] = "lvl1.jpg";
		mapBackgrounds[1] = "lvl2.jpg";
	}
	
	public String getMap()
	{
		return mapBackgrounds[level];
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
