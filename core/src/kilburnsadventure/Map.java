package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Map extends GameObject
{
	// Initialise instance variables.
	private int level;
	private String[] mapBackgrounds;
	private Texture buildMap, progressBar, progressBarUpdate,
									smallBaloon;
	
	public Map(Game game, int requiredLevel)
	{
		super(game);
		level = requiredLevel;
		mapBackgrounds = new String[5];
		mapBackgrounds[0] = "lvl1.jpg";
		mapBackgrounds[1] = "lvl2.jpg";
		mapBackgrounds[2] = "lvl3.jpg";
		mapBackgrounds[3] = "lvl4.jpg";
		mapBackgrounds[4] = "lvl5.jpg";
		
		buildMap = new Texture("graphics/maps/" + mapBackgrounds[level]);
		
		progressBar = new Texture("graphics/progressBar.jpg");
		progressBarUpdate = new Texture("graphics/progressBarUpdate.jpg");
		smallBaloon = new Texture("graphics/ballonSmall.png");
	}
	
	public Integer[][] levelSettings()
	{
		Integer[][] array = new Integer[5][7];
		for(int i = 0; i < 5; i++)
		{
				array[i][0] = (int) (30 * Math.pow(1.36, i)); // SizeOfHealth
				array[i][1] = (int) (2000 * Math.pow(1.36, i)); // HealthChanceDrop
				array[i][2] = (int) (1500 * Math.pow(1.36, i)); // ShieldChanceDrop
				array[i][3] = (int) (600 * Math.pow(1.36, i)); // AmmoChanceDrop
				array[i][4] = (int) (300 * Math.pow(0.95, i)); // EnemyFrequency
				array[i][5] = (int) (3 * Math.pow(1.36, i)); // Ammo size
				array[i][6] = (int) (3 * Math.pow(1.4, i)); // Enemy size
		}
		
		return array;
	}
	
	public Integer[][] getArray()
	{
		return levelSettings();
	}
	
	// Accessor method for texture
	public Texture getMap()
	{
		return buildMap;
	}
	
  //Accessor method for texture
	public Texture getProgressBar()
	{
		return progressBar;
	}
	
	public Texture getProgressBarUpdate()
	{
		return progressBarUpdate;
	}
	
	public Texture getSmallBaloon()
	{
		return smallBaloon;
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
